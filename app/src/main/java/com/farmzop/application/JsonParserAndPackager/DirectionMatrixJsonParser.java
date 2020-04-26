package com.farmzop.application.JsonParserAndPackager;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gaurav on 28-01-2016.
 */
public class DirectionMatrixJsonParser {

    public static float parseObject(JSONObject contents,int storeId)
    {
        //TODO :change this function accordingly
        //FindStoreDetailsItem tmp=null ;

        //parse the downloaded contents and find the distance between the location

        float tmpDistance=0;

        try {

            // go through each elements of string contents
                JSONArray arr = contents.getJSONArray("routes");

                JSONObject routes = arr.getJSONObject(0);

                JSONArray legs = routes.getJSONArray("legs");
                 JSONObject steps = legs.getJSONObject(0);

                    JSONObject distance = steps.getJSONObject("distance");

                    tmpDistance=(float)distance.getInt("value")/1000;

            Log.d("Dist",distance.toString()+":"+storeId);
                    /*This also works, it is  the shorter version
                     tmpDistance+= obj.getJSONObject(0).getJSONArray("legs").getJSONObject(j).getJSONObject("distance").getInt("value");
                     */

           //tmp = new FindStoreDetailsItem(storeId,tmpDistance,"Store "+storeId);

        } catch (JSONException e) {
            e.printStackTrace();
            }

        return tmpDistance;
    }
}
