package com.nguyenvanhoang.foodapp.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.entities.LoaiMonAn;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.RecyclerViewHolder> {
    private List<LoaiMonAn> loaiMonAns ;
    private Context context;
    private static ClickListener clickListener;
    //private int idLayout;

    public RecyclerViewHomeAdapter(List<LoaiMonAn> loaiMonAns, Context context) {
        this.loaiMonAns = loaiMonAns;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_category,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapter.RecyclerViewHolder holder, int position) {
        String hinhAnh = loaiMonAns.get(position).getHinhAnh();
        System.out.println(hinhAnh);
        Picasso.get().load(hinhAnh).placeholder(R.drawable.ic_circle).into(holder.categoryThumb);
        String tenLoai = loaiMonAns.get(position).getTenLoaiMon();
        holder.categoryName.setText(tenLoai);

    }

    @Override
    public int getItemCount() {
        return loaiMonAns.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView categoryThumb;
        TextView categoryName;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryThumb = (ImageView) itemView.findViewById(R.id.categoryThumb);
            categoryName = (TextView) itemView.findViewById(R.id.categoryName);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewHomeAdapter.clickListener = clickListener;
    }
    public interface ClickListener {
        void onClick(View view, int position);
    }
}
