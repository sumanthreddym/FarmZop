package com.farmzop.application.AddressAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.farmzop.application.R;

import java.util.List;

/**
 * Created by Gaurav on 23-01-2016.
 *
 * TODO: properly initialize the buttons
 */
public class AddressListAdapter extends ArrayAdapter<AddressDetails> implements View.OnClickListener {

    View view;
   int selectedAddressIndex;

    RadioButton checkedRadioButton;

    public AddressListAdapter(Context context, List<AddressDetails> objects) {
        super(context, 0, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AddressDetails temp = getItem(position);
        ViewHolder holder ;

        view=convertView;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.individual_address_layout, parent, false);

            holder = new ViewHolder();

        // Lookup view for data population
        holder.head = (TextView) convertView.findViewById(R.id.address_title);
        holder.contents = (TextView) convertView.findViewById(R.id.address_contents);
        holder.radioButton=(RadioButton)convertView.findViewById(R.id.selected_address);
        holder.editButton=(Button)convertView.findViewById(R.id.edit_address_btn);

        convertView.setTag(holder);
        }
        holder=(ViewHolder)convertView.getTag();

        //set tag for the radio button with its associated Address object
        holder.radioButton.setTag(temp);

        // Populate the data into the template view using the data object
        holder.head.setText(temp.getAddressType());
        holder.contents.setText(temp.getAddressContents());

        //set click listener on the radio button
        holder.radioButton.setOnClickListener(this);
        holder.editButton.setOnClickListener(this);

        if(temp.isSelected()) {
            Log.d("SeletedPos", "Item checked object at position " + position);
            holder.radioButton.setChecked(true);
            //selectedAddressIndex=position;
            checkedRadioButton=holder.radioButton;

        }else {
            holder.radioButton.setChecked(false);
        }


        // Return the completed view to render on screen
        return convertView;

    }


    //View Holder used for improving the performance
    public static class ViewHolder {
        TextView head;
        TextView contents;
        RadioButton radioButton;
        Button editButton;
    }

    @Override
    public void onClick(View v) {



        switch (v.getId()) {

            case R.id.selected_address:
                changeSelectedAddressItem(v);
                break;

            case R.id.edit_address_btn:
                Toast.makeText(getContext(),"Edit Address Button Clicked",Toast.LENGTH_SHORT).show();
                break;

        }
        }

    void changeSelectedAddressItem(View v)
    {
        AddressDetails newAD=(AddressDetails)v.getTag();

        //remove the current selected address and update the Address Item associated with it
        AddressDetails currentAD=(AddressDetails)checkedRadioButton.getTag();
        currentAD.setIsSelected(false);

        if(checkedRadioButton!=null) {
            //change the selected radio button
            checkedRadioButton.setChecked(false);
        }

        //make new radio button checked button
        RadioButton tmpRB=(RadioButton)v;
        tmpRB.setChecked(true);
        checkedRadioButton=tmpRB;
        //change it to new selected address
        newAD.setIsSelected(true);

        Toast.makeText(getContext(), "Radio Button Clicked\nNew:"+newAD.getAddressId()+"\nOld:"+currentAD.getAddressId(), Toast.LENGTH_SHORT).show();

    }
}