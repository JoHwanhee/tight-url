package ga.tight.shortenurl.shorten.domain;

import ga.tight.shortenurl.gloabl.NullChecker;

import javax.persistence.Embeddable;

@Embeddable
public class Url {

    private String url;

    public static Url of(String input) {
        Url url = new Url();
        url.url = NullChecker.orElseThrow(input);
        return url;
    }

    public String getUrl() {
        return url;
    }
}
