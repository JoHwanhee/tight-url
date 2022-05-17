package ga.tight.shortenurl.shorten.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }
}
