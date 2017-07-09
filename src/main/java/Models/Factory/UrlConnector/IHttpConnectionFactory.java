package Models.Factory.UrlConnector;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Kien Seng on 7/7/2017.
 */
public interface IHttpConnectionFactory {
    String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
    int timeout = 10000;

    IHttpConnectionFactory connectToUrl(String url, String requestMethod);
    LinkedHashMap<String, List<String>> getResponseHeader();
    LinkedHashMap<String, Object> getResponseContent();
    String getUrl();
}