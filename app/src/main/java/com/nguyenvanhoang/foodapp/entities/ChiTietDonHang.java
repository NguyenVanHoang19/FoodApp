package com.nguyenvanhoang.foodapp.entities;

import java.io.Serializable;

public class ChiTietDonHang implements Serializable {
    private String keyID;
    private String idMon;
    private String idDonHang;
    private double gia;
    private int soLuong;

    public ChiTietDonHang(String keyID, String idMon, String idDonHang, double gia, int soLuong) {
        this.keyID = keyID;
        this.idMon = idMon;
        this.idDonHang = idDonHang;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public ChiTietDonHang() {
        super();
    }

    public String getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(String idDonHang) {
        this.idDonHang = idDonHang;
    }

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getIdMon() {
        return idMon;
    }

    public void setIdMon(String idMon) {
        this.idMon = idMon;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
