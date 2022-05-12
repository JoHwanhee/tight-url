package ga.tight.shortenurl.home.dto;

import ga.tight.shortenurl.shorten.dto.request.RequestRegisterShortenDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestRegisterUrlForm {
    private String url;
    private Long userId;
}
