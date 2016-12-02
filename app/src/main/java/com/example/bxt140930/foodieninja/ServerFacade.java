package com.example.bxt140930.Foodieninja;

import android.content.Context;
import android.widget.Toast;

import com.example.bxt140930.Foodieninja.Entities.Credential;
import com.example.bxt140930.Foodieninja.Entities.Menu;
import com.example.bxt140930.Foodieninja.Entities.Order;
import com.example.bxt140930.Foodieninja.Entities.OrderItem;
import com.example.bxt140930.Foodieninja.Entities.Restaurants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.example.bxt140930.Foodieninja.Communication.HTTPCredentialCommunication;
import com.example.bxt140930.Foodieninja.Communication.HTTPGetCommunication;
import com.example.bxt140930.Foodieninja.Communication.HTTPGetFriendlyCommunication;
import com.example.bxt140930.Foodieninja.Communication.HTTPPostJsonArrayCommunication;
import com.example.bxt140930.Foodieninja.Communication.HTTPPostJsonCommunication;

import static android.R.attr.data;

/**
 * Created by bxt140930 on 11/9/2016.
 */

public class ServerFacade {
    Context C;
    public ServerFacade(Context C)
    {
        this.C = C;
    }
    public ArrayList<Restaurants> GetRestaurants()
    {
        ArrayList<Restaurants> RestaurantsList = new ArrayList<>();
        try
        {
            HTTPGetCommunication CM = new HTTPGetCommunication();
            String Result = CM.SendResuest(C, "api/food-joints", new HashMap<String, String>());

            JSONArray JSonArray = new JSONArray(Result);
            for(int i = 0; i < JSonArray.length(); i++)
            {
                JSONObject JObj = JSonArray.getJSONObject(i);
                Restaurants Res = new Restaurants();
                if(JObj.isNull("id"))
                    continue;
                Res.setId(JObj.getInt("id"));
                if(JObj.isNull("name"))
                    continue;
                Res.setName(JObj.getString("name"));
                if(!JObj.isNull("image"))
                    Res.setImageUrl(JObj.getString("image"));
                if(!JObj.isNull("workingHours"))
                    Res.setWorkingHours(JObj.getString("workingHours"));
                if(!JObj.isNull("EstimatedWait"))
                    Res.setEstimatedWait(JObj.getDouble("EstimatedWait"));
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
            HTTPGetFriendlyCommunication CM = new HTTPGetFriendlyCommunication();
            String Result = CM.SendResuest(C, "api/menu-items/food-joint", Params);
            JSONArray JSonArray = new JSONArray(Result);
            for(int i = 0; i < JSonArray.length(); i++)
            {
                JSONObject JObj = JSonArray.getJSONObject(i);
                Menu Item = new Menu();
                if(JObj.isNull("id"))
                    continue;
                Item.setId(JObj.getInt("id"));
                if(JObj.isNull("name"))
                    continue;
                Item.setName(JObj.getString("name"));
                if(!JObj.isNull("image"))
                    Item.setImageUrl(JObj.getString("image"));
                if(JObj.isNull("price"))
                    continue;
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
    public double GetTotal(Order O)
    {
        try
        {
            JSONArray JA = new JSONArray();
            for(OrderItem OI : O.getOrderItems())
            {
                JSONObject JO = new JSONObject();
                JO.put("id", OI.getItem().getId());
                JO.put("quantity", OI.getQuantuty());
                JA.put(JO);
            }
            HTTPPostJsonArrayCommunication CM = new HTTPPostJsonArrayCommunication();
            String Result = CM.SendResuest(C, "api/menu-items/price", JA);

            if(true)
                return Double.valueOf(Result);

            JSONObject JObj = new JSONObject(Result);
            if(JObj.isNull("total"))
                return -1;
            return JObj.getDouble("total");
        }
        catch(Exception Ex)
        {
            Ex.printStackTrace();
            Toast.makeText(C, C.getString(R.string.ErrorJsonParsing), Toast.LENGTH_LONG).show();
            return -1;
        }
    }
    public String CheckCredentials(Credential Cr)
    {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("j_username", Cr.getUsername());
        params.put("j_password", Cr.getPassword());
        HTTPCredentialCommunication CM = new HTTPCredentialCommunication();
        return CM.SendResuest(C, "api/authentication", params);
    }

    public String SignUpRequest(Credential Cr) {

        JSONObject JO = new JSONObject();
        try {
            JO.put("login", Cr.getUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JO.put("firstName", Cr.getFirstName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JO.put("lastName", Cr.getLastName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JO.put("email", Cr.getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JO.put("langKey", "en");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JO.put("password", Cr.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HTTPPostJsonCommunication CM = new HTTPPostJsonCommunication();
        String result = CM.SendResuest(C, "api/register", JO);

        return result;
    }

    public Order GetTicketWithOrder(Order O)
    {
        try
        {
            JSONArray JA = new JSONArray();
            for(OrderItem OI : O.getOrderItems())
            {
                JSONObject JO = new JSONObject();
                JO.put("id", OI.getItem().getId());
                JO.put("quantity", OI.getQuantuty());
                JA.put(JO);
            }
            HTTPPostJsonArrayCommunication CM = new HTTPPostJsonArrayCommunication();
            String Result = CM.SendResuest(C, "api/menu-items/price", JA);

            if(true)
                return null;

            JSONObject JObj = new JSONObject(Result);
            if(JObj.isNull("total"))
                return null;
            return null;
        }
        catch(Exception Ex)
        {
            Ex.printStackTrace();
            Toast.makeText(C, C.getString(R.string.ErrorJsonParsing), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
