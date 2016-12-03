package com.example.bxt140930.Foodieninja.Other;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.bxt140930.Foodieninja.LoginControllerActivity;

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
        DBAdapter DBA = new DBAdapter(c);
        if (!DBA.CheckCredentials())
        {
            Intent appInfo = new Intent(c, LoginControllerActivity.class);
            c.startActivity(appInfo);
            ((Activity) c).finish();
        }

    }

    public void SignOut(Context c) {
        DBAdapter AJC = new DBAdapter(c);
        AJC.LogOut();
        Intent appInfo = new Intent(c, LoginControllerActivity.class);
        c.startActivity(appInfo);
        ((Activity) c).finish();
    }

    public String GetUserId(Context c) {
        DBAdapter AJC = new DBAdapter(c);
        return AJC.getUserID();
    }

}
