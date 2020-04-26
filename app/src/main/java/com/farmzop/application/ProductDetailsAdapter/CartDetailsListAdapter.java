package com.farmzop.application.ProductDetailsAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.farmzop.application.CartDBHelper.CartDBAdapter;
import com.farmzop.application.R;
import com.farmzop.application.SharedPreferencesHelper;
import com.farmzop.application.SpinnerAdapter.CustomSpinnerAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav on 27-03-2016.
 */

//TODO: make a common shared preferences for all the activities
public class CartDetailsListAdapter extends BaseAdapter{

    private String stringVal;
    public static int cartSize=0;
    TextView cartBadgeCount;
    ArrayList<ProductItemType1> addedCartItem;
    RelativeLayout cartBottomLayout;

    Context context;
    List<ProductItemType1> rowItem;

    public CartDetailsListAdapter(Context context, List<ProductItemType1> rowItem,RelativeLayout cartBottom) {
        this.context = context;
        this.rowItem = rowItem;

        cartSize = SharedPreferencesHelper.getNumCartItemsSharedPref(context);
        addedCartItem=new ArrayList<>();


    }

    @Override
    public int getCount() {

        return rowItem.size();
    }

    @Override
    public Object getItem(int position) {

        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position) {

        return rowItem.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ProductItemType1 row_pos = rowItem.get(position);
        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();

            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.sub_tab_list1, null);

            holder.imgIcon = (ImageView) convertView.findViewById(R.id.product_image);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.product_title);
            holder.txt_mrp = (TextView) convertView.findViewById(R.id.product_mrp);
            holder.txt_our_price = (TextView) convertView.findViewById(R.id.product_our_price);
            holder.txtDesc = (TextView) convertView.findViewById(R.id.product_brand);
            holder.txt_qty = (TextView) convertView.findViewById(R.id.product_quantity);

            holder.btn_add = (ImageButton)  convertView.findViewById(R.id.product_add_item_btn);
            holder.btn_remove = (ImageButton)  convertView.findViewById(R.id.product_remove_item_btn);
            holder.spinnerBag = (Spinner) convertView.findViewById(R.id.bag_spinner);

            convertView.setTag(holder);
        }

        holder = (ViewHolder)convertView.getTag();

        holder.txt_mrp.setPaintFlags(holder.txt_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        final ViewHolder finalHolder = holder;
        holder.btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("src", "Increasing value...");

                int nCartItems=Integer.valueOf(row_pos.getProductQty());
                nCartItems=nCartItems+1;
                row_pos.setProductQty(String.valueOf(nCartItems));

                //increment the number of cart items only for unique item added to cart
                if(nCartItems==1) {
                    cartSize++;

                    //update the shared prefs for new cart count
                    SharedPreferencesHelper.setNumCartItemsSharedPref(context,cartSize);


                    //create a row (add the item) into the cart database
                    addCartItemDB(row_pos);
                }
                if(nCartItems>1)
                {
                    //updates already existing record to the latest
                    updateCartItemDB(row_pos.getProductId(),Integer.valueOf(row_pos.getProductQty()));
                }


                finalHolder.txt_qty.setText(String.valueOf(nCartItems));
            }});

        holder.btn_remove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("src", "Decreasing value...");

                int nCartItems=Integer.valueOf(row_pos.getProductQty());
                finalHolder.txt_qty.setText(String.valueOf(nCartItems));

                //ensure cart items more than 0 before decrement
                if(nCartItems>0) {
                    nCartItems-=1;

                    row_pos.setProductQty(String.valueOf(nCartItems));

                    if(nCartItems==0) {
                        cartSize--;
                        //update the shared prefs for new cart count
                        SharedPreferencesHelper.setNumCartItemsSharedPref(context, cartSize);

                        //delete record(this item) from the cart database
                        removeCartItemDB(row_pos.getProductId());

                    }
                    else
                    {
                        //updates already existing record to the latest
                        updateCartItemDB(row_pos.getProductId(),Integer.valueOf(row_pos.getProductQty()));

                    }

                }

                finalHolder.txt_qty.setText(String.valueOf(nCartItems));
            }
        });


        // setting the image resource and title
        holder.txtTitle.setText(row_pos.getProductTitle());

        holder.txtDesc.setText(row_pos.getProductBrand());

        holder.txt_mrp.setText("\u20B9"+row_pos.getMarketPrice());
        holder.txt_our_price.setText("\u20B9"+row_pos.getOurPrice());
        holder.txt_qty.setText(row_pos.getProductQty());

        holder.imgIcon.setImageResource(R.drawable.atta);

        // holder.btn_add.setImageResource(row_pos.getAddProductButton());
        //holder.btn_remove.setImageResource(row_pos.getRemoveProductButton());
        // holder.txtBag.setText(row_pos.getBag());

        //add items to spinner list
        //set the list of cities in the dropdown
        String[] cities=new String[]{"1 Kg","2 Kg","5 Kg","10 Kg"};
        String defaultText="Select Pouch";

        //initialize the adapter for it
        CustomSpinnerAdapter spinnerAdapter=new CustomSpinnerAdapter(context, R.layout.dropdown_spinner_row,cities,defaultText);
        //set the adapter
        holder.spinnerBag.setAdapter(spinnerAdapter);

        return convertView;


    }

    private void updateCartItemDB(int pid,int qty) {

        //open the database to read the details of the cart items
        CartDBAdapter mDBHelper = new CartDBAdapter(context);
        try {
            mDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //updates the product to db
        mDBHelper.updateQuantity(pid,qty);

        //close the db
        if(mDBHelper != null){
            mDBHelper.close();
        }
    }


    private class ViewHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txt_mrp;
        TextView txt_our_price;
        TextView txtDesc;
        TextView txt_qty;
        ImageButton btn_add;
        ImageButton btn_remove;
        Spinner spinnerBag;
    }

    /**
     * @return no of items on the cart
     */
    public int getCartSize()
    {
        return cartSize;
    }

    void addCartItemDB(ProductItemType1 tmp)
    {
        //open the database to read the details of the cart items
        CartDBAdapter mDBHelper = new CartDBAdapter(context);
        try {
            mDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //creates and adds the product to db
        mDBHelper.createProduct(tmp.getProductId(),tmp.getProductTitle(),tmp.getMarketPrice(),
                tmp.getOurPrice(),tmp.getProductBrand(),tmp.getProductQty(),R.drawable.wheat
        );

        //close the db
        if(mDBHelper != null){
            mDBHelper.close();
        }
    }

    void removeCartItemDB(int pid)
    {
        //open the database to read the details of the cart items
        CartDBAdapter mDBHelper = new CartDBAdapter(context);
        try {
            mDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //creates and adds the product to db
        mDBHelper.deleteRowItem(pid);


        //close the db
        if(mDBHelper != null){
            mDBHelper.close();
        }
    }



}
