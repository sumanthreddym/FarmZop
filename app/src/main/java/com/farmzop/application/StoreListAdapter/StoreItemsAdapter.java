package com.farmzop.application.StoreListAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.farmzop.application.R;

import java.util.List;

/**
 * Created by Gaurav on 07-02-2016.
 */
public class StoreItemsAdapter extends ArrayAdapter<ListStoreDetailsItem> implements View.OnClickListener {



    public StoreItemsAdapter(Context context, int resource, List<ListStoreDetailsItem> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListStoreDetailsItem temp=getItem(position);
        ViewHolder holder = null;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.store_individual_item_layout, parent, false);

        holder=new ViewHolder();

        // Lookup view for data population
        holder.area = (TextView) convertView.findViewById(R.id.offline_store_area);
        holder.address = (TextView) convertView.findViewById(R.id.offline_store_address);
        holder.contact = (TextView) convertView.findViewById(R.id.offline_stores_contact);
        holder.timings = (TextView) convertView.findViewById(R.id.offline_store_timmings);
       holder.image = (ImageView) convertView.findViewById(R.id.offline_store_image);
        holder.callButton=(Button)convertView.findViewById(R.id.call_store_button);
        holder.directionsButton=(Button)convertView.findViewById(R.id.direction_button_offline);
         convertView.setTag(holder);
        }
        holder=(ViewHolder)convertView.getTag();

        //set the onclick listeners for the buttons
        holder.directionsButton.setOnClickListener(this);
        holder.callButton.setOnClickListener(this);

        // Populate the data into the template view using the data object
        holder.area.setText(temp.getArea());
        holder.address.setText(temp.getAddress());
        holder.contact.setText("Contact No. : " + String.valueOf(temp.getContact()));
        holder.timings.setText("Timings: "+temp.getTimings());
        holder.image.setImageBitmap(temp.getImage());

        //used to tag the buttons with the object used by them so as we can use the item associated with it later
        holder.callButton.setTag(temp);
        holder.directionsButton.setTag(temp);
        return convertView;
    }

    private class ViewHolder{
        TextView area ;
        TextView address;
        TextView contact;
        TextView timings ;
        ImageView image ;
        Button callButton;
        Button directionsButton;
    }

    //invoked when current row view clicked
    @Override
    public void onClick(View v) {

        //get the item positon by its tag and use the position to get reference to the items

        ListStoreDetailsItem tempItem=(ListStoreDetailsItem)v.getTag();

        switch (v.getId()) {
            case R.id.call_store_button:
                //intent used to create calls
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + tempItem.getContact()));
                //used to call intent outside main activity
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                getContext().startActivity(callIntent);
                 Toast.makeText(getContext(),"Call Button clicked("+tempItem.getContact()+")",Toast.LENGTH_SHORT).show();
                break;

            case R.id.direction_button_offline:
                //intent used to open maps nad get directions
                // Origin of route
                String str_origin ="";
                // Destination of route
                String str_dest = tempItem.getPosition().latitude + "," + tempItem.getPosition().longitude;
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + str_origin + "&daddr=" + str_dest));
               //used to call intent outside main activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                getContext().startActivity(intent);
                //content used to get directions
                Toast.makeText(getContext(),"Directions Button clicked("+tempItem.getPosition().toString()+")",Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),"Enter Your Orgin Address",Toast.LENGTH_LONG).show();
                break;
        }

    }



}
