package com.nguyenvanhoang.foodapp.apdapter;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.entities.DiaChi;
import com.nguyenvanhoang.foodapp.entities.MonAn;
import com.nguyenvanhoang.foodapp.entities.NhaHang;
import com.nguyenvanhoang.foodapp.view.home.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class RecyclerViewMonAnByLoaiMon extends RecyclerView.Adapter<RecyclerViewMonAnByLoaiMon.RecyclerViewHolder> {
    private List<MonAn> monAnList;
    private Context context;
    private ValueEventListener databaseReference ;
    private FirebaseDatabase firebaseDatabase ;
    private static ClickListener clickListener;

    public RecyclerViewMonAnByLoaiMon(List<MonAn> monAnList, Context context) {
        this.monAnList = monAnList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewMonAnByLoaiMon.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_meal,
                                                            parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            String hinhAnh = monAnList.get(position).getHinhAnh();
            Picasso.get().load(hinhAnh).placeholder(R.drawable.shadow_bottom_to_top).into(holder.doAnHinhAnh);
            String tenMon = monAnList.get(position).getTenMon();
            holder.textViewName.setText(tenMon);
            // set ten va dia chi nha hang co mon an loai nay
            firebaseDatabase = FirebaseDatabase.getInstance();
            Query query = firebaseDatabase.getReference().child("nhahang").child(monAnList.get(position).getIdNhaHang());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    NhaHang nhaHang = dataSnapshot.getValue(NhaHang.class);
                    holder.textViewTenNhaHang.setText(nhaHang.getTenNhaHang());
                    holder.textViewDiaChi.setText(nhaHang.getDiaChi().getFullDiaChi());
                    float [] result = new float[2];
                    Location.distanceBetween(MainActivity.LATITUDE_CURRENT,MainActivity.LONGTITUDE_CURRENT,nhaHang.getDiaChi().getLatitude(),nhaHang.getDiaChi().getLongtitude(),result);
                    float ketQuaMet = result[0];
                    float ketQuaKm = ketQuaMet /1000;
                    holder.textViewKhoangCach.setText(String.format(Locale.US,"%.2f km",ketQuaKm));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView doAnHinhAnh ;
        private TextView textViewName,textViewTenNhaHang,textViewDiaChi,textViewKhoangCach;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            doAnHinhAnh = (ImageView) itemView.findViewById(R.id.mealThumb);
            textViewName = (TextView) itemView.findViewById(R.id.mealName);
            textViewTenNhaHang = (TextView) itemView.findViewById(R.id.tvTenNhaHang);
            textViewDiaChi = (TextView) itemView.findViewById(R.id.tvDiaChiNhaHang);
            textViewKhoangCach = (TextView) itemView.findViewById(R.id.tvKhoangCach);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view,getAdapterPosition());
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewMonAnByLoaiMon.clickListener = clickListener;
    }
    public interface ClickListener {
        void onClick(View view, int position);
    }
}

