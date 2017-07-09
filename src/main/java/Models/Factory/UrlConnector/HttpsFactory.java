package Models.Factory.UrlConnector;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Kien Seng on 7/7/2017.
 */
public class HttpsFactory implements IHttpConnectionFactory{
    LinkedHashMap<String, List<String>> responseHeader = new LinkedHashMap();
    LinkedHashMap<String, Object> responseContent = new LinkedHashMap();
    String url;

    public HttpsFactory connectToUrl(String url, String requestMethod){
        this.url = url;
        HttpsURLConnection httpsConnection;

        try{
            httpsConnection = (HttpsURLConnection) new URL(this.url).openConnection();
            httpsConnection.setReadTimeout(timeout);
            httpsConnection.setRequestMethod(requestMethod);
            httpsConnection.addRequestProperty("User-Agent", userAgent);
            httpsConnection.setInstanceFollowRedirects(false);
            httpsConnection.connect();
            responseHeader.putAll(httpsConnection.getHeaderFields());
            responseContent.put("code", httpsConnection.getResponseCode());
            responseContent.put("body", httpsConnection.getResponseMessage());
            httpsConnection.disconnect();
        }catch(Exception e){

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
