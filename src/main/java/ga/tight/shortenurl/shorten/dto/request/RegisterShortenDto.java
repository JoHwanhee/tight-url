package ga.tight.shortenurl.shorten.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterShortenDto {
    private Long userId;

    @URL
    private String url;

    public boolean hasUserId() {
        return userId != null;
    }
}
