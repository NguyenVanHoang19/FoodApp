package com.nguyenvanhoang.foodapp.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.dao.DonHangDAO;
import com.nguyenvanhoang.foodapp.entities.DonHang;
import com.nguyenvanhoang.foodapp.entities.NhaHang;
import com.nguyenvanhoang.foodapp.interface_dao.DonHang_Interface;

import java.text.DecimalFormat;
import java.util.List;

public class RecyclerViewLichSuDatHang extends RecyclerView.Adapter<RecyclerViewLichSuDatHang.RecyclerViewHolder> {
    private List<DonHang> donHangList;
    private Context context;
    private FirebaseDatabase firebaseDatabase ;

    public RecyclerViewLichSuDatHang(List<DonHang> donHangList, Context context) {
        this.donHangList = donHangList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_lichsudathang,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String ngayDatHang = donHangList.get(position).getNgayDat().toString();
        double soTien = donHangList.get(position).getTongTien();
        firebaseDatabase = FirebaseDatabase.getInstance();
        Query query = firebaseDatabase.getReference().child("nhahang").child(donHangList.get(position).getKeyIdNhaHang());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                NhaHang nhaHang = dataSnapshot.getValue(NhaHang.class);
                holder.tvTenNhaHang.setText(nhaHang.getTenNhaHang());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        holder.tvNgayDatHang.setText(ngayDatHang);
        holder.tvSoTien.setText(decimalFormat.format(soTien) + " VNĐ");
        String trangThaiHuyDon = donHangList.get(position).getTrangThaiHuyDonHang();
        if(trangThaiHuyDon.equals("false"))
            holder.tvTrangThai.setText("Đơn hàng đang xác nhận");
        else if(trangThaiHuyDon.equals("true"))
            holder.tvTrangThai.setText("Đã hủy");

    }

    @Override
    public int getItemCount() {
        return donHangList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNgayDatHang,tvTenNhaHang,tvSoLuongMon,tvSoTien,tvTrangThai;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNgayDatHang = (TextView) itemView.findViewById(R.id.tvNgayDatHang);
            tvTenNhaHang = (TextView) itemView.findViewById(R.id.tvTenNhaHang);
            tvSoLuongMon = (TextView) itemView.findViewById(R.id.tvSoLuongMon);
            tvSoTien = (TextView) itemView.findViewById(R.id.tvSoTien);
            tvTrangThai = (TextView) itemView.findViewById(R.id.tvTrangThai);
        }
    }
}
