package com.nguyenvanhoang.foodapp.entities;

import java.io.Serializable;

public class LoaiNhaHang implements Serializable {
    private String keyID;
    private String tenLoai;
    private String hinhAnh;

    public LoaiNhaHang(String keyID, String tenLoai, String hinhAnh) {
        this.keyID = keyID;
        this.tenLoai = tenLoai;
        this.hinhAnh = hinhAnh;
    }

    public LoaiNhaHang() {
        super();
    }

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
