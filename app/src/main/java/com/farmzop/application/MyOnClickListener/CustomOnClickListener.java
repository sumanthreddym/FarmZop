package com.farmzop.application.MyOnClickListener;

import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Created by Gaurav on 21-03-2016.
 */
public class CustomOnClickListener implements View.OnClickListener {

    private int position;
    private OnCustomClickListener callback;

    // Pass in the callback (this'll be the activity) and the row position
    public CustomOnClickListener(OnCustomClickListener callback, int pos) {
        position = pos;
        this.callback = callback;
    }



    // The onClick method which has NO position information
    @Override
    public void onClick(View v) {

        // Let's call our custom callback with the position we added in the constructor
        callback.OnCustomClick(v, position);
    }

}
