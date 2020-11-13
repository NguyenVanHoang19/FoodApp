package com.nguyenvanhoang.foodapp.entities;

import java.io.Serializable;

public class DonHang implements Serializable {
    private String keyID;
    private String keyIdUser;
    private String keyIdNhaHang;
    private String ngayDat;
    private String ghiChu;
    private String trangThaiXacNhanDonHang ;
    private String trangThaiDaGiaoHang;

    public DonHang(String keyID, String emailUser, String emailNhaHang, String ngayDat, String ghiChu, String trangThaiXacNhanDonHang, String trangThaiDaGiaoHang) {
        this.keyID = keyID;
        this.keyIdUser = emailUser;
        this.keyIdNhaHang = emailNhaHang;
        this.ngayDat = ngayDat;
        this.ghiChu = ghiChu;
        this.trangThaiXacNhanDonHang = trangThaiXacNhanDonHang;
        this.trangThaiDaGiaoHang = trangThaiDaGiaoHang;
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
