package ga.tight.shortenurl.shorten.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
@AllArgsConstructor
public class ResponseRegisterShortenDto {
    private String tag;
    private String fullUrl;
    private Long userId;

    public ResponseRegisterShortenDto(String tagValue) {
        this.tag = tagValue;
    }

    public String mergeUrl(String baseUrl) {
        Assert.notNull(baseUrl, "baseUrl must be not null");

        String url = baseUrl.replace("/home/shorten-urls", "")
                .replace("http://", "https://");
        return url + "/" + tag;
    }
}
