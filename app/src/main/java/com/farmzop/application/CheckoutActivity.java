package com.farmzop.application;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.farmzop.application.SpinnerAdapter.CustomSpinnerAdapter;
import com.farmzop.application.R;

/**
 * Created by Gaurav on 06-01-2016.
 */

public class CheckoutActivity extends AppCompatActivity implements OnClickListener {


    String defaultTextForSpinner = "Select Your Time Slot";
    String[] arrayForSpinner = {"10:00 AM to 12:00 AM", "12:00 PM to 02:00 PM", "02:00 PM to 04:00 PM",
            "04:00 PM to 06:00 PM","06:00 PM to 08:00 PM","08:00 PM to 10:00 PM","10:00 PM to 12:00 AM"};

    String defaultTextAddressTypeSpinner="Choose Address";
    String[] addressOptions={"Home","Office","Default"};

    //saves the index of the time slot chosen
    int timeSlotIndex;

    //edit text for entering coupon code
    EditText couponCode;

    //edit text for address entry
    EditText checkoutAddress;

    //spinner for drop down menu
    Spinner dropdown_spinner;

    //references to relative layout
    RelativeLayout reviewOrder,reviewOrderTitle;
    RelativeLayout address,addressTitle;
    RelativeLayout options,optionsTitle;
    RelativeLayout payment,paymentTitle;
    RelativeLayout openLayout;

    //radiogroup
    RadioGroup radioGroupDay,radioGroupPaymentType;

    //object to store checkot details
    CheckoutItem checkoutItem;
    ImageView editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.checkout_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        //set checkout item
        checkoutItem=new CheckoutItem();

        //initialize edit text
        couponCode=(EditText)findViewById(R.id.checkout_coupon_text);
        checkoutAddress=(EditText)findViewById(R.id.checkout_address);

        //disable address editing till edit button clicked
        checkoutAddress.setEnabled(false);

        reviewOrderTitle=(RelativeLayout)findViewById(R.id.checkout_review_title_layout);
        reviewOrder=(RelativeLayout)findViewById(R.id.checkout_review_order_layout);

        addressTitle=(RelativeLayout)findViewById(R.id.checkout_address_title_layout);
        address=(RelativeLayout)findViewById(R.id.checkout_address_child);

        optionsTitle=(RelativeLayout)findViewById(R.id.checkout_options_title_layout);
        options=(RelativeLayout)findViewById(R.id.checkout_options_child);

        paymentTitle=(RelativeLayout)findViewById(R.id.checkout_payment_title_layout);
        payment=(RelativeLayout)findViewById(R.id.checkout_payment_child);

        //find the spinner view
        dropdown_spinner = (Spinner)findViewById(R.id.dropdown_time);

        dropdown_spinner.setAdapter(new CustomSpinnerAdapter(getApplicationContext(), R.layout.dropdown_spinner_row, arrayForSpinner, defaultTextForSpinner));


        //find the address options spinner view
       // dropdown_spinner = (Spinner)getView().findViewById(R.id.checkout_address_option_spinner);

       // dropdown_spinner.setAdapter(new CustomSpinnerAdapter(getActivity(), R.layout.dropdown_spinner_row,addressOptions, defaultTextAddressTypeSpinner));


        //initialize openLayout at begin to reviewOrder
        openLayout=reviewOrder;

        //set the onclick listeners
        reviewOrderTitle.setOnClickListener(this);
        optionsTitle.setOnClickListener(this);
        addressTitle.setOnClickListener(this);
        paymentTitle.setOnClickListener(this);

        //set onclick listner to Text button
        findViewById(R.id.apply_button).setOnClickListener(this);
        findViewById(R.id.button_proceed_to_address).setOnClickListener(this);
        findViewById(R.id.button_proceed_to_options).setOnClickListener(this);
        findViewById(R.id.button_proceed_to_payments).setOnClickListener(this);
        findViewById(R.id.button_place_order).setOnClickListener(this);

        //set onclick listners to images
       findViewById(R.id.checkout_address_add_button).setOnClickListener(this);

        //Set onclicklistners for radio group and radio buttons
        radioGroupDay=(RadioGroup)findViewById(R.id.radiogroup_day);
        radioGroupPaymentType=(RadioGroup)findViewById(R.id.radiogroup_payment_type);

