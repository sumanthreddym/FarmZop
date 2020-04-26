package com.farmzop.application.ProductDetailsAdapter;

import java.util.ArrayList;

public class ProductItemType1 {

    private int productId;
    private String productImageName;
    private String productTitle;
    private int icon;
    private String productBrand;
    private String marketPrice;
    private String ourPrice;

    private String productQty;

    private int addProductButton;
    private int removeProductButton;
    private ArrayList<String> pouchSizes;

    String bag;

    public ProductItemType1()
    {
        this.productBrand="Brand_Name";
        this.bag="Select Pouch";
        this.productQty="0";
    }

    public ProductItemType1(String productTitle, String marketPrice, String ourPrice, int icon, String desc, String productQty, int addProductButton, int removeProductButton, String bag) {
        this.productTitle = productTitle;
        this.icon = icon;
        this.productBrand=desc;
        this.marketPrice=marketPrice;
        this.ourPrice=ourPrice;

        this.productQty=productQty;

        this.addProductButton=addProductButton;
        this.removeProductButton=removeProductButton;
        this.bag=bag;

    }


    public int getAddProductButton() {
        return addProductButton;
    }

    public void setAddProductButton(int addProductButton) {
        this.addProductButton = addProductButton;
    }

    public int getRemoveProductButton() {
        return removeProductButton;
    }

    public void setRemoveProductButton(int removeProductButton) {
        this.removeProductButton = removeProductButton;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductImageName() {
        return productImageName;
    }

    public void setProductImageName(String productImageName) {
        this.productImageName = productImageName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(String ourPrice) {
        this.ourPrice = ourPrice;
    }

    public ArrayList<String> getPouchSizes() {
        return pouchSizes;
    }

    public void setPouchSizes(ArrayList<String> pouchSizes) {
        this.pouchSizes = pouchSizes;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getBag() {
        return bag;
    }

    public void setBag(String bag) {
        this.bag = bag;
    }

}

