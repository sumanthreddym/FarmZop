package com.farmzop.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.farmzop.application.CartDBHelper.CartDBAdapter;
import com.farmzop.application.ProductDetailsAdapter.CartDetailsListAdapter;
import com.farmzop.application.ProductDetailsAdapter.ProductDetailsListAdapter;
import com.farmzop.application.ProductDetailsAdapter.ProductItemType1;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gaurav on 25-03-2016.
 */
public class CartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cart_items_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        RelativeLayout checkoutBtn=(RelativeLayout)findViewById(R.id.proceed_checkout);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCheckoutActivity();
            }
        });


    }



    @Override
    protected void onStart() {
        super.onStart();

        //initialize the cart
        initCart();
    }

    void initCart()
    {
        //open the database to read the details of the cart items
        CartDBAdapter mDBHelper = new CartDBAdapter(CartActivity.this);
        try {
            mDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get the details of the cart items
        ArrayList<ProductItemType1> cartItems=mDBHelper.getAllCartItems();

        TextView cartCountText=(TextView)findViewById(R.id.cart_count);

        if(cartItems!=null)
        {
            CartDetailsListAdapter adapter = new CartDetailsListAdapter(getApplicationContext(),cartItems,null);
            ListView listItemsView=(ListView)findViewById(R.id.cart_item_list);

            listItemsView.setAdapter(adapter);
            Log.d("cart", listItemsView.toString());
        }

        //close the db
        if(mDBHelper != null){
            mDBHelper.close();
        }

    }

    void openCheckoutActivity()
    {
        Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
        startActivity(intent);
    }

}