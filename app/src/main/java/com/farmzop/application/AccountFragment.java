package com.farmzop.application;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by Gaurav on 06-01-2016.
 */
public class AccountFragment extends Fragment implements View.OnClickListener{

    private ProgressBar spinnerProgress;

    private TextView counterText;
    private TextView headerText;
    private TextView infoText;

    private EditText phNo;

    private ImageView nextButton;
    private RelativeLayout numLayout;
    private RelativeLayout verLayout;
    EditText activationCode;

    View rootView;

    private long mobileNum;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.mobile_verification_layout, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            nextButton=(ImageView) getView().findViewById(R.id.rightArrow);
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
        numLayout=(RelativeLayout)getView().findViewById(R.id.number_entry_layout);
        phNo=(EditText)getView().findViewById(R.id.phone_number_editable);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //get ph no. string from the editable text and convert to int
                mobileNum=Long.valueOf(phNo.getText().toString());

                //change the layout view
                toggleView();
            }
        });


    }

    private void  toggleView(){
        numLayout.setVisibility(View.GONE);

        //counterText=(TextView)getView().findViewById(R.id.countdown_timer);

        verLayout=(RelativeLayout) getView().findViewById(R.id.verification_awaiting_layout);

        //get the references to the editText,verify button, and bottom texts and set onClickListeners
        activationCode=(EditText)rootView.findViewById(R.id.activation_code_edit_field);

        rootView.findViewById(R.id.verify_code_button).setOnClickListener(this);
        rootView.findViewById(R.id.resend_text).setOnClickListener(this);
        rootView.findViewById(R.id.no_code_text).setOnClickListener(this);

       //set the visibility
        verLayout.setVisibility(View.VISIBLE);

        //counterText.setVisibility(View.VISIBLE);

        //change the header and info text
        headerText=(TextView)getView().findViewById(R.id.mob_verification_title);
        infoText=(TextView)getView().findViewById(R.id.mob_verification_info_text);

        headerText.setText("Your Number is being Verified ");
        infoText.setText("We've sent an OTP to "+ String.valueOf(mobileNum)+"via SMS.\nPlease wait while we do Automatic Verification.");

        spinnerProgress=(ProgressBar)getView().findViewById(R.id.progressBar);

        //change the color of spinner progress to color code #58c672(green)
        spinnerProgress.getIndeterminateDrawable().setColorFilter(0xFF58c672,
                android.graphics.PorterDuff.Mode.MULTIPLY);
        spinnerProgress.setVisibility(View.VISIBLE);

      /*  //creates counter
        createCountDownTimer();
      */

    }

    /**
     * Creates a countdown timer which counts for 60 seconds
     */
    void createCountDownTimer()
    {
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                counterText.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                counterText.setText("0");
            }
        }.start();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.verify_code_button:
                Toast.makeText(getActivity(), "Verify Button Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.no_code_text:
                Toast.makeText(getActivity(), "No Code Text Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.resend_text:
                Toast.makeText(getActivity(), "Resend Text Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
