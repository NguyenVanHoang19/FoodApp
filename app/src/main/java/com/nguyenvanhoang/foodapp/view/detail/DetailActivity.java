package com.nguyenvanhoang.foodapp.view.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoang.foodapp.MapsActivity;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.apdapter.RecyclerViewDanhSachMonAnCuaNhaHang;
import com.nguyenvanhoang.foodapp.dao.MonAnDAO;
import com.nguyenvanhoang.foodapp.entities.LoaiMonAn;
import com.nguyenvanhoang.foodapp.entities.MonAn;
import com.nguyenvanhoang.foodapp.entities.NhaHang;
import com.nguyenvanhoang.foodapp.interface_dao.MonAn_Interface;
import com.nguyenvanhoang.foodapp.view.cart.AddCartActivity;
import com.nguyenvanhoang.foodapp.view.cart.AddCartFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private Toolbar toolbar ;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView hinhAnhThumb;
    private Button hinhAnhGoogleMaps;
    private TextView tvTenNhaHang;
    private TextView tvMoTaNhaHang;
    private TextView tvDiaChiDetail;
    private TextView tvGioMoCua;
    private RecyclerView recyclerViewDanhSachMonAnDetail;
    private ValueEventListener databaseReference ;
    private FirebaseDatabase firebaseDatabase ;
    private String tenNhaHang_SendCart = "";
    private String diaChiNhaHang_SendCart = "";
    List<MonAn> monAnList = new ArrayList<MonAn>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        hinhAnhThumb = (ImageView) findViewById(R.id.mealThumb);
        tvTenNhaHang = (TextView) findViewById(R.id.tvTenNhaHang);
        tvDiaChiDetail = (TextView) findViewById(R.id.tvDiaChiDetail);
        tvMoTaNhaHang = (TextView) findViewById(R.id.tvMoTaNhaHang);
        tvGioMoCua = (TextView) findViewById(R.id.tvGioMoCua);
        hinhAnhGoogleMaps = (Button) findViewById(R.id.imageViewMaps);
        recyclerViewDanhSachMonAnDetail = (RecyclerView) findViewById(R.id.recyclerViewDanhSachMonAnDetail);
        setupActionBar();
        Intent intent =getIntent();
        String idNhaHang = intent.getStringExtra("idNhaHang");
        RecyclerViewDanhSachMonAnCuaNhaHang adapter = new RecyclerViewDanhSachMonAnCuaNhaHang(monAnList,this);
        recyclerViewDanhSachMonAnDetail.setLayoutManager(new GridLayoutManager(this,1));
        recyclerViewDanhSachMonAnDetail.setClipToPadding(false);
        recyclerViewDanhSachMonAnDetail.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerViewDanhSachMonAnCuaNhaHang.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                AddCartFragment addCartFragment = AddCartFragment.newInstance(monAnList.get(position),tenNhaHang_SendCart,diaChiNhaHang_SendCart);
                addCartFragment.show(getSupportFragmentManager(),"Thêm vào giỏ hàng");
            }
        });
        hinhAnhGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent1);
            }
        });
        //
        Query query = firebaseDatabase.getReference().child("nhahang").child(idNhaHang);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                NhaHang nhaHang = dataSnapshot.getValue(NhaHang.class);
                tvTenNhaHang.setText(nhaHang.getTenNhaHang());
                tvMoTaNhaHang.setText(nhaHang.getGioiThieu()+ " ");
                tenNhaHang_SendCart = nhaHang.getTenNhaHang();
                diaChiNhaHang_SendCart = nhaHang.getDiaChi();
                tvDiaChiDetail.setText(nhaHang.getDiaChi() + " ");
                // them gio mo cua
                Picasso.get().load(nhaHang.getHinhAnh()).placeholder(R.drawable.shadow_bottom_to_top).into(hinhAnhThumb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        MonAn_Interface monAn_interface = new MonAnDAO();
        databaseReference = monAn_interface.getAllMonAn().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                findViewById(R.id.progressBarDanhSachMonAn).setVisibility(View.GONE);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    MonAn monAn = snapshot.getValue(MonAn.class);
                    if(monAn.getIdNhaHang().equals(idNhaHang)){
                        monAnList.add(monAn);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void anhXaView(){

    }
    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
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