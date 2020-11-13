package com.nguyenvanhoang.foodapp.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoang.foodapp.entities.LoaiMonAn;
import com.nguyenvanhoang.foodapp.interface_dao.LoaiMonAn_Interface;

public class LoaiMonAnDAO implements LoaiMonAn_Interface {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
    private DatabaseReference databaseReference = firebaseDatabase.getReference("loaimonan");
    @Override
    public DatabaseReference getAllLoaiMonAn() {
        return databaseReference;
    }

    @Override
    public DatabaseReference addLoaiMonAn(LoaiMonAn loaiMonAn) {
        String keyID = databaseReference.push().getKey();
        loaiMonAn.setKeyID(keyID);
        databaseReference.child(loaiMonAn.getKeyID()).setValue(loaiMonAn);
        return databaseReference;
    }

    @Override
    public DatabaseReference updateLoaiMonAn(LoaiMonAn loaiMonAn) {
        databaseReference.child(loaiMonAn.getKeyID()).setValue(loaiMonAn);
        return databaseReference;
    }
}
