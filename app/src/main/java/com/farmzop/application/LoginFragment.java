package com.farmzop.application;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;


import com.farmzop.application.AddressAdapter.AddressDetails;
import com.farmzop.application.AddressAdapter.AddressItem;
import com.farmzop.application.AddressAdapter.AddressListAdapter;
import com.farmzop.application.SpinnerAdapter.CustomSpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by Gaurav on 23-01-2016.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    ArrayList<AddressDetails> addressDetails;
    LayoutInflater inflater;
    ImageView addAddressButton;
    View rootView;

    AddressItem addressItem;

    PopupWindow addressPopup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.loggedin_layout, container, false);

         this.inflater=inflater;
        return rootView;
    }

    @Override
    public void onStart() {

         super.onStart();
        addressDetails=new ArrayList<>();
        addressDetails.add(0,new AddressDetails(101,"Home","45, 8th Cross,Marathalli,Bangalore",true));
        addressDetails.add(1,new AddressDetails(102,"Office","78, 4th Cross,Marathalli,Bangalore",false));

        //instansiate addressItem
        addressItem = new AddressItem();

        //get reference to logout button
        Button logoutBtn=(Button)getActivity().findViewById(R.id.logout_button);
        logoutBtn.setOnClickListener(this);

        //find the view for add address button
        addAddressButton=(ImageView)getView().findViewById(R.id.new_address_button);
        addAddressButton.setOnClickListener(this);


        AddressListAdapter addressListAdapter=new AddressListAdapter(getActivity(),addressDetails);

        //setting up adapter for listView
        ListView list = (ListView)getActivity().findViewById(R.id.saved_address_list);
        list.setAdapter(addressListAdapter);


    }

    /**
     * Creates a PopUp window to get the details of new adress to be added
     */

    private void createAddressPopup()
    {
       final View popupLayout=inflater.inflate(R.layout.address_details_layout,null);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        addressPopup = new PopupWindow(popupLayout,metrics.widthPixels,metrics.heightPixels,true);
        addressPopup.showAtLocation(popupLayout, Gravity.CENTER,0,0);


        addressPopup.setTouchable(true);
        addressPopup.setFocusable(true);

        //set the list of cities in the dropdown
        String[] cities=new String[]{"Bangalore","Mysore","Chennai","Delhi","Mumbai","Kolkata"};
        String defaultText="Select City";

        //get the reference to the city spinner view from the popup view
        final Spinner citySpinner=(Spinner)popupLayout.findViewById(R.id.city_select_spinner);
        //initialize the adapter for it
        CustomSpinnerAdapter spinnerAdapter=new CustomSpinnerAdapter(popupLayout.getContext(), R.layout.dropdown_spinner_row,cities,defaultText);
        //set the adapter
        citySpinner.setAdapter(spinnerAdapter);

        //get references to the editText views of address fields
        final EditText addressTitle=(EditText)popupLayout.findViewById(R.id.address_type);
        final EditText hno=(EditText)popupLayout.findViewById(R.id.house_number);
        final EditText streetName=(EditText)popupLayout.findViewById(R.id.street_name);
        final EditText areaName=(EditText)popupLayout.findViewById(R.id.area_name);


        //check for the click on the save button to close the window
        Button saveButton=(Button)popupLayout.findViewById(R.id.save_address_details);
        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                //check if all fields have been entered
                if (isAddressFieldComplete()&&(!isAddressTypeAlreadyExists())) {
                    //if all field are filled then save them to Adress Objects
                    saveAddressField(addressTitle.getText().toString(),
                            hno.getText().toString(),
                            streetName.getText().toString(),
                            areaName.getText().toString(),
                            citySpinner.getSelectedItem().toString()
                    );

                    //display them
                    Toast.makeText(getActivity(),addressItem.getAddressTypeName()+"\n"+addressItem.getHouseNumber()+", "
                                    +addressItem.getStreetName()+"\n"+addressItem.getAreaName()+", "+addressItem.getCityName(),
                            Toast.LENGTH_SHORT
                    ).show();

                    //after saving the contents dismiss popup
                    addressPopup.dismiss();

                }
            }
        });

        //check for the click on the cancel button to close the window
        Button cancelButton=(Button)popupLayout.findViewById(R.id.cancel_popup);
                cancelButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {

                        addressPopup.dismiss();
                    }
                });




    }

    /**
     * This method checks whether the address type name entered already exits
     * @return true if address type name already exists
     * @return false if address type name not existing already
     */
    private boolean isAddressTypeAlreadyExists() {

        return false;
    }

    /**
     * This method saves the details of addresses into the AddressItem object
     */
    private void saveAddressField(String type,String hno,String street,String area,String city) {

        //set the attributes of addressItem
        addressItem.setAddressTypeName(type);
        addressItem.setHouseNumber(hno);
        addressItem.setStreetName(street);
        addressItem.setAreaName(area);
        addressItem.setCityName(city);

    }


    /**
     * This method checks whether the fields of address are all filled or not
     * @return true if all fields filled
     * @return false one of the fields not filled
     */
    private boolean isAddressFieldComplete() {

        //presently it will always return true
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
          case  R.id.logout_button:
              Toast.makeText(getActivity(),"Logout Clicked",Toast.LENGTH_SHORT).show();
            break;

           case R.id.new_address_button:
            createAddressPopup();
            break;
        }
    }
}