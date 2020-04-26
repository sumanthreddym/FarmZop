package com.farmzop.application;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Gaurav on 07-02-2016.
 */
public class FindStoreDetailsItem {

    private int storeId;
    private String area;
    private LatLng position;


    private float distFromCurrentPos;

    public FindStoreDetailsItem(int storeId,String area, LatLng position ) {
        this.area = area;
        this.position = position;
        this.storeId = storeId;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getArea() {
        return area;
    }

    public LatLng getPosition() {
        return position;
    }

    public int getStoreId() {
        return storeId;
    }


    public float getDistFromCurrentPos() {
        return distFromCurrentPos;
    }

    public void setDistFromCurrentPos(float distFromCurrentPos) {
        this.distFromCurrentPos = distFromCurrentPos;
    }
}
