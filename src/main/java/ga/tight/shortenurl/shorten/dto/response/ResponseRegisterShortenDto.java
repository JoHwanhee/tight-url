package ga.tight.shortenurl.shorten.dto.response;

import ga.tight.shortenurl.gloabl.user.User;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
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

    public static ResponseRegisterShortenDto of(ShortenUrl url) {
        return of(url, 0L);
    }

    public static ResponseRegisterShortenDto of(ShortenUrl url, User user) {
        return of(url, user.getId());
    }

    public static ResponseRegisterShortenDto of(ShortenUrl url, Long userId) {
        return new ResponseRegisterShortenDto(
                url.getTagValue(),
                url.getRedirectUrl(),
                userId
        );
    }
}
