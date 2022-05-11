package ga.tight.shortenurl.shorten.dto.response;

import lombok.Getter;

@Getter
public class ResponseRegisterShorten {
    private String tag;

    public ResponseRegisterShorten(String tagValue) {
        this.tag = tagValue;
    }
}
