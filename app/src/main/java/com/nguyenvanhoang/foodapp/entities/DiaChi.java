package com.nguyenvanhoang.foodapp.entities;

import java.io.Serializable;

public class DiaChi {
    private double latitude ;
    private double longtitude ;
    private String fullDiaChi;

    public DiaChi(double latitude, double longtitude, String fullDiaChi) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.fullDiaChi = fullDiaChi;
    }

    public DiaChi() {
        super();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }


    public String getFullDiaChi() {
        return fullDiaChi;
    }

    public void setFullDiaChi(String fullDiaChi) {
        this.fullDiaChi = fullDiaChi;
    }

    @Override
    public String toString() {
        return "DiaChi{" +
                "latitude=" + latitude +
                ", longtitude=" + longtitude +
                ", fullDiaChi='" + fullDiaChi + '\'' +
                '}';
    }
}
