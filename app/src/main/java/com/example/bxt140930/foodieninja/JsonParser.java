package com.example.bxt140930.foodieninja;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by bxt140930 on 11/9/2016.
 */

public class JsonParser {
    Context C;
    public JsonParser(Context C)
    {
        this.C = C;
    }
    public ArrayList<Restaurants> GetRestaurants()
    {
        ArrayList<Restaurants> RestaurantsList = new ArrayList<>();
        try
        {
            CommunicationManager CM = new CommunicationManager();
            String Result = CM.SendTheResuest(C, "api/food-joints", new HashMap<String, String>());

            JSONArray JSonArray = new JSONArray(Result);
            for(int i = 0; i < JSonArray.length(); i++)
            {
                JSONObject JObj = JSonArray.getJSONObject(i);
                Restaurants Res = new Restaurants();
                Res.setId(JObj.getInt("id"));
                Res.setName(JObj.getString("name"));
                Res.setImageUrl(JObj.getString("image"));
                Res.setWorkingHours(JObj.getString("workingHours"));
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
    public ArrayList<Menu> GetMenu(int FoodJointID)
    {
        ArrayList<Menu> Menu = new ArrayList<>();
        try
        {
            ArrayList<String> Params = new ArrayList<>();
            Params.add(String.valueOf(FoodJointID));
            CommunicationManager CM = new CommunicationManager();
            String Result = CM.SendTheFriendlyResuest(C, "api/menu-items/food-joint", Params);
            JSONArray JSonArray = new JSONArray(Result);
            for(int i = 0; i < JSonArray.length(); i++)
            {
                JSONObject JObj = JSonArray.getJSONObject(i);
                Menu Item = new Menu();
                Item.setId(JObj.getInt("id"));
                Item.setName(JObj.getString("name"));
                Item.setImageUrl(JObj.getString("image"));
                Item.setPrice(JObj.getDouble("price"));
                Menu.add(Item);
            }
            return Menu;
        }
        catch(Exception Ex)
        {
            Toast.makeText(C, C.getString(R.string.ErrorJsonParsing), Toast.LENGTH_LONG).show();
            return Menu;
        }
    }
}
