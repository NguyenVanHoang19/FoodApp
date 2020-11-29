package com.nguyenvanhoang.foodapp.view.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.apdapter.RecyclerViewLichSuDatHang;
import com.nguyenvanhoang.foodapp.dao.DonHangDAO;
import com.nguyenvanhoang.foodapp.entities.DonHang;
import com.nguyenvanhoang.foodapp.interface_dao.DonHang_Interface;
import com.nguyenvanhoang.foodapp.view.user.UserActivity;

import java.util.ArrayList;
import java.util.List;

public class LichSuDatHangActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Toolbar toolbar ;
    private List<DonHang> donHangList = new ArrayList<DonHang>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_dat_hang);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewLichSuDatHang);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Lịch sử đặt hàng");
        setTitleColor(R.color.colorPrimary);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        RecyclerViewLichSuDatHang adapter = new RecyclerViewLichSuDatHang(donHangList,LichSuDatHangActivity.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        DonHang_Interface donHang_interface = new DonHangDAO();
        donHang_interface.getAllDonHang().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    DonHang donHang = snapshot.getValue(DonHang.class);
                    if(donHang.getKeyIdUser().equals(UserActivity.Email_Login)){
                        System.out.println(donHang.getNgayDat());
                        donHangList.add(donHang);
                        adapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setupActionBar() {
        setSupportActionBar(toolbar);
        setTitle("Giỏ hàng");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}