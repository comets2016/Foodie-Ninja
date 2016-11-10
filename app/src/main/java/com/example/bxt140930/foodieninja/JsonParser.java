package com.example.bxt140930.foodieninja;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bxt140930 on 11/9/2016.
 */

public class JsonParser {
    Context C;
    public JsonParser(Context C)
    {
        this.C = C;
    }
    public ArrayList<Restaurants> GetRestaurants(String Input)
    {
        ArrayList<Restaurants> RestaurantsList = new ArrayList<>();
        try {
            JSONArray JSonArray = new JSONArray(Input);
            for(int i = 0; i < JSonArray.length(); i++)
            {
                JSONObject JObj = JSonArray.getJSONObject(i);
                Restaurants Res = new Restaurants();
                Res.setName(JObj.getString("name"));
                Res.setEstimatWaitPerPerson(JObj.getDouble("estimatWaitPerPerson"));
                RestaurantsList.add(Res);
            }
            return RestaurantsList;
        }
        catch(Exception Ex)
        {
            Toast.makeText(C, C.getString(R.string.ErrorJsonParsing), Toast.LENGTH_LONG).show();
            return RestaurantsList;
        }
    }
}
