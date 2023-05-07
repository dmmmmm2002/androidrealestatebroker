package com.example.realestatebroker;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class RealEstateRVModel{
    private String realEstateName;
    private String realEstatePrice;
    private String realEstateLocation;
    private String realEstateImageLink;
    private String realEstateRooms;
    private String realEstateDesc;
    private String id;

    public RealEstateRVModel() {
    }

    public RealEstateRVModel(String id, String realEstateName, String realEstatePrice, String realEstateLocation, String realEstateImageLink, String realEstateRooms, String realEstateDesc) {
        this.id = id;
        this.realEstateName = realEstateName;
        this.realEstatePrice = realEstatePrice;
        this.realEstateLocation = realEstateLocation;
        this.realEstateImageLink = realEstateImageLink;
        this.realEstateRooms = realEstateRooms;
        this.realEstateDesc = realEstateDesc;
    }

    public String getRealEstateName() {
        return realEstateName;
    }

    public void setRealEstateName(String realEstateName) {
        this.realEstateName = realEstateName;
    }

    public String getRealEstatePrice() {
        return realEstatePrice;
    }

    public void setRealEstatePrice(String realEstatePrice) {
        this.realEstatePrice = realEstatePrice;
    }

    public String getRealEstateLocation() {
        return realEstateLocation;
    }

    public void setRealEstateLocation(String realEstateLocation) {
        this.realEstateLocation = realEstateLocation;
    }

    public String getRealEstateImageLink() {
        return realEstateImageLink;
    }

    public void setRealEstateImageLink(String realEstateImageLink) {
        this.realEstateImageLink = realEstateImageLink;
    }

    public String getRealEstateRooms() {
        return realEstateRooms;
    }

    public void setRealEstateRooms(String realEstateRooms) {
        this.realEstateRooms = realEstateRooms;
    }

    public String getRealEstateDesc() {
        return realEstateDesc;
    }

    public void setRealEstateDesc(String realEstateDesc) {
        this.realEstateDesc = realEstateDesc;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

}
