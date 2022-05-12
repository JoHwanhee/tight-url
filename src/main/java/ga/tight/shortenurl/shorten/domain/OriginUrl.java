package ga.tight.shortenurl.shorten.domain;

import ga.tight.shortenurl.gloabl.NullChecker;
import ga.tight.shortenurl.gloabl.UrlChecker;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class OriginUrl {
    private String url;

    public static OriginUrl of(String input) {
        OriginUrl originUrl = new OriginUrl();
        originUrl.url = UrlChecker.convertValidatedUrl(NullChecker.orElseThrow(input));
        return originUrl;
    }
}
