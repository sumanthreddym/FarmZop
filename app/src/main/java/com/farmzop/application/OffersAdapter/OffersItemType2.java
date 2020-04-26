package com.farmzop.application.OffersAdapter;

import android.graphics.Bitmap;

/**
 * Created by Gaurav on 03-03-2016.
 */
public class OffersItemType2 implements OffersItem{

        private static final int TYPE = 1 ;//1 for type 2
        private int id;
        private Bitmap imageLeft;
        private Bitmap imageRight;


    public OffersItemType2(int id, Bitmap imageL, Bitmap imageR) {
            this.imageLeft = imageL;
            this.imageRight = imageR;
            this.id = id;
        }

    public Bitmap getImageRight() {
        return imageRight;
    }

    public void setImageRight(Bitmap imageRight) {
        this.imageRight = imageRight;
    }

    public Bitmap getImageLeft() {
        return imageLeft;
    }

    public void setImageLeft(Bitmap imageLeft) {
        this.imageLeft = imageLeft;
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


