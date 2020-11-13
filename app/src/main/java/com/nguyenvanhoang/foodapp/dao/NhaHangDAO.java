package com.nguyenvanhoang.foodapp.dao;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenvanhoang.foodapp.entities.NhaHang;
import com.nguyenvanhoang.foodapp.interface_dao.NhaHang_Interface;

import java.util.ArrayList;
import java.util.List;

public class NhaHangDAO implements NhaHang_Interface {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
    private DatabaseReference databaseReference = firebaseDatabase.getReference("nhahang");

    @Override
    public DatabaseReference getAllNhaHang() {
        return databaseReference;
    }

    @Override
    public DatabaseReference addNhaHang(NhaHang nhaHang) {
        String keyID = databaseReference.push().getKey();
        nhaHang.setKeyID(keyID);
        databaseReference.child(nhaHang.getKeyID()).setValue(nhaHang);
        return databaseReference;
    }

    @Override
    public DatabaseReference updateNhaHang(NhaHang nhaHang) {
        databaseReference.child(nhaHang.getKeyID()).setValue(nhaHang);
        return databaseReference;
    }
}
