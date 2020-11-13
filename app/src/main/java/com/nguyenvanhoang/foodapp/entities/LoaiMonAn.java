package com.nguyenvanhoang.foodapp.entities;

import java.io.Serializable;

public class LoaiMonAn implements Serializable {
    private String keyID;
    private String tenLoaiMon;
    private String hinhAnh;

    public LoaiMonAn(String keyID, String tenLoaiMon, String hinhAnh) {
        this.keyID = keyID;
        this.tenLoaiMon = tenLoaiMon;
        this.hinhAnh = hinhAnh;
    }

    public LoaiMonAn(String tenLoaiMon, String hinhAnh) {
        this.tenLoaiMon = tenLoaiMon;
        this.hinhAnh = hinhAnh;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public LoaiMonAn() {
        super();
    }

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getTenLoaiMon() {
        return tenLoaiMon;
    }

    public void setTenLoaiMon(String tenLoaiMon) {
        this.tenLoaiMon = tenLoaiMon;
    }

}
