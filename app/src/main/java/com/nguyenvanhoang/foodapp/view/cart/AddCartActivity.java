package com.nguyenvanhoang.foodapp.view.cart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.apdapter.RecyclerViewDanhSachMonAnThanhToan;
import com.nguyenvanhoang.foodapp.database.CreateDatabaseSQLite;
import com.nguyenvanhoang.foodapp.database.MonAnCart;
import com.nguyenvanhoang.foodapp.entities.MonAn;
import com.nguyenvanhoang.foodapp.interface_send_data.OnClick_MonAnThanhToan;
import com.nguyenvanhoang.foodapp.view.user.LoginActivity;
import com.nguyenvanhoang.foodapp.view.user.UserActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AddCartActivity extends AppCompatActivity implements OnClick_MonAnThanhToan {
    private Toolbar toolbar ;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView recyclerViewDanhSachMonAnThanhToan;
    private TextView tvGiaMonAnGioHang,tvTongTienThanhToan,tvPhiGiaoHang,tvTenNhaHang;
    private Button btnTongThanhToan,btnThemMon;
    private final double PHI_GIAO_HANG = 12000;
    public static RecyclerViewDanhSachMonAnThanhToan adapter;
    public static List<MonAnCart> monAnCartList = new ArrayList<MonAnCart>();
    public static CreateDatabaseSQLite databaseSQLite;
    private OnClick_MonAnThanhToan onClick_monAnBtnThanhToan;
    private int soLuongChon= 1;
    public static double giaTien = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        recyclerViewDanhSachMonAnThanhToan = (RecyclerView) findViewById(R.id.recyclerViewDanhSachMonAnGioHang);
        tvGiaMonAnGioHang = (TextView) findViewById(R.id.tvGiaMonAnGioHang);
        tvTongTienThanhToan = (TextView) findViewById(R.id.tvTongTienThanhToan);
        tvTenNhaHang = (TextView) findViewById(R.id.tvTenNhaHang);
        tvPhiGiaoHang = (TextView) findViewById(R.id.tvPhiGiaoHang);
        btnTongThanhToan = (Button) findViewById(R.id.btnTongThanhToan);
        btnThemMon = (Button) findViewById(R.id.btnThemMon);
        tvPhiGiaoHang.setText(decimalFormat.format(PHI_GIAO_HANG) + " VNĐ");
        setupActionBar();
        databaseSQLite = new CreateDatabaseSQLite(getApplicationContext());
        if(UserActivity.TRANG_THAI_DANG_NHAP == true){
            monAnCartList =  databaseSQLite.getAllMonAnCartByUser(UserActivity.Email_Login);
        }
        else
            monAnCartList = databaseSQLite.getAllMonAnCartNoUser("false");
        double tongGiaTienMonAn =  0;
        if(monAnCartList.size() > 0){
            for(MonAnCart monAnCart : monAnCartList){
                System.out.println("ma : "+monAnCart.getMaUser());
                System.out.println("ma : "+monAnCart.getTenNhaHang());
                System.out.println(monAnCart);
                tongGiaTienMonAn += monAnCart.getGia() * monAnCart.getSoLuongChon();
            }
            tvGiaMonAnGioHang.setText(decimalFormat.format(tongGiaTienMonAn) + " VNĐ");
            tvTongTienThanhToan.setText(decimalFormat.format(tongGiaTienMonAn + PHI_GIAO_HANG) + " VNĐ");
            tvTenNhaHang.setText(monAnCartList.get(0).getTenNhaHang());
        }
        adapter = new RecyclerViewDanhSachMonAnThanhToan(monAnCartList,this);
        recyclerViewDanhSachMonAnThanhToan.setLayoutManager(new GridLayoutManager(this,1));
        recyclerViewDanhSachMonAnThanhToan.setClipToPadding(false);
        recyclerViewDanhSachMonAnThanhToan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnTongThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserActivity.TRANG_THAI_DANG_NHAP == true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddCartActivity.this);
                    builder.setMessage("Đặt hàng thành công!!!");
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddCartActivity.this);
                    builder.setMessage("Bạn phải đăng nhập để thực hiện chức năng đặt hàng");
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(AddCartActivity.this, LoginActivity.class));
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
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

    @Override
    public void onClickBtn(List<MonAnCart> monAnCartList, int position) {
        int tongGiaTienMonAn1 = 0;
        databaseSQLite = new CreateDatabaseSQLite(getApplicationContext());
        for(MonAnCart monAnCart1 : databaseSQLite.getAllMonAn()){
            tongGiaTienMonAn1 += monAnCart1.getGia() * monAnCart1.getSoLuongChon();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGiaMonAnGioHang.setText(decimalFormat.format(tongGiaTienMonAn1) + " VNĐ");
        if(tongGiaTienMonAn1 == 0)
            tvTongTienThanhToan.setText(decimalFormat.format(tongGiaTienMonAn1) + " VNĐ");
        else
            tvTongTienThanhToan.setText(decimalFormat.format(tongGiaTienMonAn1 + PHI_GIAO_HANG) + " VNĐ");
    }
}