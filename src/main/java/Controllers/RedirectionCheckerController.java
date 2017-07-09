package Controllers;

import Models.Factory.UrlConnector.UrlConnector;
import Models.RedirectionChecker.RedirectionCheckerCore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

/**
 * Created by Kien Seng on 7/8/2017.
 */
@RestController
public class RedirectionCheckerController {
    @RequestMapping(value = "/redirectioncheck", method = RequestMethod.GET)
    public LinkedHashMap<String, Object> RedirectionCheck(){
        RedirectionCheckerCore redirection = new RedirectionCheckerCore(new UrlConnector("http://jobstreet.com"), "Redirection of jobstreet.com");
        redirection.setRedirectedLocation("https://www.jobstreet.com/");
        redirection.startRedirectionCheck();
        return redirection.getRedirectionResult();
    }
}
