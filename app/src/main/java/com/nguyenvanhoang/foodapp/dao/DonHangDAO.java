package com.nguyenvanhoang.foodapp.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoang.foodapp.entities.DonHang;
import com.nguyenvanhoang.foodapp.interface_dao.DonHang_Interface;

public class DonHangDAO implements DonHang_Interface {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
    private DatabaseReference databaseReference = firebaseDatabase.getReference("donhang");
    @Override
    public DatabaseReference getAllDonHang() {
        return databaseReference;
    }

    @Override
    public DatabaseReference addDonHang(DonHang donHang) {
        String keyID = databaseReference.push().getKey();
        donHang.setKeyID(keyID);
        databaseReference.child(donHang.getKeyID()).setValue(donHang);
        return databaseReference;
    }

    @Override
    public DatabaseReference updateDonHang(DonHang donHang) {
        databaseReference.child(donHang.getKeyID()).setValue(donHang);
        return databaseReference;
    }
}
