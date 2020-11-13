package com.nguyenvanhoang.foodapp.interface_dao;

import com.google.firebase.database.DatabaseReference;
import com.nguyenvanhoang.foodapp.entities.DonHang;

import java.util.List;

public interface DonHang_Interface {
    public DatabaseReference getAllDonHang();
    public DatabaseReference addDonHang(DonHang donHang);
    public DatabaseReference updateDonHang(DonHang donHang);
}
