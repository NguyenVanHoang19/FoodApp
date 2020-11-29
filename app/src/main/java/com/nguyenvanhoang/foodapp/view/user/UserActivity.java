package com.nguyenvanhoang.foodapp.view.user;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.view.cart.AddCartActivity;
import com.nguyenvanhoang.foodapp.view.cart.LichSuDatHangActivity;
import com.nguyenvanhoang.foodapp.view.locationmaps.Constaints;
import com.nguyenvanhoang.foodapp.view.locationmaps.ServiceAddress;

public class UserActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private Toolbar toolbar ;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Button btnDangNhap,btnDangKy,btnDangXuat,btnGioHang,btnLichSuDatHang;
    private TextView tvViTriHienTai;
    private ResultReceiver resultReceiver;
    public static boolean TRANG_THAI_DANG_NHAP = false;
    public static String Email_Login = "false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        resultReceiver = new AddressResultReceiver(new Handler());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangXuat = (Button) findViewById(R.id.btnDangXuat);
        btnGioHang = (Button) findViewById(R.id.btnGioHang);
        tvViTriHienTai = (TextView) findViewById(R.id.tvViTriHienTai);
        btnLichSuDatHang = (Button) findViewById(R.id.btnLichSuDatHang);
        // check quyen
        if (ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(UserActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION);
        } else {
            getCurrentLocation();
        }
        btnDangXuat.setVisibility(View.GONE);
        btnLichSuDatHang.setVisibility(View.GONE);
        initActionBar();
        if(TRANG_THAI_DANG_NHAP){
            btnDangNhap.setVisibility(View.GONE);
            btnDangKy.setVisibility(View.GONE);
            btnDangXuat.setVisibility(View.VISIBLE);
            btnLichSuDatHang.setVisibility(View.VISIBLE);
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
                btnLichSuDatHang.setVisibility(View.GONE);
                TRANG_THAI_DANG_NHAP = false;
                Email_Login = "false";
                setTitle("Cá nhân");
            }
        });
        btnLichSuDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserActivity.this, LichSuDatHangActivity.class));
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Chua Cho Phep Quyen Vi Tri", Toast.LENGTH_LONG).show();
            }
        }
    }
    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(UserActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(UserActivity.this)
                                .removeLocationUpdates(this);
                        if(locationResult != null && locationResult.getLocations().size() > 0){
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longtitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLongitude();
                            Location location = new Location("providerNA");
                            location.setLatitude(latitude);
                            location.setLongitude(longtitude);
                            fetchAddressFromLatLong(location);
                        }
                        else{
                        }

                    }
                }, Looper.getMainLooper());
    }
    private void fetchAddressFromLatLong(Location location){
        Intent intent = new Intent(this, ServiceAddress.class);
        intent.putExtra(Constaints.RECEIVER,resultReceiver);
        intent.putExtra(Constaints.LOCATION_DATA_EXTRA,location);
        startService(intent);
    }
    private class AddressResultReceiver extends ResultReceiver {
        AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if(resultCode ==  Constaints.SUCCESS_RESULT){
                tvViTriHienTai.setText(resultData.getString(Constaints.RESULT_DATA_KEY));
            }
            else{
                Toast.makeText(UserActivity.this,resultData.getString(Constaints.RESULT_DATA_KEY),Toast.LENGTH_LONG).show();
            }
        }
    }
}