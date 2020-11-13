package com.nguyenvanhoang.foodapp.interface_dao;

import com.google.firebase.database.DatabaseReference;
import com.nguyenvanhoang.foodapp.entities.KhuVuc;

import java.util.List;

public interface KhuVuc_Interface {
    public DatabaseReference getAllKhuVuc();
    public DatabaseReference addKhuVuc(KhuVuc khuVuc);
    public DatabaseReference updateKhuVuc(KhuVuc khuVuc);
}
