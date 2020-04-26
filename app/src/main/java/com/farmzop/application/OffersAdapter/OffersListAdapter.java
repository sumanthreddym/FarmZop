package com.farmzop.application.OffersAdapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.farmzop.application.ImageSlideShow.CirclePageIndicator;
import com.farmzop.application.ImageSlideShow.ImageSlideAdapter;
import com.farmzop.application.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Gaurav on 01-03-2016.
 */
public class OffersListAdapter extends ArrayAdapter<OffersItem> {

    Context context;

    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    public OffersListAdapter(Context context, List<OffersItem> objects) {
        super(context, 0, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        OffersItem temp=getItem(position);
        if(temp.getType()==0)
        {
            convertView=getOfferType1View(temp,convertView,parent);
        }
        else if (temp.getType()==1)
        {
            convertView=getOfferType2View(temp, convertView, parent);
        }
        else
        {
            convertView=getOfferTypeSliderView(temp, convertView, parent);
        }


        // Return the completed view to render on screen
        return convertView;

    }

    private View getOfferTypeSliderView(OffersItem temp, View convertView, ViewGroup parent) {

        // Get the data item for this position
       OffersItemTypeMain tmp =(OffersItemTypeMain)temp;
      ViewHolderTypeMain holder;


        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.individual_offers_type_main, parent, false);

            holder = new ViewHolderTypeMain();
            // Lookup view for data population
            holder.image=(ImageView)convertView.findViewById(R.id.offer_of_the_day_image);

            convertView.setTag(holder);
        }

          holder = (ViewHolderTypeMain) convertView.getTag();

        holder.image.setImageBitmap(tmp.getImages());

        return convertView;
    }

    private View getOfferType1View(OffersItem temp, View convertView, ViewGroup parent) {


        // Get the data item for this position
        OffersItemType1 tmp =(OffersItemType1)temp;
        ViewHolderType1 holder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.individual_offers_type1, parent, false);

            holder = new ViewHolderType1();
            // Lookup view for data population
            holder.img = (ImageView) convertView.findViewById(R.id.offers_image_type1);

            convertView.setTag(holder);
        }
        holder = (ViewHolderType1) convertView.getTag();

        // Populate the data into the template view using the data object
        holder.img.setImageBitmap(tmp.getImage());

    return convertView;
     }

    private View getOfferType2View(OffersItem temp, View convertView, ViewGroup parent) {


        // Get the data item for this position
        OffersItemType2 tmp =(OffersItemType2)temp;
        ViewHolderType2 holder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.individual_offers_type2, parent, false);

            holder = new ViewHolderType2();
            // Lookup view for data population
            holder.imgL = (ImageView) convertView.findViewById(R.id.left_image_offerstype2);
            holder.imgR = (ImageView) convertView.findViewById(R.id.right_image_offerstype2);


            convertView.setTag(holder);
        }
        holder = (ViewHolderType2) convertView.getTag();

        // Populate the data into the template view using the data object
        holder.imgL.setImageBitmap(tmp.getImageLeft());
        holder.imgR.setImageBitmap(tmp.getImageRight());

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return this.getItem(position).getType();
    }

    class ViewHolderType1 {
        ImageView img;
    }

    class ViewHolderType2 {
        ImageView imgL;
        ImageView imgR;
    }
    static class ViewHolderTypeMain {
     ImageView image;
    }



}