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
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@Slf4j
public class ShortenQueryService {
    private final ShortenRepository shortenRepository;

    @Cacheable(value = "tag", key = "#tag")
    public ShortenUrl findByTag(Tag tag) {
        return shortenRepository.findShortenUrlByTagHashCode(tag.hash())
                .orElseThrow(() -> new IllegalArgumentException("has no value"));
    }

    public List<ShortenUrl> findByUserId(Long id) {
        Long userId = NullChecker.orElseThrow(id);

        return shortenRepository.findByUserId(userId)
                .orElseThrow();
    }
}
