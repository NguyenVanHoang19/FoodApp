package com.nguyenvanhoang.foodapp.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoang.foodapp.entities.LoaiNhaHang;
import com.nguyenvanhoang.foodapp.interface_dao.LoaiNhaHang_Interface;

public class LoaiNhaHangDAO implements LoaiNhaHang_Interface {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
    private  DatabaseReference databaseReference = firebaseDatabase.getReference("loainhahang");
    @Override
    public DatabaseReference getAllLoaiNhaHang() {
        return databaseReference;
    }

    @Override
    public DatabaseReference addLoaiNhaHang(LoaiNhaHang loaiNhaHang) {
        String keyID = databaseReference.push().getKey();
        loaiNhaHang.setKeyID(keyID);
        databaseReference.child(loaiNhaHang.getKeyID()).setValue(loaiNhaHang);
        return databaseReference;
    }

    @Override
    public DatabaseReference updateLoaiNhaHang(LoaiNhaHang loaiNhaHang) {
        databaseReference.child(loaiNhaHang.getKeyID()).setValue(loaiNhaHang);
        return databaseReference;
    }
}
