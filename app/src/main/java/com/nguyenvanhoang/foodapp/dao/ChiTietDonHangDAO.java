package com.nguyenvanhoang.foodapp.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoang.foodapp.entities.ChiTietDonHang;
import com.nguyenvanhoang.foodapp.interface_dao.ChiTietDonHang_Interface;

public class ChiTietDonHangDAO implements ChiTietDonHang_Interface {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
    private DatabaseReference databaseReference = firebaseDatabase.getReference("chitietdonhang");
    @Override
    public DatabaseReference getAllChiTietDonHang() {
        return databaseReference;
    }

    @Override
    public DatabaseReference addChiTietDonHang(ChiTietDonHang chiTietDonHang) {
        String keyID = databaseReference.push().getKey();
        chiTietDonHang.setKeyID(keyID);
        databaseReference.child(chiTietDonHang.getKeyID()).setValue(chiTietDonHang);
        return databaseReference;
    }

    @Override
    public DatabaseReference updateChiTietDonHang(ChiTietDonHang chiTietDonHang) {
        databaseReference.child(chiTietDonHang.getKeyID()).setValue(chiTietDonHang);
        return databaseReference;
    }
}
