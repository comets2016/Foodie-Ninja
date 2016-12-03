package com.example.bxt140930.Foodieninja.Communication;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by bxt140930 on 11/15/2016.
 */

public class HTTPGetCommunication extends CommunicationManager
{

    @Override
    protected String SendRequest(String URL, Object Parameters) throws Exception
    {
        String UrlWithParams = URL;
        Map<String, String> params = (Map<String, String>) Parameters;
        if (params.size() > 0) {
            UrlWithParams += "?";
            for (String Key : params.keySet())
                UrlWithParams += Key + "=" + params.get(Key) + "&";
        }
        URL url = new URL(UrlWithParams);
        URLConnection conn = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection) conn;
        httpConn.setAllowUserInteraction(false);
        httpConn.setInstanceFollowRedirects(true);
        httpConn.setRequestMethod("GET");
        httpConn.connect();
        InputStream is = httpConn.getInputStream();
        return ConvertInputStreamToString(is);
    }
}
