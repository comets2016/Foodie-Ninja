package com.example.bxt140930.foodieninja;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by bxt140930 on 11/9/2016.
 */

public class CommunicationManager {
    String ServerUrl = "http://ec2-35-162-107-27.us-west-2.compute.amazonaws.com:8080/";
    private ProgressDialog pDialog;
    String Result;

    public String sendJsonPOSTResuest(Context C, final String Type, final JSONObject Data)
    {
        try {
            pDialog = ProgressDialog.show(C, C.getString(R.string.Downloading), C.getString(R.string.Wait), true, false);
            Thread welcomeThread = new Thread() {
                @Override
                public void run() {
                    try {
                        super.run();
                        try
                        {
                            String completedUrl = ServerUrl + Type;
                            URL url = new URL(completedUrl);
                            URLConnection conn = url.openConnection();
                            HttpURLConnection httpConn = (HttpURLConnection) conn;
                            httpConn.setAllowUserInteraction(false);
                            httpConn.setInstanceFollowRedirects(true);
                            httpConn.setRequestProperty("Content-Type", "application/json");
                            httpConn.setRequestMethod("POST");
                            httpConn.connect();

                            DataOutputStream wr = new DataOutputStream(httpConn.getOutputStream());
                            wr.writeBytes(Data.toString());
                            wr.flush();
                            wr.close();

                            InputStream is = httpConn.getInputStream();
                            Result = convertinputStreamToString(is);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {

                    } finally {
                        pDialog.dismiss();
                    }
                }
            };
            welcomeThread.start();
            welcomeThread.join();
            return Result;
        }
        catch (Exception Ex)
        {
            Toast.makeText(C, C.getString(R.string.ErrorRetrvingData), Toast.LENGTH_LONG).show();
            return "";
        }
    }
    public String SendTheResuest(Context C, final String Type, HashMap<String, String> Parameters)
    {
        try {
            pDialog = ProgressDialog.show(C, C.getString(R.string.Downloading), C.getString(R.string.Wait), true, false);
            Thread welcomeThread = new Thread() {
                @Override
                public void run() {
                    try {
                        super.run();
                        try
                        {
                            URL url = new URL(ServerUrl + Type);
                            URLConnection conn = url.openConnection();
                            HttpURLConnection httpConn = (HttpURLConnection) conn;
                            httpConn.setAllowUserInteraction(false);
                            httpConn.setInstanceFollowRedirects(true);
                            httpConn.setRequestMethod("GET");
                            httpConn.connect();

                            InputStream is = httpConn.getInputStream();
                            Result = convertinputStreamToString(is);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {

                    } finally {
                        pDialog.dismiss();
                    }
                }
            };
            welcomeThread.start();
            welcomeThread.join();
            return Result;
        }
        catch (Exception Ex)
        {
            Toast.makeText(C, C.getString(R.string.ErrorRetrvingData), Toast.LENGTH_LONG).show();
            return "";
        }
    }
    public String SendTheFriendlyResuest(Context C, final String Type, final ArrayList<String> Parameters)
    {
        try {
            pDialog = ProgressDialog.show(C, C.getString(R.string.Downloading), C.getString(R.string.Wait), true, false);
            Thread welcomeThread = new Thread() {
                @Override
                public void run() {
                    try {
                        super.run();
                        try
                        {
                            String UrlParams = Type;
                            for(String Value : Parameters)
                                UrlParams += "/" + Value;
                            URL url = new URL(ServerUrl + UrlParams);
                            URLConnection conn = url.openConnection();
                            HttpURLConnection httpConn = (HttpURLConnection) conn;
                            httpConn.setAllowUserInteraction(false);
                            httpConn.setInstanceFollowRedirects(true);
                            httpConn.setRequestMethod("GET");
                            httpConn.connect();

                            InputStream is = httpConn.getInputStream();
                            Result = convertinputStreamToString(is);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {

                    } finally {
                        pDialog.dismiss();
                    }
                }
            };
            welcomeThread.start();
            welcomeThread.join();
            return Result;
        }
        catch (Exception Ex)
        {
            Toast.makeText(C, C.getString(R.string.ErrorRetrvingData), Toast.LENGTH_LONG).show();
            return "";
        }
    }
    public static String convertinputStreamToString(InputStream ists)
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
