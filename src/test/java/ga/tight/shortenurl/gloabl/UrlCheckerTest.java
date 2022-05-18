package ga.tight.shortenurl.gloabl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UrlCheckerTest {

    @Test
    @DisplayName("https 스키마가 없는 경우")
    void noHttps() {
        String converted = UrlChecker.convertValidatedUrl("www.naver.com");
        assertThat(converted).isEqualTo("https://www.naver.com");
    }

    @Test
    @DisplayName("http 스키마가 있는 경우")
    void has_scheme() {
        String converted = UrlChecker.convertValidatedUrl("http://www.naver.com");
        assertThat(converted).isEqualTo("http://www.naver.com");
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"", " ", "        "})
    @DisplayName("null and empty 파라미터 테스트")
    void has_scheme(String input) {
        assertThrows(IllegalArgumentException.class, () -> {
            UrlChecker.convertValidatedUrl(input);
        });
    }
}