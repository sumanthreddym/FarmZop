package com.farmzop.application.NonFarmzopProductAdapter;

import android.graphics.Bitmap;

/**
 * Created by Gaurav on 21-01-2016.
 */
public class ProductInfo {
    private String name;
    private Bitmap image;

    public ProductInfo( String name,Bitmap image) {
        this.image = image;
        this.name = name;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImage() {
        return image;
    }
}
