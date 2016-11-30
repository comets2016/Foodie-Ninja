package com.example.bxt140930.Foodieninja.Communication;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by bxt140930 on 11/15/2016.
 */

public class HTTPPostJsonCommunication extends CommunicationManager
{

    @Override
    protected String SendRequest(String URL, Object Parameters) throws Exception
    {
        java.net.URL url = new URL(URL);
        URLConnection conn = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection) conn;
        httpConn.setAllowUserInteraction(false);
        httpConn.setInstanceFollowRedirects(true);
        httpConn.setRequestProperty("Content-Type", "application/json");
        httpConn.setRequestMethod("POST");
        httpConn.connect();
        DataOutputStream wr = new DataOutputStream(httpConn.getOutputStream());
        wr.writeBytes(((JSONObject)Parameters).toString());
        wr.flush();
        wr.close();
        InputStream is = httpConn.getInputStream();
        return ConvertInputStreamToString(is);
    }
}
