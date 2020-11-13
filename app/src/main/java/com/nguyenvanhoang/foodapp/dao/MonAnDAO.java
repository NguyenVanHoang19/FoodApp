package com.nguyenvanhoang.foodapp.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoang.foodapp.entities.MonAn;
import com.nguyenvanhoang.foodapp.interface_dao.MonAn_Interface;

public class MonAnDAO implements MonAn_Interface {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
    private DatabaseReference databaseReference = firebaseDatabase.getReference("monan");

    @Override
    public DatabaseReference getAllMonAn() {
        return databaseReference;
    }

    @Override
    public DatabaseReference addMonAn(MonAn monAn) {
        String keyID = databaseReference.push().getKey();
        monAn.setKeyID(keyID);
        databaseReference.child(monAn.getKeyID()).setValue(monAn);
        return databaseReference;
    }

    @Override
    public DatabaseReference updateMonAn(MonAn monAn) {
        databaseReference.child(monAn.getKeyID()).setValue(monAn);
        return databaseReference;
    }
}
