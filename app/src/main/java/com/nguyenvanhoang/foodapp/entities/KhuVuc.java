package com.nguyenvanhoang.foodapp.entities;

import java.io.Serializable;

public class KhuVuc implements Serializable {
    private String keyID;
    private String tenKhuVuc;

    public KhuVuc(String keyID, String tenKhuVuc) {
        this.keyID = keyID;
        this.tenKhuVuc = tenKhuVuc;
    }

    public KhuVuc() {
        super();
    }

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getTenKhuVuc() {
        return tenKhuVuc;
    }

    public void setTenKhuVuc(String tenKhuVuc) {
        this.tenKhuVuc = tenKhuVuc;
    }
}
