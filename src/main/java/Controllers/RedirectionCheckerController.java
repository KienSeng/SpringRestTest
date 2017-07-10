package Controllers;

import Models.Factory.UrlConnector.UrlConnector;
import Models.RedirectionChecker.RedirectionCheckerCore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

/**
 * Created by Kien Seng on 7/8/2017.
 */
@RestController
public class RedirectionCheckerController {
    @RequestMapping(value = "/redirectioncheck", method = RequestMethod.GET)
    public LinkedHashMap<String, Object> RedirectionCheck(@RequestParam(value="url") String url,
                                                          @RequestParam(value="destUrl", defaultValue = "") String destUrl){
        RedirectionCheckerCore redirection = new RedirectionCheckerCore(new UrlConnector(url), "Redirection of " + url);
        redirection.setRedirectedLocation(destUrl);
        redirection.startRedirectionCheck();
        return redirection.getRedirectionResult();
    }
}
