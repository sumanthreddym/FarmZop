package com.farmzop.application.OffersAdapter;

import android.graphics.Bitmap;

/**
 * Created by Gaurav on 01-03-2016.
 */
public class OffersItemType1 implements OffersItem{

    private static final int TYPE = 0 ;//0 for type 1
    private int id;
    private Bitmap image;

    public OffersItemType1(int id, Bitmap image) {
        this.image = image;
        this.id = id;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    public void setId(int id) {
        this.id = id;
    }
}
