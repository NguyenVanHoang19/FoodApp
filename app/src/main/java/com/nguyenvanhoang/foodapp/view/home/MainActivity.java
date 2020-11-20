package com.nguyenvanhoang.foodapp.view.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.nguyenvanhoang.foodapp.R;
import com.nguyenvanhoang.foodapp.apdapter.RecyclerViewHomeAdapter;
import com.nguyenvanhoang.foodapp.dao.AccountDAO;
import com.nguyenvanhoang.foodapp.dao.ChiTietDonHangDAO;
import com.nguyenvanhoang.foodapp.dao.DonHangDAO;
import com.nguyenvanhoang.foodapp.dao.KhuVucDAO;
import com.nguyenvanhoang.foodapp.dao.LoaiMonAnDAO;
import com.nguyenvanhoang.foodapp.dao.LoaiNhaHangDAO;
import com.nguyenvanhoang.foodapp.dao.MonAnDAO;
import com.nguyenvanhoang.foodapp.dao.NhaHangDAO;
import com.nguyenvanhoang.foodapp.dao.UserDAO;
import com.nguyenvanhoang.foodapp.entities.Account;
import com.nguyenvanhoang.foodapp.entities.ChiTietDonHang;
import com.nguyenvanhoang.foodapp.entities.DiaChi;
import com.nguyenvanhoang.foodapp.entities.DonHang;
import com.nguyenvanhoang.foodapp.entities.KhuVuc;
import com.nguyenvanhoang.foodapp.entities.LoaiMonAn;
import com.nguyenvanhoang.foodapp.entities.LoaiNhaHang;
import com.nguyenvanhoang.foodapp.entities.MonAn;
import com.nguyenvanhoang.foodapp.entities.NhaHang;
import com.nguyenvanhoang.foodapp.entities.User;
import com.nguyenvanhoang.foodapp.interface_dao.AccountDAO_Interface;
import com.nguyenvanhoang.foodapp.interface_dao.ChiTietDonHang_Interface;
import com.nguyenvanhoang.foodapp.interface_dao.DonHang_Interface;
import com.nguyenvanhoang.foodapp.interface_dao.KhuVuc_Interface;
import com.nguyenvanhoang.foodapp.interface_dao.LoaiMonAn_Interface;
import com.nguyenvanhoang.foodapp.interface_dao.LoaiNhaHang_Interface;
import com.nguyenvanhoang.foodapp.interface_dao.MonAn_Interface;
import com.nguyenvanhoang.foodapp.interface_dao.NhaHang_Interface;
import com.nguyenvanhoang.foodapp.interface_dao.UserDAO_Interface;
import com.nguyenvanhoang.foodapp.view.cart.AddCartActivity;
import com.nguyenvanhoang.foodapp.view.category.CategoryActivity;
import com.nguyenvanhoang.foodapp.view.locationmaps.Constaints;
import com.nguyenvanhoang.foodapp.view.locationmaps.ServiceAddress;
import com.nguyenvanhoang.foodapp.view.searchviewmonan.SearchViewMonAnActivity;
import com.nguyenvanhoang.foodapp.view.user.UserActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    public static double LATITUDE_CURRENT = 0.0;
    public static double LONGTITUDE_CURRENT = 0.0;
    public static String DIACHIHIENTAI = "";
    private ValueEventListener databaseReference ;
    private FirebaseDatabase firebaseDatabase ;
    private ViewPager viewPager;
    private RecyclerView recyclerViewMonAn ;
    private TextView tvTimKiemMonAn;
    private Button btnUser;
    private FloatingActionButton btnThongBaoGioHang;
    private TextView tvViTriHienTai ;
    private ResultReceiver resultReceiver;
    private StorageReference mStorageRef;
    List<LoaiMonAn> loaiMonAns = new ArrayList<LoaiMonAn>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultReceiver = new AddressResultReceiver(new Handler());
        tvTimKiemMonAn = (TextView) findViewById(R.id.edtTimKiemMonAn);
        viewPager = (ViewPager) findViewById(R.id.viewPagerHeader);
        recyclerViewMonAn = (RecyclerView) findViewById(R.id.recyclerCategory);
        btnUser = (Button) findViewById(R.id.btnUser);
        tvViTriHienTai = (TextView) findViewById(R.id.tvViTriHienTai);
        btnThongBaoGioHang = (FloatingActionButton) findViewById(R.id.btnThongBaoGioHang);
        // check quyen
        if (ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION);
        } else {
            getCurrentLocation();
        }
        btnThongBaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddCartActivity.class));
            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
       //themDuLieu();
        // load
        LoaiMonAn_Interface loaiMonAn_interface = new LoaiMonAnDAO();
        showLoading();
        databaseReference = loaiMonAn_interface.getAllLoaiMonAn().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hideLoading();
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    LoaiMonAn loaiMonAn = data.getValue(LoaiMonAn.class);
                    loaiMonAns.add(loaiMonAn);
                    System.out.println(data.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        RecyclerViewHomeAdapter homeAdapter =new RecyclerViewHomeAdapter(loaiMonAns,this);
        recyclerViewMonAn.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3,
                GridLayoutManager.VERTICAL, false);
        recyclerViewMonAn.setLayoutManager(layoutManager);
        recyclerViewMonAn.setNestedScrollingEnabled(true);
        homeAdapter.setOnItemClickListener(new RecyclerViewHomeAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
               // Toast.makeText(getApplicationContext(),loaiMonAns.get(position).getKeyID(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.putExtra("loaiMon", (Serializable) loaiMonAns);
                intent.putExtra("viTri",position);
                startActivity(intent);
            }
        });
        homeAdapter.notifyDataSetChanged();

        ///// mon an

        MonAn_Interface monAn_interface = new MonAnDAO();
        List<MonAn> monAnList = new ArrayList<MonAn>();
        monAn_interface.getAllMonAn().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    MonAn monAn = snapshot.getValue(MonAn.class);
                    monAnList.add(monAn);
                    System.out.println(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        tvTimKiemMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchViewMonAnActivity.class);
                intent.putExtra("monAnList", (Serializable) monAnList);
                startActivity(intent);
            }
        });
    }
    public void showLoading() {
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
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

    /// get current location
    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(MainActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(MainActivity.this)
                                .removeLocationUpdates(this);
                        if(locationResult != null && locationResult.getLocations().size() > 0){
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            LATITUDE_CURRENT = latitude;
                            double longtitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLongitude();
                            LONGTITUDE_CURRENT = longtitude;
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

    //////
    private void fetchAddressFromLatLong(Location location){
        Intent intent = new Intent(this, ServiceAddress.class);
        intent.putExtra(Constaints.RECEIVER,resultReceiver);
        intent.putExtra(Constaints.LOCATION_DATA_EXTRA,location);
        startService(intent);
    }
    // google maps
    private class AddressResultReceiver extends ResultReceiver {
        AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if(resultCode ==  Constaints.SUCCESS_RESULT){
                tvViTriHienTai.setText(resultData.getString(Constaints.RESULT_DATA_KEY));
                DIACHIHIENTAI = resultData.getString(Constaints.RESULT_DATA_KEY);
                DIACHIHIENTAI = resultData.getString(Constaints.RESULT_DATA_KEY);
            }
            else{
                Toast.makeText(MainActivity.this,resultData.getString(Constaints.RESULT_DATA_KEY),Toast.LENGTH_LONG).show();
            }
        }
    }


    public void themDuLieu(){
        // add user
        final User user = new User();
        user.setEmailUser("nguyenvanhoang34iuh@gmail.com");
        user.setPassWord("12345678");
        user.setHoTen("Nguyễn Văn Hoàng");
        user.setSdt("0898136946");
        user.setHinhAnh(".img");
        user.setNamSinh("21/11/1999");
        user.setDiaChi("Khánh Hòa");
        user.setGioiTinh("Nam");
        UserDAO_Interface userDAO_interface = new UserDAO();
        userDAO_interface.addUser(user);
        //add loai nha hang
        LoaiNhaHang_Interface loaiNhaHang_interface = new LoaiNhaHangDAO();
        LoaiNhaHang loaiNhaHang = new LoaiNhaHang();
        loaiNhaHang.setTenLoai("Nhà hàng Hải Yến");
        loaiNhaHang.setHinhAnh("./img");
        loaiNhaHang_interface.addLoaiNhaHang(loaiNhaHang);
        //add khu vực
        KhuVuc khuVuc = new KhuVuc();
        khuVuc.setTenKhuVuc("Hồ Chí Minh");

        KhuVuc_Interface khuVuc_interface = new KhuVucDAO();
        khuVuc_interface.addKhuVuc(khuVuc);
        //add Nha hang
        NhaHang nhaHang = new NhaHang();
        nhaHang.setEmailNhaHang("thatbatngo34@gmail.com");
        nhaHang.setIdKhuVuc(khuVuc.getKeyID());
        nhaHang.setIdLoai(loaiNhaHang.getKeyID());
        nhaHang.setSdt("0891723133");
        DiaChi diaChi = new DiaChi();
        diaChi.setLatitude(10.846758);
        diaChi.setLongtitude(106.670653);
        diaChi.setFullDiaChi("28,P15,Lê Đức Thọ,Gò Vấp");
        nhaHang.setDiaChi(diaChi);
        nhaHang.setGioiThieu("Quán nướng BBQ");
        nhaHang.setHinhAnh("https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/LoaiMonAn%2Fypuxtw1511297463.jpg?alt=media&token=6705e9fb-d20f-4c84-808c-f364aabe9fa6");
        nhaHang.setTenNhaHang("Nhà Hàng BBQ Số 1");
        ///
        NhaHang nhaHang1 = new NhaHang();
        nhaHang1.setEmailNhaHang("nguyenvanhoang34@gmail.com");
        nhaHang1.setIdKhuVuc(khuVuc.getKeyID());
        nhaHang1.setIdLoai(loaiNhaHang.getKeyID());
        nhaHang1.setSdt("08917238992");
        DiaChi diaChi1 = new DiaChi();
        diaChi1.setLatitude(10.845797);
        diaChi1.setLongtitude(106.642546);
        diaChi1.setFullDiaChi("69 Phạm Văn Chiêu,P.8,Gò Vấp,Hồ Chí Minh");
        nhaHang1.setDiaChi(diaChi1);
        nhaHang1.setGioiThieu("Thiên đường món ngon");
        nhaHang1.setHinhAnh("https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/LoaiMonAn%2Fd8f6qx1604182128.jpg?alt=media&token=975e6245-1eb6-405f-afd3-3fcdb88aef26");
        nhaHang1.setTenNhaHang("Thức ăn nhanh Ngọc Hoa");

        NhaHang_Interface nhaHang_interface = new NhaHangDAO();
        nhaHang_interface.addNhaHang(nhaHang);
        nhaHang_interface.addNhaHang(nhaHang1);
        LoaiMonAn loaiMonAn = new LoaiMonAn("Đồ ăn nhanh","https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/LoaiMonAn%2Fd8f6qx1604182128.jpg?alt=media&token=975e6245-1eb6-405f-afd3-3fcdb88aef26");
        LoaiMonAn loaiMonAn1 = new LoaiMonAn("Cơm phần","https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/LoaiMonAn%2Frlwcc51598734603.jpg?alt=media&token=a3e5b164-62cc-40ad-9850-966dd9ea8eb5");
        LoaiMonAn loaiMonAn2 = new LoaiMonAn("Bánh mì","https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/LoaiMonAn%2Fkcv6hj1598733479.jpg?alt=media&token=d6dc8883-c592-4744-af58-4c5605aab19f");
        LoaiMonAn loaiMonAn3 = new LoaiMonAn("Ăn vặt","https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/LoaiMonAn%2Fwprvrw1511641295.jpg?alt=media&token=1c4d8174-f9fa-461b-a000-02465fa019b7");
        LoaiMonAn loaiMonAn4 = new LoaiMonAn("Gà","https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/LoaiMonAn%2Flvn2d51598732465.jpg?alt=media&token=9b689592-1a00-4f09-b696-ae3ed638ca46");
        LoaiMonAn loaiMonAn5 = new LoaiMonAn("Bò nướng","https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/LoaiMonAn%2Fypuxtw1511297463.jpg?alt=media&token=6705e9fb-d20f-4c84-808c-f364aabe9fa6");
        LoaiMonAn loaiMonAn6 = new LoaiMonAn("Mì xào","https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/LoaiMonAn%2Fmixao.jpg?alt=media&token=c7d4e250-7bd2-44be-bd9d-707dcc931f9e");
        LoaiMonAn loaiMonAn7 = new LoaiMonAn("Trà sữa","https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/LoaiMonAn%2Ftra-sua.jpg?alt=media&token=22eada9d-57eb-4a9d-81a3-c997bccbc5d1");
        LoaiMonAn loaiMonAn8 = new LoaiMonAn("Bizza","https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/LoaiMonAn%2Fbizza.jpg?alt=media&token=a4af9334-60d8-4cc5-ba40-03cf2672d7b0");
        LoaiMonAn_Interface loaiMonAn_interface = new LoaiMonAnDAO();
        loaiMonAn_interface.addLoaiMonAn(loaiMonAn);
        loaiMonAn_interface.addLoaiMonAn(loaiMonAn1);
        loaiMonAn_interface.addLoaiMonAn(loaiMonAn2);
        loaiMonAn_interface.addLoaiMonAn(loaiMonAn3);
        loaiMonAn_interface.addLoaiMonAn(loaiMonAn4);
        loaiMonAn_interface.addLoaiMonAn(loaiMonAn5);
        loaiMonAn_interface.addLoaiMonAn(loaiMonAn6);
        loaiMonAn_interface.addLoaiMonAn(loaiMonAn7);
        loaiMonAn_interface.addLoaiMonAn(loaiMonAn8);
        loaiMonAn_interface.addLoaiMonAn(loaiMonAn2);
        loaiMonAn_interface.addLoaiMonAn(loaiMonAn3);
        loaiMonAn_interface.addLoaiMonAn(loaiMonAn7);
        //add Mon an
        MonAn_Interface monAn_interface = new MonAnDAO();
        MonAn monAn = new MonAn();
        monAn.setTenMon("Bò Nướng BBQ");
        monAn.setGiaTien(250000);
        monAn.setIdNhaHang(nhaHang.getKeyID());
        monAn.setMoTa("Bò nướng siêu ngon");
        monAn.setDiscount(12);
        monAn.setIdLoaiMon(loaiMonAn5.getKeyID());
        monAn.setHinhAnh("https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/MonAn%2Fypuxtw1511297463.jpg?alt=media&token=fd853ef5-8953-4c75-85ed-180c6660eeeb");
        monAn.setTrangThai("true");
        ///

        MonAn monAn1 = new MonAn();
        monAn1.setTenMon("Bò Nướng Số 1 Sài Gòn");
        monAn1.setGiaTien(250000);
        monAn1.setIdNhaHang(nhaHang.getKeyID());
        monAn1.setMoTa("Bò nướng siêu ngon");
        monAn1.setDiscount(12);
        monAn1.setIdLoaiMon(loaiMonAn5.getKeyID());
        monAn1.setHinhAnh("https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/MonAn%2Fypuxtw1511297463.jpg?alt=media&token=fd853ef5-8953-4c75-85ed-180c6660eeeb");
        monAn1.setTrangThai("true");
      //  System.out.println("Loai mon an 5" + loaiMonAn5.getKeyID());

        /// set mon an loai do an nhanh
        MonAn monAnDoAnNhanh = new MonAn();
        monAnDoAnNhanh.setTenMon("Humburger");
        monAnDoAnNhanh.setGiaTien(250000);
        monAnDoAnNhanh.setIdNhaHang(nhaHang1.getKeyID());
        monAnDoAnNhanh.setMoTa("Humburger ngon siêu rẻ");
        monAnDoAnNhanh.setDiscount(12);
        monAnDoAnNhanh.setIdLoaiMon(loaiMonAn.getKeyID());
        monAnDoAnNhanh.setHinhAnh("https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/MonAn%2FdoAnNhanh.jpg?alt=media&token=29081397-15ab-4dbb-ba10-867813a8b209");
        monAnDoAnNhanh.setTrangThai("true");


        //
        MonAn monAnDoAnNhanh1 = new MonAn();
        monAnDoAnNhanh1.setTenMon("Susi Nhật");
        monAnDoAnNhanh1.setGiaTien(250000);
        monAnDoAnNhanh1.setIdNhaHang(nhaHang1.getKeyID());
        monAnDoAnNhanh1.setMoTa("Susi Nhật Bản thượng hạn");
        monAnDoAnNhanh1.setDiscount(12);
        monAnDoAnNhanh1.setIdLoaiMon(loaiMonAn.getKeyID());
        monAnDoAnNhanh1.setHinhAnh("https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/MonAn%2FdoAnNhanh02.jpg?alt=media&token=d36c091a-e0a1-4ecd-95aa-ef9aad9ff0b6");
        monAnDoAnNhanh1.setTrangThai("true");
        //
        MonAn monAnDoAnNhanh2 = new MonAn();
        monAnDoAnNhanh2.setTenMon("Bánh Tráng Nướng");
        monAnDoAnNhanh2.setGiaTien(250000);
        monAnDoAnNhanh2.setIdNhaHang(nhaHang1.getKeyID());
        monAnDoAnNhanh2.setMoTa("Bánh tráng nướng Sài Gòn");
        monAnDoAnNhanh2.setDiscount(12);
        monAnDoAnNhanh2.setIdLoaiMon(loaiMonAn.getKeyID());
        monAnDoAnNhanh2.setHinhAnh("https://firebasestorage.googleapis.com/v0/b/foodapp-1ad11.appspot.com/o/MonAn%2FdoAnNhanh03.jpg?alt=media&token=9edef593-12fc-4493-83ea-6efb4e16494c");
        monAnDoAnNhanh2.setTrangThai("true");


        monAn_interface.addMonAn(monAn);
        monAn_interface.addMonAn(monAn1);
        monAn_interface.addMonAn(monAnDoAnNhanh);
        monAn_interface.addMonAn(monAnDoAnNhanh1);
        monAn_interface.addMonAn(monAnDoAnNhanh2);
//        //add Don hang
        DonHang donHang = new DonHang();
        donHang.setKeyIdNhaHang(nhaHang.getKeyID());
        donHang.setKeyIdUser(user.getKeyID());
        donHang.setGhiChu("3 Bò Nướng thượng hạng");
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        donHang.setNgayDat(simpleDateFormat.format(date));
        donHang.setTrangThaiXacNhanDonHang("true");
        donHang.setTrangThaiDaGiaoHang("false");

        DonHang_Interface donHang_interface = new DonHangDAO();
        donHang_interface.addDonHang(donHang);
        // add chi tiet don hang
        ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
        chiTietDonHang.setGia(450000);
        chiTietDonHang.setIdMon(monAn.getKeyID());
        chiTietDonHang.setSoLuong(3);

        ChiTietDonHang_Interface chiTietDonHang_interface = new ChiTietDonHangDAO();
        chiTietDonHang_interface.addChiTietDonHang(chiTietDonHang);
//        // add account
        Account account = new Account();
        account.setEmail("hoangnguyenbao19@gmail.com");
        account.setPassWord("12345678");
        account.setRole("nhahang");

        AccountDAO_Interface accountDAO_interface = new AccountDAO();
        accountDAO_interface.addAccount(account);
    }
}