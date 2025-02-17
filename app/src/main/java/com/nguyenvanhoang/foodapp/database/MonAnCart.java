package com.nguyenvanhoang.foodapp.database;

import java.io.Serializable;

public class MonAnCart implements Serializable {
    private String maMon;
    private String tenMon;
    private String chiTiet;
    private String hinhAnh;
    private double gia;
    private int soLuongChon;
    private String maNhaHang;
    private String diaChiNhaHang;
    private String tenNhaHang;
    private String maUser;
    private double latNhaHang;
    private double longNhaHang;
    private float soKm;


    public MonAnCart() {
        super();
    }

    public MonAnCart(String maMon, String tenMon, String chiTiet, String hinhAnh, double gia, int soLuongChon, String maNhaHang, String diaChiNhaHang, String tenNhaHang, String maUser, double latNhaHang, double longNhaHang, float soKm) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.chiTiet = chiTiet;
        this.hinhAnh = hinhAnh;
        this.gia = gia;
        this.soLuongChon = soLuongChon;
        this.maNhaHang = maNhaHang;
        this.diaChiNhaHang = diaChiNhaHang;
        this.tenNhaHang = tenNhaHang;
        this.maUser = maUser;
        this.latNhaHang = latNhaHang;
        this.longNhaHang = longNhaHang;
        this.soKm = soKm;
    }

    public double getLatNhaHang() {
        return latNhaHang;
    }

    public void setLatNhaHang(double latNhaHang) {
        this.latNhaHang = latNhaHang;
    }

    public double getLongNhaHang() {
        return longNhaHang;
    }

    public void setLongNhaHang(double longNhaHang) {
        this.longNhaHang = longNhaHang;
    }

    public float getSoKm() {
        return soKm;
    }

    public void setSoKm(float soKm) {
        this.soKm = soKm;
    }

    public String getMaUser() {
        return maUser;
    }

    public void setMaUser(String maUser) {
        this.maUser = maUser;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuongChon() {
        return soLuongChon;
    }

    public void setSoLuongChon(int soLuongChon) {
        this.soLuongChon = soLuongChon;
    }

    public String getMaNhaHang() {
        return maNhaHang;
    }

    public void setMaNhaHang(String maNhaHang) {
        this.maNhaHang = maNhaHang;
    }

    public String getDiaChiNhaHang() {
        return diaChiNhaHang;
    }

    public void setDiaChiNhaHang(String diaChiNhaHang) {
        this.diaChiNhaHang = diaChiNhaHang;
    }

    public String getTenNhaHang() {
        return tenNhaHang;
    }

    public void setTenNhaHang(String tenNhaHang) {
        this.tenNhaHang = tenNhaHang;
    }

    @Override
    public String toString() {
        return "MonAnCart{" +
                "maMon='" + maMon + '\'' +
                ", tenMon='" + tenMon + '\'' +
                ", chiTiet='" + chiTiet + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", gia=" + gia +
                ", soLuongChon=" + soLuongChon +
                ", maNhaHang='" + maNhaHang + '\'' +
                ", diaChiNhaHang='" + diaChiNhaHang + '\'' +
                ", tenNhaHang='" + tenNhaHang + '\'' +
                ", maUser='" + maUser + '\'' +
                ", latNhaHang=" + latNhaHang +
                ", longNhaHang=" + longNhaHang +
                ", soKm=" + soKm +
                '}';
    }
}
