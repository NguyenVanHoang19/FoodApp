package com.nguyenvanhoang.foodapp.entities;

import java.io.Serializable;

public class DonHang implements Serializable {
    private String keyID;
    private String keyIdUser;
    private String keyIdNhaHang;
    private String ngayDat;
    private String ghiChu;
    private double tongTien;
    private String trangThaiXacNhanDonHang ;
    private String trangThaiDaGiaoHang;
    private String trangThaiHuyDonHang;

    public DonHang(String keyID, String keyIdUser, String keyIdNhaHang, String ngayDat, String ghiChu, double tongTien, String trangThaiXacNhanDonHang, String trangThaiDaGiaoHang, String trangThaiHuyDonHang) {
        this.keyID = keyID;
        this.keyIdUser = keyIdUser;
        this.keyIdNhaHang = keyIdNhaHang;
        this.ngayDat = ngayDat;
        this.ghiChu = ghiChu;
        this.tongTien = tongTien;
        this.trangThaiXacNhanDonHang = trangThaiXacNhanDonHang;
        this.trangThaiDaGiaoHang = trangThaiDaGiaoHang;
        this.trangThaiHuyDonHang = trangThaiHuyDonHang;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThaiHuyDonHang() {
        return trangThaiHuyDonHang;
    }

    public void setTrangThaiHuyDonHang(String trangThaiHuyDonHang) {
        this.trangThaiHuyDonHang = trangThaiHuyDonHang;
    }

    public DonHang() {
        super();
    }

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getKeyIdUser() {
        return keyIdUser;
    }

    public void setKeyIdUser(String keyIdUser) {
        this.keyIdUser = keyIdUser;
    }

    public String getKeyIdNhaHang() {
        return keyIdNhaHang;
    }

    public void setKeyIdNhaHang(String keyIdNhaHang) {
        this.keyIdNhaHang = keyIdNhaHang;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThaiXacNhanDonHang() {
        return trangThaiXacNhanDonHang;
    }

    public void setTrangThaiXacNhanDonHang(String trangThaiXacNhanDonHang) {
        this.trangThaiXacNhanDonHang = trangThaiXacNhanDonHang;
    }

    public String getTrangThaiDaGiaoHang() {
        return trangThaiDaGiaoHang;
    }

    public void setTrangThaiDaGiaoHang(String trangThaiDaGiaoHang) {
        this.trangThaiDaGiaoHang = trangThaiDaGiaoHang;
    }
}
