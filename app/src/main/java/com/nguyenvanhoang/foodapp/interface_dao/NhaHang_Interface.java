package com.nguyenvanhoang.foodapp.interface_dao;

import com.google.firebase.database.DatabaseReference;
import com.nguyenvanhoang.foodapp.entities.NhaHang;

import java.util.List;

public interface NhaHang_Interface {
    public DatabaseReference getAllNhaHang();
    public DatabaseReference addNhaHang(NhaHang nhaHang);
    public DatabaseReference updateNhaHang(NhaHang nhaHang);
}
