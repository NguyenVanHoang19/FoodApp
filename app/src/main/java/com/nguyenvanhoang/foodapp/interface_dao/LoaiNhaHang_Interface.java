package com.nguyenvanhoang.foodapp.interface_dao;

import com.google.firebase.database.DatabaseReference;
import com.nguyenvanhoang.foodapp.entities.LoaiNhaHang;

import java.util.List;

public interface LoaiNhaHang_Interface {
    public DatabaseReference getAllLoaiNhaHang();
    public DatabaseReference addLoaiNhaHang(LoaiNhaHang loaiNhaHang);
    public DatabaseReference updateLoaiNhaHang(LoaiNhaHang loaiNhaHang);
}
