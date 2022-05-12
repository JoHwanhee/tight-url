package ga.tight.shortenurl.gloabl;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlChecker {
    public static String convertValidatedUrl(String input) {
        String validStr = NullChecker.orElseThrow(input);

        if(isValidURL(validStr)) {
            return validStr;
        }

        return "https://" + validStr;
    }

    public static boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }

        return true;
    }
}
