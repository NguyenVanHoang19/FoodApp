package com.nguyenvanhoang.foodapp.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.database.CreateDatabaseSQLite;
import com.nguyenvanhoang.foodapp.database.MonAnCart;
import com.nguyenvanhoang.foodapp.entities.MonAn;
import com.nguyenvanhoang.foodapp.view.cart.AddCartActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class RecyclerViewDanhSachMonAnThanhToan extends RecyclerView.Adapter<RecyclerViewDanhSachMonAnThanhToan.RecyclerViewHolder> {
    private List<MonAnCart> monAnList;
    private Context context;
    private double gia = 1;
    private int soLuong = 1;
    private   MonAnCart monAnCart = new MonAnCart();
    public RecyclerViewDanhSachMonAnThanhToan(List<MonAnCart> monAnList, Context context) {
        this.monAnList = monAnList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewDanhSachMonAnThanhToan.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_cart,parent,false);
        return new RecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        String hinhAnh = monAnList.get(position).getHinhAnh();
        Picasso.get().load(hinhAnh).placeholder(R.drawable.shadow_bottom_to_top).into(holder.doAnHinhAnh);
        String tenMon = monAnList.get(position).getTenMon();
        holder.mealName.setText(tenMon);
        String chiTiet = monAnList.get(position).getChiTiet();
        holder.tvChiTiet.setText(chiTiet.toString());
        monAnCart = holder.databaseSQLite.timMonAnTheoMa(monAnList.get(position).getMaMon());
        soLuong = monAnList.get(position).getSoLuongChon();
        System.out.println("soluonglucdau: "+soLuong +" " + monAnCart.getMaMon());
        gia = monAnList.get(position).getGia();
        gia = gia*soLuong;
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String giaFormat = decimalFormat.format(gia);
        holder.tvGiaTien.setText(giaFormat + " VNĐ");
        holder.tvSoLuongMonAnChonThanhToan.setText(soLuong + "");

      // int soLuong_CartDB = monAnList.get(position).getSoLuongChon();
        holder.btnCongSoLuongThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuongClick = 1;
                soLuongClick = holder.databaseSQLite.timMonAnTheoMa(monAnList.get(position).getMaMon()).getSoLuongChon() + 1;
                System.out.println("soluonglucsautang: "+soLuongClick);
                if(holder.databaseSQLite.updateSoLuongMonAn(monAnList.get(position).getMaMon(),soLuongClick) > 0){
                    System.out.println("da cap nhat thanh cong! " +monAnCart.getMaMon() + " " + soLuongClick);
                }
                monAnCart = holder.databaseSQLite.timMonAnTheoMa(monAnList.get(position).getMaMon());
                System.out.println(monAnCart.getSoLuongChon() + " ma " + monAnList.get(position).getMaMon());
                int soLuongUpdate = monAnCart.getSoLuongChon();
                holder.tvSoLuongMonAnChonThanhToan.setText(soLuongUpdate+"");
                gia = monAnCart.getGia()*soLuongUpdate;
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                String giaFormat = decimalFormat.format(gia);
                holder.tvGiaTien.setText(giaFormat + " VNĐ");
               // AddCartActivity.adapter.notifyDataSetChanged();
            }
        });
        holder.btnTruSoLuongThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(soLuong > 0){
                    soLuong --;
                    if(soLuong == 0){
                        //AddCartActivity.monAnList.remove(position);
                        holder.databaseSQLite.deleteMonAn(monAnList.get(position).getMaMon());
                        AddCartActivity.adapter.notifyDataSetChanged();
                    }
                    monAnCart = holder.databaseSQLite.timMonAnTheoMa(monAnList.get(position).getMaMon());
                    int soLuongChonUpdate = soLuong + monAnCart.getSoLuongChon();
                    holder.databaseSQLite.updateSoLuongMonAn(monAnCart.getMaMon(),soLuongChonUpdate);
                    holder.tvSoLuongMonAnChonThanhToan.setText(soLuongChonUpdate+"");
                    gia = gia*soLuongChonUpdate;
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    String giaFormat = decimalFormat.format(gia);
                    holder.tvGiaTien.setText(giaFormat + " VNĐ");
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder  {
        private ImageView doAnHinhAnh;
        private Button btnTruSoLuongThanhToan,btnCongSoLuongThanhToan;
        private TextView tvSoLuongMonAnChonThanhToan;
        private TextView mealName,tvChiTiet,tvGiaTien;
        private CreateDatabaseSQLite databaseSQLite;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            doAnHinhAnh = (ImageView) itemView.findViewById(R.id.mealThumb);
            btnTruSoLuongThanhToan = (Button) itemView.findViewById(R.id.btnTruSoLuongThanhToan);
            btnCongSoLuongThanhToan = (Button) itemView.findViewById(R.id.btnCongSoLuongThanhToan);
            tvSoLuongMonAnChonThanhToan = (TextView) itemView.findViewById(R.id.tvSoLuongMonAnChonThanhToan);
            mealName = (TextView) itemView.findViewById(R.id.mealName);
            tvChiTiet =(TextView) itemView.findViewById(R.id.tvChiTiet);
            tvGiaTien = (TextView) itemView.findViewById(R.id.tvGiaTien);
            databaseSQLite = new CreateDatabaseSQLite(itemView.getContext());
        }
    }
}
