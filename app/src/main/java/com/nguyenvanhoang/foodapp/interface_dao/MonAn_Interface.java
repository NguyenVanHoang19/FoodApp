package com.nguyenvanhoang.foodapp.interface_dao;

import com.google.firebase.database.DatabaseReference;
import com.nguyenvanhoang.foodapp.entities.MonAn;

import java.util.List;

public interface MonAn_Interface {
    public DatabaseReference getAllMonAn();
    public DatabaseReference addMonAn(MonAn monAn);
    public DatabaseReference updateMonAn(MonAn monAn);
}
