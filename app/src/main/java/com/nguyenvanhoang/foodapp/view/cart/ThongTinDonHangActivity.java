package com.nguyenvanhoang.foodapp.view.cart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.database.MonAnCart;
import com.nguyenvanhoang.foodapp.view.home.MainActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ThongTinDonHangActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Toolbar toolbar ;
    private double latNhaHang;
    private double longNhaHang;
    private String diaChiGiaoHang;
    private String tenNhaHang;
    private String maNhaHang;
    private FirebaseDatabase firebaseDatabase ;
    private List<MonAnCart> monAnCartList ;
    private TextView tvTenNhaHang,tvDiaChiGiaoHang,tvGiaMonAnGioHang,tvKhuyenMai,tvSoKm,tvTongTienThanhToan;
    private Button btnHuyDonHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_don_hang);
        connectView();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Đơn hàng của bạn");
        setTitleColor(R.color.colorPrimary);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinDonHangActivity.this);
        builder.setMessage("Đặt hàng thành công!!!");
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // nhan du lieu
        Intent intent = getIntent();
        tenNhaHang = intent.getStringExtra("tenNhaHang");
        maNhaHang =  intent.getStringExtra("maNhaHang");
        diaChiGiaoHang = intent.getStringExtra("diaChiGiaoHang");
        double giaMonAn = intent.getDoubleExtra("giaMonAn",0);
        double tongThanhToan = intent.getDoubleExtra("tongThanhToan",0);
        monAnCartList = new ArrayList<>();
        monAnCartList = (List<MonAnCart>) intent.getSerializableExtra("monAnCartList");
        tvTenNhaHang.setText(tenNhaHang);
        tvDiaChiGiaoHang.setText(diaChiGiaoHang);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGiaMonAnGioHang.setText(decimalFormat.format(giaMonAn) + " VNĐ");
        tvTongTienThanhToan.setText(decimalFormat.format(tongThanhToan) + " VNĐ");
        if(monAnCartList.size() > 0){
            tvSoKm.setText( String.format(Locale.US,"%.2f km",monAnCartList.get(0).getSoKm()));
        }
        System.out.println(monAnCartList.size());


//        firebaseDatabase = FirebaseDatabase.getInstance();
//        Query query = firebaseDatabase.getReference().child("nhahang").child(intent.getStringExtra("maNhaHang"));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        if (monAnCartList.size() > 0){
            latNhaHang = monAnCartList.get(0).getLatNhaHang();
            longNhaHang = monAnCartList.get(0).getLongNhaHang();
            LatLng sydney = new LatLng(latNhaHang, longNhaHang);
            System.out.println(latNhaHang);
            System.out.println(longNhaHang);
            mMap.addMarker(new MarkerOptions().position(sydney).title(tenNhaHang));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,18));
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(ThongTinDonHangActivity.this, MainActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ThongTinDonHangActivity.this, MainActivity.class));
        finish();
    }

    public void connectView(){
        tvTenNhaHang =  (TextView) findViewById(R.id.tvTenNhaHang);
        tvDiaChiGiaoHang = (TextView) findViewById(R.id.tvDiaChiGiaoHang);
        tvGiaMonAnGioHang = (TextView) findViewById(R.id.tvGiaMonAnGioHang);
        tvKhuyenMai = (TextView) findViewById(R.id.tvKhuyenMai);
        tvSoKm = (TextView) findViewById(R.id.tvSoKm);
        tvTongTienThanhToan = (TextView) findViewById(R.id.tvTongTienThanhToan);
        btnHuyDonHang = (Button) findViewById(R.id.btnHuyDonHang);
    }
}