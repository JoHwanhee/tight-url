package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.shorten.domain.statistics.Statistics;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.dto.request.RegisterShortenDto;
import ga.tight.shortenurl.shorten.repository.StatisticsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StatisticsServiceTest {

    @Autowired ShortenRegisterService shortenRegisterService;
    @Autowired StatisticsRepository repository;

    @Autowired StatisticsService statisticsService;

    @DisplayName("히트 수치 테스트")
    @Test
    void increase() {
        // given
        RegisterShortenDto dto = new RegisterShortenDto(0L, "https://google.com");
        ShortenUrl url = shortenRegisterService.register(dto);

        // when
        statisticsService.increase(url);

        // then
        Statistics statistics = repository.findByShortenUrl(url.getId()).get();
        assertAll(
                () -> assertThat(statistics).isNotNull(),
                () -> assertThat(statistics.getCount()).isGreaterThan(0)
        );
    }
}