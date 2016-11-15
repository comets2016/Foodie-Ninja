package com.example.bxt140930.foodieninja;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by jxj050100 on 11/14/2016.
 */

public class LoginSinglton {
    //create an object of SingleObject
    private static LoginSinglton instance = new LoginSinglton();

    //make the constructor private so that this class cannot be
    //instantiated
    private LoginSinglton(){

    }

    //Get the only object available
    public static LoginSinglton getInstance(){
        return instance;
    }

    public int validateUser(Context c, String user, String password){
        CommunicationManager cm = new CommunicationManager();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("j_username", user);
            jsonObject.put("j_password", password);
        } catch(Exception e)
        {
            //TODO: need different error message
            Toast.makeText(c, c.getString(R.string.ErrorRetrvingData), Toast.LENGTH_LONG).show();
        }

        int returnCode = cm.sendJsonPOSTResuest(c, "api/authentication", jsonObject);
        return returnCode;
    }



}
