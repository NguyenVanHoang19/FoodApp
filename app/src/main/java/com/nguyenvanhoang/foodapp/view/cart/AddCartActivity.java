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
import com.nguyenvanhoang.foodapp.dao.ChiTietDonHangDAO;
import com.nguyenvanhoang.foodapp.dao.DonHangDAO;
import com.nguyenvanhoang.foodapp.database.CreateDatabaseSQLite;
import com.nguyenvanhoang.foodapp.database.MonAnCart;
import com.nguyenvanhoang.foodapp.entities.ChiTietDonHang;
import com.nguyenvanhoang.foodapp.entities.DonHang;
import com.nguyenvanhoang.foodapp.entities.MonAn;
import com.nguyenvanhoang.foodapp.entities.NhaHang;
import com.nguyenvanhoang.foodapp.interface_dao.ChiTietDonHang_Interface;
import com.nguyenvanhoang.foodapp.interface_dao.DonHang_Interface;
import com.nguyenvanhoang.foodapp.interface_send_data.OnClick_MonAnThanhToan;
import com.nguyenvanhoang.foodapp.view.home.MainActivity;
import com.nguyenvanhoang.foodapp.view.user.LoginActivity;
import com.nguyenvanhoang.foodapp.view.user.UserActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AddCartActivity extends AppCompatActivity implements OnClick_MonAnThanhToan {
    private Toolbar toolbar ;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView recyclerViewDanhSachMonAnThanhToan;
    private TextView tvGiaMonAnGioHang,tvTongTienThanhToan,tvPhiGiaoHang,tvTenNhaHang,tvDiaChiGiaoHangDetail;
    private Button btnTongThanhToan,btnThemMon;
    private final double PHI_GIAO_HANG = 12000;
    public static RecyclerViewDanhSachMonAnThanhToan adapter;
    public static List<MonAnCart> monAnCartList = new ArrayList<MonAnCart>();
    public static CreateDatabaseSQLite databaseSQLite;
    private OnClick_MonAnThanhToan onClick_monAnBtnThanhToan;
    public static boolean TRANG_THAI_CLICK_BUTTON_THANHTOAN_CHUA_LOGIN = false;
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
        tvDiaChiGiaoHangDetail = (TextView) findViewById(R.id.tvDiaChiGiaoHangDetail);
        tvPhiGiaoHang.setText(decimalFormat.format(PHI_GIAO_HANG) + " VNĐ");
        setupActionBar();
        databaseSQLite = new CreateDatabaseSQLite(getApplicationContext());
        monAnCartList =  databaseSQLite.getAllMonAnCartByUser(UserActivity.Email_Login);
        if(monAnCartList.size() < 1 ){
            btnTongThanhToan.setEnabled(false);
        }
        double tongGiaTienMonAn =  0;
        if(monAnCartList.size() > 0){
            btnTongThanhToan.setEnabled(true);
            for(MonAnCart monAnCart : monAnCartList){
                System.out.println("ma : "+monAnCart.getMaUser());
                System.out.println("ma : "+monAnCart.getTenNhaHang());
                System.out.println(monAnCart);
                tongGiaTienMonAn += monAnCart.getGia() * monAnCart.getSoLuongChon();
            }
            tvGiaMonAnGioHang.setText(decimalFormat.format(tongGiaTienMonAn) + " VNĐ");
            tvTongTienThanhToan.setText(decimalFormat.format(tongGiaTienMonAn + PHI_GIAO_HANG) + " VNĐ");
            tvTenNhaHang.setText(monAnCartList.get(0).getTenNhaHang());
            tvDiaChiGiaoHangDetail.setText(MainActivity.DIACHIHIENTAI+"");
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
                    //
                    if(monAnCartList.size() > 0){
                        luuThanhToan(monAnCartList.get(0).getMaNhaHang(),UserActivity.Email_Login);
                        databaseSQLite.deleteGioHang(monAnCartList.get(0).getMaNhaHang(),UserActivity.Email_Login);
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddCartActivity.this);
                    builder.setMessage("Đặt hàng thành công!!!");
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            TRANG_THAI_CLICK_BUTTON_THANHTOAN_CHUA_LOGIN = false;
                            dialogInterface.dismiss();
                            monAnCartList.removeAll(monAnCartList);
                            adapter.notifyDataSetChanged();
                            btnTongThanhToan.setEnabled(false);
                            tvGiaMonAnGioHang.setText("0 VNĐ");
                            tvTongTienThanhToan.setText("0 VNĐ");
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
                            TRANG_THAI_CLICK_BUTTON_THANHTOAN_CHUA_LOGIN = true;
                            Intent intent = new Intent(AddCartActivity.this,LoginActivity.class);
                            if(monAnCartList.size() > 0){
                                intent.putExtra("idNhaHang",monAnCartList.get(0).getMaNhaHang());
                            }
                            startActivity(intent);
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
    public void onClickBtn(int position) {
        int tongGiaTienMonAn1 = 0;
        databaseSQLite = new CreateDatabaseSQLite(getApplicationContext());
        List<MonAnCart> list = new ArrayList<>();
        list =  databaseSQLite.getAllMonAnCartByUser(UserActivity.Email_Login);
        if(list.size()  == 0 ){
            btnTongThanhToan.setEnabled(false);
        }
        for(MonAnCart monAnCart1 : list){
            tongGiaTienMonAn1 += monAnCart1.getGia() * monAnCart1.getSoLuongChon();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGiaMonAnGioHang.setText(decimalFormat.format(tongGiaTienMonAn1) + " VNĐ");
        if(tongGiaTienMonAn1 == 0)
            tvTongTienThanhToan.setText(decimalFormat.format(tongGiaTienMonAn1) + " VNĐ");
        else
            tvTongTienThanhToan.setText(decimalFormat.format(tongGiaTienMonAn1 + PHI_GIAO_HANG) + " VNĐ");
    }
    public void luuThanhToan(String idNhaHang,String idUser ){
        DonHang donHang = new DonHang();
        donHang.setKeyIdNhaHang(idNhaHang);
        donHang.setKeyIdUser(idUser);
        donHang.setGhiChu("3 Bò Nướng thượng hạng");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        donHang.setNgayDat(simpleDateFormat.format(date));
        donHang.setTrangThaiXacNhanDonHang("false");
        donHang.setTrangThaiDaGiaoHang("false");
        DonHang_Interface donHang_interface = new DonHangDAO();
        donHang_interface.addDonHang(donHang);
        // chi tiet don hang
        ChiTietDonHang_Interface chiTietDonHang_interface = new ChiTietDonHangDAO();
        for(MonAnCart monAnCart :  databaseSQLite.getAllMonAnCartByUser(UserActivity.Email_Login)){
            ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
            chiTietDonHang.setIdMon(monAnCart.getMaMon());
            chiTietDonHang.setSoLuong(monAnCart.getSoLuongChon());
            chiTietDonHang.setGia(monAnCart.getGia());
            chiTietDonHang.setIdDonHang(donHang.getKeyID());
            chiTietDonHang_interface.addChiTietDonHang(chiTietDonHang);
        }
    }
}