package com.example.bxt140930.Foodieninja.Communication;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.bxt140930.Foodieninja.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by bxt140930 on 11/9/2016.
 */

public abstract class CommunicationManager {
    private String ServerUrl = "http://ec2-35-162-107-27.us-west-2.compute.amazonaws.com:8080/";
    private ProgressDialog pDialog;
    private String Result;
    private Exception E = null;

    protected abstract String SendRequest(final String URL, Object Parameters) throws Exception;

    public String SendResuest(final Context C, final String Type, final Object Parameters)
    {
        try
        {
            pDialog = ProgressDialog.show(C, C.getString(R.string.Downloading), C.getString(R.string.Wait), true, false);
            Thread welcomeThread = new Thread() {
                @Override
                public void run() {
                    try {
                        super.run();
                        try
                        {
                            Result = SendRequest(ServerUrl + Type, Parameters);
                        } catch (Exception e) {
                            E = e;
                        }
                    } catch (Exception e) {
                        E = e;
                    } finally {
                        pDialog.dismiss();
                    }
                }
            };
            welcomeThread.start();
            welcomeThread.join();
            if(E != null)
                Toast.makeText(C, C.getString(R.string.ErrorRetrvingData), Toast.LENGTH_LONG).show();
            return Result;
        }
        catch (Exception Ex)
        {
            Toast.makeText(C, C.getString(R.string.ErrorRetrvingData), Toast.LENGTH_LONG).show();
            return null;
        }
    }
    protected static String ConvertInputStreamToString(InputStream ists)
            throws IOException {
        if (ists != null) {
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                BufferedReader r1 = new BufferedReader(new InputStreamReader(
                        ists, "UTF-8"));
                while ((line = r1.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                ists.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }
}
