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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.entities.MonAn;
import com.nguyenvanhoang.foodapp.entities.NhaHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class RecyclerViewDanhSachMonAnCuaNhaHang extends RecyclerView.Adapter<RecyclerViewDanhSachMonAnCuaNhaHang.RecyclerViewHolder> {

    private List<MonAn> monAnList;
    private Context context;
    private ValueEventListener databaseReference ;
    private FirebaseDatabase firebaseDatabase ;
    private static ClickListener clickListener;
    public RecyclerViewDanhSachMonAnCuaNhaHang(List<MonAn> monAnList, Context context) {
        this.monAnList = monAnList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerViewDanhSachMonAnCuaNhaHang.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_meal_detail,
                parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewDanhSachMonAnCuaNhaHang.RecyclerViewHolder holder, int position) {
        String hinhAnh = monAnList.get(position).getHinhAnh();
        Picasso.get().load(hinhAnh).placeholder(R.drawable.shadow_bottom_to_top).into(holder.hinhAnhMonAn);
        String tenMon = monAnList.get(position).getTenMon();
        holder.textViewTenMonAn.setText(tenMon);
        String chiTiet = monAnList.get(position).getMoTa();
        holder.textViewChiTiet.setText(chiTiet.toString());
        double gia = monAnList.get(position).getGiaTien();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String giaFormat = decimalFormat.format(gia);
        holder.textViewGia.setText(giaFormat + "VNĐ");
        holder.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(holder.,tenMon,Toast.LENGTH_LONG).show();
                System.out.println("da kich");
            }
        });
    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView hinhAnhMonAn;
        private TextView textViewTenMonAn,textViewChiTiet,textViewGia;
        private Button btnThem;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhAnhMonAn = (ImageView) itemView.findViewById(R.id.mealThumb);
            textViewTenMonAn = (TextView) itemView.findViewById(R.id.mealName);
            textViewChiTiet = (TextView) itemView.findViewById(R.id.tvChiTiet);
            textViewGia = (TextView) itemView.findViewById(R.id.tvGiaTien);
            btnThem = (Button) itemView.findViewById(R.id.btnThem);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            clickListener.onClick(view,getAdapterPosition());
        }
    }
    public void setOnItemClickListener(ClickListener clickListener){
        RecyclerViewDanhSachMonAnCuaNhaHang.clickListener = clickListener;
    }
    public interface ClickListener {
        void onClick(View view, int position);
    }
}
