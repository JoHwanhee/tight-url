package ga.tight.shortenurl.home.dto;

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
