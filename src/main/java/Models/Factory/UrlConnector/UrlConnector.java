package Models.Factory.UrlConnector;

/**
 * Created by Kien Seng on 7/7/2017.
 */
public class UrlConnector {
    String url;

    public UrlConnector(String url){
        this.url = url;
    }
    public IHttpConnectionFactory connectUrl(String requestMethod){
        this.url = url;

        if(url.substring(0, 5).equals("https")){
            return new HttpsFactory().connectToUrl(url, requestMethod.toUpperCase());
        } else {
            return new HttpFactory().connectToUrl(url, requestMethod.toUpperCase());
        }
    }

    public String getUrl(){
        return url;
    }

    public UrlConnector setUrl(String url){
        this.url = url;
        return this;
    }
}
