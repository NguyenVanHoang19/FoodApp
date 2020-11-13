package com.nguyenvanhoang.foodapp.interface_dao;

import com.google.firebase.database.DatabaseReference;
import com.nguyenvanhoang.foodapp.entities.LoaiMonAn;
import com.nguyenvanhoang.foodapp.entities.MonAn;

public interface LoaiMonAn_Interface {
    public DatabaseReference getAllLoaiMonAn();
    public DatabaseReference addLoaiMonAn(LoaiMonAn loaiMonAn);
    public DatabaseReference updateLoaiMonAn(LoaiMonAn loaiMonAn);
}
