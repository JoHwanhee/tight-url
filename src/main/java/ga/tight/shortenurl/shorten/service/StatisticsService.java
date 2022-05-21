package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.gloabl.NullChecker;
import ga.tight.shortenurl.shorten.domain.statistics.Statistics;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.url.Tag;
import ga.tight.shortenurl.shorten.dto.response.ResponseQueryShorten;
import ga.tight.shortenurl.shorten.repository.ShortenRepository;
import ga.tight.shortenurl.shorten.repository.StatisticsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public void increase(ShortenUrl url) {
        Statistics statistics = statisticsRepository.findByShortenUrl(url.getId())
                .orElseGet(() -> new Statistics(url, 0L));
        statistics.increase();

        statisticsRepository.save(statistics);
    }
}
