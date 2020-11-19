package com.nguyenvanhoang.foodapp.entities;

import java.io.Serializable;

public class NhaHang implements Serializable {
    private String keyID;
    private String keyIdAccount;
    private String emailNhaHang;
    private String idKhuVuc;
    private String idLoai;
    private String tenNhaHang;
    private DiaChi diaChi;
    private String sdt ;
    private String gioiThieu;
    private String hinhAnh;

    public NhaHang(String keyID, String keyIdAccount, String emailNhaHang, String idKhuVuc, String idLoai, String tenNhaHang, DiaChi diaChi, String sdt, String gioiThieu, String hinhAnh) {
        this.keyID = keyID;
        this.keyIdAccount = keyIdAccount;
        this.emailNhaHang = emailNhaHang;
        this.idKhuVuc = idKhuVuc;
        this.idLoai = idLoai;
        this.tenNhaHang = tenNhaHang;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.gioiThieu = gioiThieu;
        this.hinhAnh = hinhAnh;
    }

    public NhaHang() {
        super();
    }

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getKeyIdAccount() {
        return keyIdAccount;
    }

    public void setKeyIdAccount(String keyIdAccount) {
        this.keyIdAccount = keyIdAccount;
    }

    public String getEmailNhaHang() {
        return emailNhaHang;
    }

    public void setEmailNhaHang(String emailNhaHang) {
        this.emailNhaHang = emailNhaHang;
    }

    public String getIdKhuVuc() {
        return idKhuVuc;
    }

    public void setIdKhuVuc(String idKhuVuc) {
        this.idKhuVuc = idKhuVuc;
    }

    public String getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(String idLoai) {
        this.idLoai = idLoai;
    }

    public String getTenNhaHang() {
        return tenNhaHang;
    }

    public void setTenNhaHang(String tenNhaHang) {
        this.tenNhaHang = tenNhaHang;
    }

    public DiaChi getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "NhaHang{" +
                "keyID='" + keyID + '\'' +
                ", keyIdAccount='" + keyIdAccount + '\'' +
                ", emailNhaHang='" + emailNhaHang + '\'' +
                ", idKhuVuc='" + idKhuVuc + '\'' +
                ", idLoai='" + idLoai + '\'' +
                ", tenNhaHang='" + tenNhaHang + '\'' +
                ", diaChi=" + diaChi +
                ", sdt='" + sdt + '\'' +
                ", gioiThieu='" + gioiThieu + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                '}';
    }
}
