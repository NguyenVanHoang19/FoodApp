package com.nguyenvanhoang.foodapp.view.user;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.view.cart.AddCartActivity;

public class UserActivity extends AppCompatActivity {
    private Toolbar toolbar ;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Button btnDangNhap,btnDangKy,btnDangXuat,btnGioHang;
    public static boolean TRANG_THAI_DANG_NHAP = false;
    public static String Email_Login = "false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangXuat = (Button) findViewById(R.id.btnDangXuat);
        btnGioHang = (Button) findViewById(R.id.btnGioHang);
        btnDangXuat.setVisibility(View.GONE);
        initActionBar();
        if(TRANG_THAI_DANG_NHAP){
            btnDangNhap.setVisibility(View.GONE);
            btnDangKy.setVisibility(View.GONE);
            btnDangXuat.setVisibility(View.VISIBLE);
            setTitle(Email_Login);
        }
        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(), AddCartActivity.class));
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDangXuat.setVisibility(View.GONE);
                btnDangNhap.setVisibility(View.VISIBLE);
                btnDangKy.setVisibility(View.VISIBLE);
                TRANG_THAI_DANG_NHAP = false;
                Email_Login = "false";
                setTitle("Cá nhân");
            }
        });

    }
    private void initActionBar() {
        setSupportActionBar(toolbar);
        setTitle("Cá nhân");
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