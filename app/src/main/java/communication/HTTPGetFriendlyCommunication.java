package communication;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by bxt140930 on 11/15/2016.
 */

public class HTTPGetFriendlyCommunication extends CommunicationManager
{
    @Override
    protected String SendRequest(String URL, Object Parameters) throws Exception
    {
        ArrayList<String> Params = (ArrayList<String>)Parameters;
        String UrlParams = URL;
        for(String Value : Params)
            UrlParams += "/" + Value;
        URL url = new URL(UrlParams);
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
