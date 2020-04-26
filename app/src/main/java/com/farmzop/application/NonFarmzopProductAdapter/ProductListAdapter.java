package com.farmzop.application.NonFarmzopProductAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.farmzop.application.R;

import java.util.List;

/**
 * Created by Gaurav on 21-01-2016.
 */
public class ProductListAdapter  extends ArrayAdapter<ProductInfo> {

    public ProductListAdapter(Context context,List<ProductInfo> objects) {
        super(context,0,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ProductInfo temp = getItem(position);
        ViewHolder holder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.non_farmzop_product_image, parent, false);

            holder = new ViewHolder();
            // Lookup view for data population
            holder.img = (ImageView) convertView.findViewById(R.id.non_farmzop_category_image);
            holder.name=(TextView) convertView.findViewById(R.id.non_farmzop_category_name);
            convertView.setTag(holder);
        }
        holder=(ViewHolder)convertView.getTag();

        // Populate the data into the template view using the data object
        holder.name.setText(temp.getName());
        holder.img.setImageBitmap( temp.getImage());

        // Return the completed view to render on screen
        return convertView;

    }


  public static class ViewHolder
  {
      ImageView img;
      TextView name;
  }


}
