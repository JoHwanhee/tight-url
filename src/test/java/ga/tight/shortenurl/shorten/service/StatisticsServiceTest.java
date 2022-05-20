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

    @DisplayName("히트 수치 올라가야함")
    @Test
    void increase() {
        RegisterShortenDto dto = new RegisterShortenDto(0L, "https://google.com");
        ShortenUrl url = shortenRegisterService.register(dto);

        statisticsService.increase(url);

        Statistics statistics = repository.findByShortenUrl(url).get();

        assertAll(
                () -> assertThat(statistics).isNotNull(),
                () -> assertThat(statistics.getCount()).isGreaterThan(0)
        );
    }
}