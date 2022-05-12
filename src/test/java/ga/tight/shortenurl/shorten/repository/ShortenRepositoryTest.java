package ga.tight.shortenurl.shorten.repository;

import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.url.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShortenRepositoryTest {

    @Autowired ShortenRepository repository;

    @DisplayName("생성 테스트")
    @Test
    void create() {
        // given
        ShortenUrl url = ShortenUrl.of("https://www.naver.com");

        // when
        repository.save(url);

        // then
        String tag = url.getTagValue();
        ShortenUrl found = repository.findShortenUrlByTag(Tag.of(tag)).get();
        assertThat(found).isNotNull();
    }
}