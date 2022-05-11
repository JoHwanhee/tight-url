package ga.tight.shortenurl.shorten.dto.response;

import lombok.Getter;

@Getter
public class ResponseQueryShorten {
    private String fullUrl;

    public ResponseQueryShorten(String fullUrl) {
        this.fullUrl = fullUrl;
    }
}
