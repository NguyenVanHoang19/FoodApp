package com.nguyenvanhoang.foodapp.view.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.apdapter.RecyclerViewDanhSachMonAnThanhToan;
import com.nguyenvanhoang.foodapp.database.CreateDatabaseSQLite;
import com.nguyenvanhoang.foodapp.database.MonAnCart;
import com.nguyenvanhoang.foodapp.entities.MonAn;

import java.util.ArrayList;
import java.util.List;

public class AddCartActivity extends AppCompatActivity {
    private Toolbar toolbar ;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView recyclerViewDanhSachMonAnThanhToan;
    public static RecyclerViewDanhSachMonAnThanhToan adapter;
    public static List<MonAn> monAnList = new ArrayList<MonAn>();
    public static List<MonAnCart> monAnCartList = new ArrayList<MonAnCart>();
    private CreateDatabaseSQLite databaseSQLite;
    private int soLuongChon= 1;
    public static double giaTien = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        recyclerViewDanhSachMonAnThanhToan = (RecyclerView) findViewById(R.id.recyclerViewDanhSachMonAnGioHang);
        setupActionBar();
        databaseSQLite = new CreateDatabaseSQLite(getApplicationContext());
        monAnCartList = databaseSQLite.getAllMonAn();
        if(monAnCartList.size() > 0){

        }
//        Intent  intent = getIntent();
//        MonAn monAn = (MonAn) intent.getSerializableExtra("monAn");
//        System.out.println(soLuongChon);
//        if(monAnList.contains(monAn)){
//            soLuongChon =  intent.getIntExtra("soLuongChon",0);
//            System.out.println(soLuongChon);
//            giaTien += monAn.getGiaTien() * soLuongChon;
//            monAn.setGiaTien(giaTien);
//            System.out.println(monAn.getGiaTien());
//            monAnList.set(monAnList.indexOf(monAn),monAn);
//        }
//        else {
//            giaTien = 0;
//            soLuongChon = intent.getIntExtra("soLuongChon",0);
//            giaTien += monAn.getGiaTien() * soLuongChon;
//            System.out.println(giaTien);
//            monAn.setGiaTien(giaTien);
//            monAnList.add(monAn);
//        }
//        System.out.println(monAnList.get(0).getIdLoaiMon());
        adapter = new RecyclerViewDanhSachMonAnThanhToan(monAnCartList,this);
        recyclerViewDanhSachMonAnThanhToan.setLayoutManager(new GridLayoutManager(this,1));
        recyclerViewDanhSachMonAnThanhToan.setClipToPadding(false);
        recyclerViewDanhSachMonAnThanhToan.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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