package Models.RedirectionChecker;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Created by Kien Seng on 7/7/2017.
 */
public class ResponseContents {
    String test_name;
    String input_url;
    String destination_url;
    String result;
    LinkedHashMap<String, String> redirection_paths;

    public ResponseContents(String testName){
        test_name = testName;
    }

    public void setInputUrl(String url){
        input_url = url;
    }

    public void setDestinationUrl(String destinationUrl){
        destination_url = destinationUrl;
    }

    public void setResult(String result){
        this.result = result;
    }

    public void setRedirectionPaths(LinkedHashMap<String, String> redirectionPaths){
        redirection_paths = redirectionPaths;
    }
}
