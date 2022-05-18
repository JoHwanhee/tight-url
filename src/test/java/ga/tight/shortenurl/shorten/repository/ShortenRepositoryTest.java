package ga.tight.shortenurl.shorten.repository;

import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.url.Tag;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
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
        ShortenUrl found = repository.findShortenUrlByTagHashCode(Tag.of(tag).hash()).get();
        assertThat(found).isNotNull();
    }
}