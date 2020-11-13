package com.nguyenvanhoang.foodapp.entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String keyID;
    private String keyIdUser;
    private String emailUser;
    private String hoTen ;
    private String namSinh;
    private String gioiTinh;
    private String diaChi;
    private String passWord;
    private String sdt ;
    private String hinhAnh;

    public User(String keyID, String keyIdUser, String emailUser, String hoTen, String namSinh, String gioiTinh, String diaChi, String passWord, String sdt, String hinhAnh) {
        this.keyID = keyID;
        this.keyIdUser = keyIdUser;
        this.emailUser = emailUser;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.passWord = passWord;
        this.sdt = sdt;
        this.hinhAnh = hinhAnh;
    }

    public User(String keyID) {
        this.keyID = keyID;
    }

    public User() {
        super();
    }

    public String getKeyID() {
        return keyID;
    }

    public String getKeyIdUser() {
        return keyIdUser;
    }

    public void setKeyIdUser(String keyIdUser) {
        this.keyIdUser = keyIdUser;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }


    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getEmailUser().equals(user.getEmailUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmailUser());
    }
}
