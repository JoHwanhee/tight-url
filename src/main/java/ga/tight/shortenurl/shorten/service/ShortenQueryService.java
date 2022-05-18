package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.gloabl.NullChecker;
import ga.tight.shortenurl.shorten.domain.statistics.Statistics;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.url.Tag;
import ga.tight.shortenurl.shorten.dto.response.ResponseQueryShorten;
import ga.tight.shortenurl.shorten.repository.ShortenRepository;
import ga.tight.shortenurl.shorten.repository.StatisticsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ShortenQueryService {
    private final ShortenRepository shortenRepository;
    private final StatisticsRepository statisticsRepository;

    public ShortenQueryService(ShortenRepository shortenRepository, StatisticsRepository statisticsRepository) {
        this.shortenRepository = shortenRepository;
        this.statisticsRepository = statisticsRepository;
    }

    @Cacheable(value = "tag", key = "#tag")
    public ResponseQueryShorten findByTag(String tag) {
        Tag tagToFind = Tag.of(NullChecker.orElseThrow(tag));
        ShortenUrl url = shortenRepository.findShortenUrlByTagHashCode(tagToFind.hash())
                .orElseThrow(() -> new IllegalArgumentException("has no value"));

        Statistics statistics = statisticsRepository.findByShortenUrl(url)
                .orElse(new Statistics(url, 0L));
        statistics.increase();

        statisticsRepository.save(statistics);

        return new ResponseQueryShorten(
                url.getRedirectUrl()
        );
    }

    public List<ResponseQueryShorten> findByUserId(Long id) {
        List<ShortenUrl> urls = shortenRepository.findByUserId(id);

        return urls.stream()
                   .map(url -> new ResponseQueryShorten(
                           url.getRedirectUrl()
                   ))
                .collect(Collectors.toList());
    }
}
