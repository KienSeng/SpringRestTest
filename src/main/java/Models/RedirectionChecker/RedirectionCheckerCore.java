package Models.RedirectionChecker;

import Models.Factory.UrlConnector.IHttpConnectionFactory;
import Models.Factory.UrlConnector.UrlConnector;

import java.util.LinkedHashMap;

/**
 * Created by Kien Seng on 7/7/2017.
 */
public class RedirectionCheckerCore {
    private UrlConnector urlConnector;
    private LinkedHashMap<String, String> redirectionList = new LinkedHashMap<>();
    private String startUrl, destinationUrl;
    private LinkedHashMap<String, Object> redirectionResult = new LinkedHashMap<>();
    private IHttpConnectionFactory connectionFactory;

    public RedirectionCheckerCore(UrlConnector urlConnector, String testName){
        this.urlConnector = urlConnector;
        startUrl = urlConnector.getUrl();
        redirectionResult.put("test_name", testName);
        redirectionResult.put("start_url", startUrl);
    }

    public void startRedirectionCheck(){
        String redirectlocation, currentUrl = startUrl;
        int responseCode = 0, numberOfRedirection = 0;

        do{
            connectionFactory = urlConnector.setUrl(currentUrl).connectUrl("GET");
            redirectionList.put(String.valueOf(numberOfRedirection++), currentUrl);
            responseCode = (int) connectionFactory.getResponseContent().get("code");

            if(responseCode == 301 || responseCode == 302){
                redirectlocation = connectionFactory.getResponseHeader().get("Location").get(0);
                //Verify if next URL is equal startUrl for infinite redirection
                if(isInfiniteRedirection(currentUrl, redirectlocation)){
                    setResultFail("Infinite redirection loop detected.");
                    break;
                }

                //Assign startUrl with the next url to check for redirection
                currentUrl = redirectlocation;
            } else if (responseCode >= 400 && responseCode < 600){
                setResultFail("URL return with invalid response code " + responseCode);
            } else if (responseCode == 200){
                if(!destinationUrl.isEmpty()){
                    System.out.println(destinationUrl);
                    verifyDestinationUrlEqual(currentUrl, destinationUrl);
                }
            }
        }while(responseCode != 200);

        redirectionResult.put("redirection_paths", redirectionList);
    }

    public void setRedirectedLocation(String destinationUrl){
        this.destinationUrl = destinationUrl;
    }

    public LinkedHashMap<String, Object> getRedirectionResult(){
        return redirectionResult;
    }

    private void verifyDestinationUrlEqual(String currentUrl, String destinationUrl){
        if(currentUrl.equals(destinationUrl)){
            setResultPass();
        } else {
            setResultFail("Redirected URL mismatch with provided URL.");
        }
    }

    private boolean isInfiniteRedirection(String currentUrl, String nextUrl){
        return currentUrl.equals(nextUrl);
    }

    private void setResultFail(String reason){
        redirectionResult.put("result", false);
        redirectionResult.put("fail_reson", reason);
    }

    private void setResultPass(){
        redirectionResult.put("result", true);
    }
}
