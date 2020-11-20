package com.nguyenvanhoang.foodapp.view.cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.nguyenvanhoang.foodapp.view.user.UserActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddCartFragment extends BottomSheetDialogFragment {
    private ImageView imageViewMonAnCart;
    private TextView tvTenMonAnChon,tvSoLuongChon;
    private Button btnCongSoLuong,btnTruSoLuong,btnThemVaoGioHang;
    private int soLuongChon = 1;
    private CreateDatabaseSQLite databaseSQLite;
    private FirebaseDatabase firebaseDatabase ;
    private static MonAn monAnChon;
    private static String tenNhaHang_send,diaChiNhaHang_send;
    private static double latNhaHang_send,longNhaHang_send;
    private static float soKm_send;
    public AddCartFragment(MonAn monAn,String tenNhaHang,String diaChiNhaHang,double latNhaHang,double longNhaHang,float soKm) {
        // Required empty public constructor
    }

    public AddCartFragment() {
        super();
    }

    public static AddCartFragment newInstance(MonAn monAn,String tenNhaHang,String diaChiNhaHang,double latNhaHang,double longNhaHang,float soKm){
        AddCartFragment addCartFragment = new AddCartFragment(monAn,tenNhaHang,diaChiNhaHang,latNhaHang,longNhaHang,soKm);
        monAnChon = monAn;
        tenNhaHang_send = tenNhaHang;
        diaChiNhaHang_send = diaChiNhaHang;
        latNhaHang_send = latNhaHang;
        longNhaHang_send = longNhaHang;
        soKm_send = soKm;
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
                //// kiem tra trung nha hang

                // thong bao thay doi nha hang
                if(checkCartTrungNhaHang()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn đã có 1 giỏ hàng ở 1 nhà hàng khác. \n" +
                            "Bạn có muốn thay đổi giỏ hàng không?");
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            System.out.println(monAnChon.getIdNhaHang());

                            List<MonAnCart> list = new ArrayList<>();
                            list = databaseSQLite.getAllMonAnCartByUser(UserActivity.Email_Login);
                            if(list.size() > 0){
                                if(databaseSQLite.deleteGioHang(list.get(0).getMaNhaHang(),UserActivity.Email_Login) > 0){
                                    themMonAnVaoGioHang();
                                }
                            }
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else{
                    themMonAnVaoGioHang();
                }
            }
        });
    }
    public boolean checkCartTrungNhaHang(){
        List<MonAnCart> list = new ArrayList<>();
        list = databaseSQLite.getAllMonAnCartByUser(UserActivity.Email_Login);
        for(MonAnCart cart : list){
            if( !monAnChon.getIdNhaHang().equals(cart.getMaNhaHang())){
                return true;
            }
        }
        return false;
    }
    public void themMonAnVaoGioHang(){
        Intent intent = new Intent(getActivity(),AddCartActivity.class);
        intent.putExtra("latNhaHang",latNhaHang_send);
        intent.putExtra("longNhaHang",longNhaHang_send);
        intent.putExtra("soKm",soKm_send);

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
        monAnCart.setLatNhaHang(latNhaHang_send);
        monAnCart.setLongNhaHang(longNhaHang_send);
        monAnCart.setSoKm(soKm_send);
        MonAnCart monAnCart_Query = null;
        monAnCart_Query = databaseSQLite.kiemTraTrungMaMonAn(monAnChon.getKeyID(),UserActivity.Email_Login);
        monAnCart.setMaUser(UserActivity.Email_Login);
        if(!(monAnCart_Query.getMaMon() == null)){
            int soLuongUpdate = soLuongChon + monAnCart_Query.getSoLuongChon();
            if(databaseSQLite.updateSoLuongMonAn(monAnChon.getKeyID(),soLuongUpdate,UserActivity.Email_Login) > 0){
                startActivity(intent);
            }
        }
        else if(databaseSQLite.insertMonAn(monAnCart) > 0){
            System.out.println("da them : "+monAnCart);
            startActivity(intent);
        }
    }
}