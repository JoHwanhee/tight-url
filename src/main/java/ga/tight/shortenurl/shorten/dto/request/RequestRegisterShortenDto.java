package ga.tight.shortenurl.shorten.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterShortenDto {
    private Long userId;
    private String url;

    public boolean hasUserId() {
        return userId != null;
    }
}
