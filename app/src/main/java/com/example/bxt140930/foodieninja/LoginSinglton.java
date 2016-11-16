package com.example.bxt140930.foodieninja;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by jxj050100 on 11/14/2016.
 */

public class LoginSinglton
{
    private static LoginSinglton instance = new LoginSinglton();
    private LoginSinglton(){
    }
    public static LoginSinglton getInstance(){
        return instance;
    }

    public void validateUser(Context c)
    {
        SQLiteJDBCforCredential sqlite = new SQLiteJDBCforCredential(c);
        if(!sqlite.getAllContacts())
        {
            Intent appInfo = new Intent(c, LoginActivity.class);
            c.startActivity(appInfo);
            ((Activity) c).finish();
        }

    }

}
