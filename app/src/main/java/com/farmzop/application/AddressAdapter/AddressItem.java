package com.farmzop.application.AddressAdapter;

/**
 * Created by Gaurav on 01-02-2016.
 */
public class AddressItem {

    String addressTypeName;
    String houseNumber;
    String streetName;
    String areaName;
    String cityName;

    public void setAddressTypeName(String addressTypeName) {
        this.addressTypeName = addressTypeName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAddressTypeName() {

        return addressTypeName;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }
}
