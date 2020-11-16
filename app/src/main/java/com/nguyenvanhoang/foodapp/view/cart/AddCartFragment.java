package com.nguyenvanhoang.foodapp.view.cart;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.database.CreateDatabaseSQLite;
import com.nguyenvanhoang.foodapp.database.MonAnCart;
import com.nguyenvanhoang.foodapp.entities.MonAn;
import com.nguyenvanhoang.foodapp.entities.NhaHang;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class AddCartFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    private ImageView imageViewMonAnCart;
    private TextView tvTenMonAnChon,tvSoLuongChon;
    private Button btnCongSoLuong,btnTruSoLuong,btnThemVaoGioHang;
    private int soLuongChon = 1;
    private CreateDatabaseSQLite databaseSQLite;
    private FirebaseDatabase firebaseDatabase ;
    private static MonAn monAnChon;
    private static String tenNhaHang_send,diaChiNhaHang_send;
    public AddCartFragment(MonAn monAn,String tenNhaHang,String diaChiNhaHang) {
        // Required empty public constructor
    }

    public AddCartFragment() {
        super();
    }

    public static AddCartFragment newInstance(MonAn monAn,String tenNhaHang,String diaChiNhaHang){
        AddCartFragment addCartFragment = new AddCartFragment(monAn,tenNhaHang,diaChiNhaHang);
        monAnChon = monAn;
        tenNhaHang_send = tenNhaHang;
        diaChiNhaHang_send = diaChiNhaHang;
      return addCartFragment;
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_cart,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseSQLite = new CreateDatabaseSQLite(getContext());
        imageViewMonAnCart = (ImageView) view.findViewById(R.id.imageMonAnCart);
        tvTenMonAnChon = (TextView) view.findViewById(R.id.tvTenMonAnCart);
        tvSoLuongChon = (TextView) view.findViewById(R.id.tvSoLuongMonAnChon);
        btnCongSoLuong = (Button) view.findViewById(R.id.btnCongSoLuong);
        btnTruSoLuong = (Button) view.findViewById(R.id.btnTruSoLuong);
        btnThemVaoGioHang = (Button) view.findViewById(R.id.btnThemVaoGioHang);
        tvTenMonAnChon.setText(monAnChon.getTenMon());
        Picasso
                .get()
                .load(monAnChon.getHinhAnh()).into(imageViewMonAnCart);
        btnTruSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(soLuongChon > 1){
                    soLuongChon --;
                    tvSoLuongChon.setText(soLuongChon+"");
                }
            }
        });
        btnCongSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soLuongChon ++;
                tvSoLuongChon.setText(soLuongChon+"");
            }
        });
        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddCartActivity.class);
                //
                MonAnCart monAnCart = new MonAnCart();
                monAnCart.setMaMon(monAnChon.getKeyID());
                monAnCart.setTenMon(monAnChon.getTenMon());
                monAnCart.setChiTiet(monAnChon.getMoTa());
                monAnCart.setHinhAnh(monAnChon.getHinhAnh());
                monAnCart.setGia(monAnChon.getGiaTien());
                monAnCart.setSoLuongChon(soLuongChon);
                monAnCart.setMaNhaHang(monAnChon.getIdNhaHang());
                monAnCart.setTenNhaHang(tenNhaHang_send);
                monAnCart.setDiaChiNhaHang(diaChiNhaHang_send);
                MonAnCart monAnCart_Query;
                monAnCart_Query = databaseSQLite.kiemTraTrungMaMonAn(monAnChon.getKeyID());
                System.out.println(monAnCart_Query);
                if(!(monAnCart_Query.getMaMon() == null)){
                    Toast.makeText(getContext(),"Trung Ma",Toast.LENGTH_LONG).show();
                    int soLuongUpdate = soLuongChon + monAnCart_Query.getSoLuongChon();
                    if(databaseSQLite.updateSoLuongMonAn(monAnChon.getKeyID(),soLuongUpdate)>0){
                        startActivity(intent);
                    }
                }
                else if(databaseSQLite.insertMonAn(monAnCart) > 0){
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
    public interface ItemClickListener{
    }
}