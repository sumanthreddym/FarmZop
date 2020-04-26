package com.farmzop.application.JsonParserAndPackager;

import android.annotation.TargetApi;
import android.os.Build;

import com.farmzop.application.OrdersDisplayAdapter.OrderDetails;
import com.farmzop.application.ProductDetailsAdapter.ProductItemType1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gaurav on 16-03-2016.
 */
public class CustomJsonParsers {

    /**
     * This Function is used to parse the contents recieved by the StringRequest
     * @param contents The contents that need to be parsed
     * @return tmp The result of the parsed contents as ArrayList<OrderDetails>
     */
   public static ArrayList<OrderDetails> parseOrdersObject(String contents)
    {

        JSONArray ar = null;
        ArrayList<OrderDetails> tmp = new ArrayList<>();

        try {
            ar = new JSONArray(contents);

            // go through each elements of string contents
            for(int i=0;i<ar.length();i++)
            {
                JSONObject obj = ar.getJSONObject(i);

                //Use the appropriate tags and data format to parse the objects
                /*
                OrderDetails orderDetails;
                orderDetails.setProductId(obj.getInt("productId"));
                orderDetails.setImageName(obj.getString("photo"));//gets the image names
                orderDetails.setTitle(obj.getString("name"));//gets the categories

                 tmp.add(i,orderDetails);
                */

            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return tmp;
    }

    /**
     * This Function is used to parse the contents recieved by the StringRequest
     * @param contents The contents that need to be parsed
     * @return tmp The result of the parsed contents as ArrayList<ProductItemType1>
     */

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static ArrayList<ProductItemType1> parseProductObject(String contents)
    {

        JSONArray ar = null;
        ArrayList<ProductItemType1> tmp = new ArrayList<>();

        try {
            ar = new JSONArray(contents);

            // go through each elements of string contents
            for(int i=0;i<ar.length();i++)
            {
                JSONObject obj = ar.getJSONObject(i);
                ProductItemType1 item = new ProductItemType1();
                item.setProductId(obj.getInt("productid"));
                item.setProductTitle(obj.getString("productname"));
               //item.setMarketPrice(String.valueOf(obj.getInt("mrp")));
                item.setMarketPrice("100");
               //item.setOurPrice(String.valueOf(obj.getInt("ourprice")));
                item.setOurPrice("80");
               // item.setProductImageName(obj.getString("image"));
               //item.setProductBrand("Aashirvad "+ i );
               //item.setItemImageName(obj.getString("photo"));//gets the image names
               //img.setTitle(obj.getString("name"));//gets the categories

                tmp.add(i,item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return tmp;
    }

    /**
     * This Function is used to parse the contents recieved by the StringRequest
     * @param contents The contents that need to be parsed
     * @return tmp The result of the parsed contents as ArrayList<String>
     */
    public static ArrayList<String> parseCityList(String contents) {
        JSONArray ar = null;
        ArrayList<String> cityNames = new ArrayList<>();

        try {
            ar = new JSONArray(contents);

            // go through each elements of string contents
            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);
                String item = obj.getString("city");

                cityNames.add(i,item);
            }
        } catch (JSONException e) {
        e.printStackTrace();
        return null;
    }

    return cityNames;
}

    /**
     * This Function is used to parse the contents recieved by the StringRequest
     * @param contents The contents that need to be parsed
     * @return tmp The result of the parsed contents as ArrayList<String>
     */
    public static ArrayList<String> parseAreaList(String contents) {
        JSONArray ar = null;
        ArrayList<String> areaNames = new ArrayList<>();

        try {
            ar = new JSONArray(contents);

            // go through each elements of string contents
            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);
                String item = obj.getString("place");

                areaNames.add(i,item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return areaNames;
    }


}