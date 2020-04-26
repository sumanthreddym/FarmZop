package com.farmzop.application.OffersAdapter;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Gaurav on 01-03-2016.
 */
public class OffersItemTypeMain implements OffersItem{

    private static final int TYPE = 2 ;//2 for type slider

    private int id;
    private Bitmap image;




    public OffersItemTypeMain(int id,Bitmap image) {
        this.image = image;
        this.id = id;
    }

    public Bitmap getImages() {
        return image;
    }

    public void setImages(Bitmap image) {
        this.image = image;
    }

    @Override
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