        RadioGroup.OnCheckedChangeListener radioGroupListner= new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //set deliverydaycode and paymentmodeCode based on the radio buttons clicked
                switch (checkedId)
                {
                    case R.id.now_radio_button:
                        checkoutItem.setDeliveryDayCode(1);
                        Toast.makeText(getApplicationContext(),"Now radio Button clicked",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.today_radio_button:
                        Toast.makeText(getApplicationContext(), "today radio Button clicked", Toast.LENGTH_SHORT).show();
                        checkoutItem.setDeliveryDayCode(2);
                        break;

                    case R.id.tommrow_radio_button:
                        checkoutItem.setDeliveryDayCode(3);
                        Toast.makeText(getApplicationContext(), "Tomm radio Button clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.cod_radio_button:
                        Toast.makeText(getApplicationContext(), "COD radio Button clicked", Toast.LENGTH_SHORT).show();
                        checkoutItem.setPaymentModeCode(1);
                        break;

                    case R.id.online_radio_button:
                        checkoutItem.setPaymentModeCode(2);
                        Toast.makeText(getApplicationContext(), "Online mode radio Button clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        //set the listner for radio group
        radioGroupDay.setOnCheckedChangeListener(radioGroupListner);
        radioGroupPaymentType.setOnCheckedChangeListener(radioGroupListner);

    }

    /**
     * Used for slide Down animation
     * @param ctx Current context
     * @param v Current view
     */
    public static void slide_down(Context ctx, View v) {

        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down_anim);
        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }

    /**
     * Used for slide up animaion
     * @param ctx Current Context
     * @param v Current View
     */
    public static void slide_up(Context ctx, View v) {

        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_up_anim);
        if(a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }

    /**
     * This function is used to set visibility of  RelativeLayout passed as visible and make the current openLayout insvisible
     * @param temp It is  the reference of the RelativeLayout which should be made visible
     */
      public void toggle_contents(RelativeLayout temp){

          if(openLayout!=null)
          {
              openLayout.setVisibility(View.GONE);

          }

          if(temp.isShown()){
              slide_up(getApplicationContext(), temp);
              temp.setVisibility(View.GONE);
          }
          else{
                openLayout=temp;
              temp.setVisibility(View.VISIBLE);
              slide_down(getApplicationContext(), temp);
          }

  }


    /**
     * This function is used to listen to the clicks
     * @param v View on which listening should happen
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.checkout_review_title_layout:
                                                 //switch title colors
                                               toggle_contents(reviewOrder);
                                                break;
            case R.id.checkout_address_title_layout:
                                                toggle_contents(address);
                                                break;

            case R.id.checkout_options_title_layout:

                                                toggle_contents(options);
                                                break;
            case R.id.checkout_payment_title_layout:

                                                toggle_contents(payment);
                                                break;
            case R.id.apply_button:
                                  checkoutItem.setCouponCode(couponCode.getText().toString());

                                  Toast.makeText(getApplicationContext(), "Apply Button Clicked.\n"+"Coupon code: "+checkoutItem.getCouponCode(), Toast.LENGTH_LONG).show();
                                 break;

            case R.id.button_proceed_to_address:
                                            //// TODO: 26-01-2016 save the contents and appropriately process it
                                            //switch title colors
                                            switchTitleColors(reviewOrderTitle,addressTitle);
                                            //then chance the contents
                                             toggle_contents(address);
                                             break;

            case R.id.button_proceed_to_options:

                                            //// TODO: 26-01-2016 save the contents and appropriately process it

                                            //switch title colors
                                            switchTitleColors(addressTitle, optionsTitle);
                                            //then chance the contents
                                            toggle_contents(options);
                                            break;

            case R.id.button_proceed_to_payments:

                                            //// TODO: 26-01-2016 save the contents and appropriately process it
                                              checkoutItem.setTimeSlot(dropdown_spinner.getSelectedItem().toString());
                                            //switch title colors
                                            switchTitleColors(optionsTitle, paymentTitle);

                                            //then chance the contents
                                            toggle_contents(payment);
                                            break;
            case R.id.button_place_order:
                                                //// TODO: 26-01-2016 save the contents and process place order
                Toast.makeText(getApplicationContext(), "Button Clicked Place Order", Toast.LENGTH_LONG).show();
                       break;

            case R.id.checkout_address_add_button:
                Toast.makeText(getApplicationContext(),"Address Edit Button Clicked.",Toast.LENGTH_SHORT).show();
                editAddress();
                break;
        }
    }

    private void editAddress() {

        checkoutAddress.setEnabled(true);
    }

    private boolean isAddressEmpty()
    {
        if(checkoutAddress.getText().toString().matches(""))
            return true;

        return false;
    }

    private boolean isDeliverySlotEmpty()
    {
        if(dropdown_spinner.getSelectedItem().toString().matches(""))
        {

        }

        return false;
    }

    private boolean isCouponCodeEmpty()
    {
        if (couponCode.getText().toString().matches(""))
            return  true;

        return false;
    }
    /**
     * switchTiteColors() is used to change the background color of the RelativeLayout Passed
     * @param r1 Previous Active layout whose event is completed and color indication is green
     * @param r2 Current Active layout whose event is on going and color indication is blue
     */

    void switchTitleColors(RelativeLayout r1,RelativeLayout r2)
    {
        r1.setBackgroundResource(R.color.event_in_progress);
        r2.setBackgroundResource(R.color.event_in_progress);

    }


}
