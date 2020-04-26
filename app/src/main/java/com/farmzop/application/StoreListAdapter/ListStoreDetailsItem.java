package com.farmzop.application.StoreListAdapter;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Gaurav on 07-02-2016.
 */
public class ListStoreDetailsItem {



    private int storeId;

    private Bitmap image;
    private String area;
    private String address;
    private String timings;
    private long  contact;
    private LatLng position;


    public ListStoreDetailsItem(int storeId ,Bitmap image,  String area, String address, String timings, long contact, LatLng position ) {
        this.address = address;
        this.area = area;
        this.contact = contact;
        this.image = image;
        this.position = position;
        this.storeId = storeId;
        this.timings = timings;
    }

    public String getAddress() {
        return address;
    }

    public String getArea() {
        return area;
    }

    public long getContact() {
        return contact;
    }

    public Bitmap getImage() {
        return image;
    }

    public LatLng getPosition() {
        return position;
    }

    public String getTimings() {
        return timings;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getStoreId() {

        return storeId;
    }
}
