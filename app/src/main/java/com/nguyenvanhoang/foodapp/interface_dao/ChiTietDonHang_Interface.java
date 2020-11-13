package com.nguyenvanhoang.foodapp.interface_dao;

import com.google.firebase.database.DatabaseReference;
import com.nguyenvanhoang.foodapp.entities.ChiTietDonHang;

import java.util.List;

public interface ChiTietDonHang_Interface {
    public DatabaseReference getAllChiTietDonHang();
    public DatabaseReference addChiTietDonHang(ChiTietDonHang chiTietDonHang);
    public DatabaseReference updateChiTietDonHang(ChiTietDonHang chiTietDonHang);
}
