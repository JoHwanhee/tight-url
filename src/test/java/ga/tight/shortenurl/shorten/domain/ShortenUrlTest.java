package ga.tight.shortenurl.shorten.domain;

import ga.tight.shortenurl.shorten.domain.ShortenUrl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShortenUrlTest {

    @DisplayName("URL 등록 테스트")
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"https://www.naver.com"})
    void create(String input) {
        if(input == null)
            assertThrows(IllegalArgumentException.class, () -> {
                ShortenUrl.of(input);
            });
        else if(input.equals(""))
            assertThrows(IllegalArgumentException.class, () -> {
                ShortenUrl.of(input);
            });
        else {
            ShortenUrl shortenUrl = ShortenUrl.of(input);
            assertThat(shortenUrl.getTagValue().length()).isEqualTo(6);
        }
    }
}