package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.gloabl.NullChecker;
import ga.tight.shortenurl.shorten.domain.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.Tag;
import ga.tight.shortenurl.shorten.dto.response.ResponseQueryShorten;
import ga.tight.shortenurl.shorten.repository.ShortenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ShortenQueryService {
    private final ShortenRepository shortenRepository;

    public ShortenQueryService(ShortenRepository shortenRepository) {
        this.shortenRepository = shortenRepository;
    }

    @Cacheable(value = "tag", key = "#tag")
    public ResponseQueryShorten findByTag(String tag) {
        log.info(tag);

        String tagToFind = NullChecker.orElseThrow(tag);
        return new ResponseQueryShorten(
                shortenRepository.findShortenUrlByTag(Tag.of(tagToFind))
                        .orElseThrow(() -> new IllegalArgumentException("has no value"))
                        .getRedirectUrl()
        );
    }
}
