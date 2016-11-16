package communication;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by bxt140930 on 11/15/2016.
 */

public class HTTPGetCommunication extends CommunicationManager
{

    @Override
    protected String SendRequest(String URL, Object Parameters) throws Exception
    {

        URL url = new URL(URL);
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
