package com.nguyenvanhoang.foodapp.entities;

import java.io.Serializable;
import java.util.Objects;

public class MonAn implements Serializable {
    private String keyID;
    private String idNhaHang;
    private String idLoaiMon;
    private String tenMon;
    private double giaTien;
    private String moTa;
    private double discount;
    private String trangThai;
    private String hinhAnh;

    public MonAn(String keyID) {
        this.keyID = keyID;
    }

    public MonAn(String keyID, String idNhaHang, String idLoaiMon, String tenMon, double giaTien, String moTa, double discount, String trangThai, String hinhAnh) {
        this.keyID = keyID;
        this.idNhaHang = idNhaHang;
        this.idLoaiMon = idLoaiMon;
        this.tenMon = tenMon;
        this.giaTien = giaTien;
        this.moTa = moTa;
        this.discount = discount;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
    }

    public MonAn() {
        super();
    }

    public String getIdLoaiMon() {
        return idLoaiMon;
    }

    public void setIdLoaiMon(String idLoaiMon) {
        this.idLoaiMon = idLoaiMon;
    }

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getIdNhaHang() {
        return idNhaHang;
    }

    public void setIdNhaHang(String idNhaHang) {
        this.idNhaHang = idNhaHang;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonAn)) return false;
        MonAn monAn = (MonAn) o;
        return getKeyID().equals(monAn.getKeyID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKeyID());
    }

    @Override
    public String toString() {
        return "MonAn{" +
                "keyID='" + keyID + '\'' +
                ", idNhaHang='" + idNhaHang + '\'' +
                ", idLoaiMon='" + idLoaiMon + '\'' +
                ", tenMon='" + tenMon + '\'' +
                ", giaTien=" + giaTien +
                ", moTa='" + moTa + '\'' +
                ", discount=" + discount +
                ", trangThai='" + trangThai + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                '}';
    }
}
