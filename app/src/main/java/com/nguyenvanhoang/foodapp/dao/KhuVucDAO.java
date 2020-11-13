package com.nguyenvanhoang.foodapp.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenvanhoang.foodapp.entities.KhuVuc;
import com.nguyenvanhoang.foodapp.interface_dao.KhuVuc_Interface;

public class KhuVucDAO implements KhuVuc_Interface {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
    private DatabaseReference databaseReference = firebaseDatabase.getReference("khuvuc");
    @Override
    public DatabaseReference getAllKhuVuc() {
        return databaseReference;
    }

    @Override
    public DatabaseReference addKhuVuc(KhuVuc khuVuc) {
        String keyID = databaseReference.push().getKey();
        khuVuc.setKeyID(keyID);
        databaseReference.child(khuVuc.getKeyID()).setValue(khuVuc);
        return databaseReference;
    }

    @Override
    public DatabaseReference updateKhuVuc(KhuVuc khuVuc) {
        databaseReference.child(khuVuc.getKeyID()).setValue(khuVuc);
        return databaseReference;
    }
}
