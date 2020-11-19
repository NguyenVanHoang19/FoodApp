package com.nguyenvanhoang.foodapp.view.category;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.data.DataBufferObserver;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.apdapter.RecyclerViewMonAnByLoaiMon;
import com.nguyenvanhoang.foodapp.dao.MonAnDAO;
import com.nguyenvanhoang.foodapp.entities.MonAn;
import com.nguyenvanhoang.foodapp.interface_dao.MonAn_Interface;
import com.nguyenvanhoang.foodapp.view.detail.DetailActivity;
import com.nguyenvanhoang.foodapp.view.home.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class CategoryFragment extends Fragment{
    private  RecyclerView recyclerView ;
    private  ProgressBar progressBar;
    private ImageView imageViewCategory,imageViewCategoryBackgroud;
    private TextView textViewCategory,textViewTenNhaHang,textViewDiaChi;
    private ValueEventListener databaseReference ;
    private FirebaseDatabase firebaseDatabase ;
    private List<MonAn> monAnTheoLoaiList = new ArrayList<MonAn>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view =inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        imageViewCategory = (ImageView) view.findViewById(R.id.imageCategory);
        imageViewCategoryBackgroud =  (ImageView) view.findViewById(R.id.imageCategoryBg);
        textViewCategory = (TextView) view.findViewById(R.id.textCategory);
        textViewTenNhaHang = (TextView) view.findViewById(R.id.tvTenNhaHang);
        textViewDiaChi = (TextView) view.findViewById(R.id.tvDiaChiNhaHang);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null){
            textViewCategory.setText(getArguments().getString("tenLoaiMon"));
            Picasso.get()
                        .load(getArguments().getString("hinhAnh"))
                        .into(imageViewCategory);
            Picasso.get()
                    .load(getArguments().getString("hinhAnh"))
                    .into(imageViewCategoryBackgroud);
            firebaseDatabase = FirebaseDatabase.getInstance();

            Query query = firebaseDatabase.getReference().child("monan").orderByChild("idLoaiMon").equalTo(getArguments().getString("keyID"));
            showLoading();
            RecyclerViewMonAnByLoaiMon adapter = new RecyclerViewMonAnByLoaiMon(monAnTheoLoaiList,getActivity());
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
            recyclerView.setClipToPadding(false);
            recyclerView.setAdapter(adapter);
            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    hideLoading();
                        MonAn monAn = dataSnapshot.getValue(MonAn.class);
                        System.out.println(monAn.getTenMon());
                        if(!monAnTheoLoaiList.contains(monAn)){
                            monAnTheoLoaiList.add(monAn);
                        }
                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListener(new RecyclerViewMonAnByLoaiMon.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Intent intent = new Intent(getContext(), DetailActivity.class);
                                System.out.println(monAnTheoLoaiList.size());
                                intent.putExtra("idNhaHang",monAnTheoLoaiList.get(position).getIdNhaHang());
                                startActivity(intent);
                            }
                        });
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
    }

    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }
    public  void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    public float  tinhKhoangCach(double latHienTai,double longHienTai,double latNhaHang,double longNhaHang){
        float [] result = new float[2];
        Location.distanceBetween(latHienTai,longHienTai,latNhaHang,longNhaHang,result);
        float ketQuaMet = result[0];
        float ketQuaKm = ketQuaMet /1000;
        return ketQuaKm;
    }

}