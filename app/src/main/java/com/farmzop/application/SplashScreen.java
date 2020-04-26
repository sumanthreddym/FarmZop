package com.farmzop.application;
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ProgressBar;


public class SplashScreen extends Activity {

    private ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        loading=(ProgressBar) findViewById(R.id.progressBar);
        loading.setVisibility(View.VISIBLE);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    //decide which activity to open Locality or Main

                    int tmp=SharedPreferencesHelper.getNumCartItemsSharedPref(getApplicationContext());

                    if(tmp==0) {
                        Intent intent = new Intent(SplashScreen.this, LocalitySelectionActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}