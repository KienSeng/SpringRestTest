package Models.Factory.UrlConnector;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Kien Seng on 7/7/2017.
 */
public class HttpFactory implements IHttpConnectionFactory {
    LinkedHashMap<String, List<String>> responseHeader = new LinkedHashMap();
    LinkedHashMap<String, Object> responseContent = new LinkedHashMap();
    String url;

    public HttpFactory connectToUrl(String url, String requestMethod){
        this.url = url;
        HttpURLConnection httpConnection;

        try{
            httpConnection = (HttpURLConnection) new URL(this.url).openConnection();
            httpConnection.setReadTimeout(timeout);
            httpConnection.setRequestMethod(requestMethod);
            httpConnection.addRequestProperty("User-Agent", userAgent);
            httpConnection.setInstanceFollowRedirects(false);
            httpConnection.connect();
            responseHeader.putAll(httpConnection.getHeaderFields());
            responseContent.put("code", httpConnection.getResponseCode());
            responseContent.put("body", httpConnection.getResponseMessage());
            httpConnection.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }

        return this;
    }

    public String getUrl(){
        return url;
    }

    public LinkedHashMap<String, List<String>> getResponseHeader() {
        return responseHeader;
    }

    public LinkedHashMap<String, Object> getResponseContent() {
        return responseContent;
    }
}
