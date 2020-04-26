package com.farmzop.application.AddressAdapter;

/**
 * Created by Gaurav on 23-01-2016.
 */
public class AddressDetails {

    private String addressType;
    private String addressContents;
    private int addressId;


    private boolean isSelected;

    public AddressDetails(int id, String addressType,String addressContents, boolean isSelected) {
        this.addressContents = addressContents;
        this.addressType = addressType;
        this.isSelected = isSelected;
        this.addressId=id;
    }

    public void setAddressContents(String addressContents) {
        this.addressContents = addressContents;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getAddressContents() {

        return addressContents;
    }

    public String getAddressType() {
        return addressType;
    }

    public boolean isSelected() {
        return isSelected;
    }


    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getAddressId() {

        return addressId;
    }
}
