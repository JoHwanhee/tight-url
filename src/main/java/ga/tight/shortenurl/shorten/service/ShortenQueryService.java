package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.gloabl.NullChecker;
import ga.tight.shortenurl.shorten.domain.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.Tag;
import ga.tight.shortenurl.shorten.dto.response.ResponseQueryShorten;
import ga.tight.shortenurl.shorten.repository.ShortenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShortenQueryService {
    private final ShortenRepository shortenRepository;

    public ShortenQueryService(ShortenRepository shortenRepository) {
        this.shortenRepository = shortenRepository;
    }

    public ResponseQueryShorten findByTag(String tag) {
        String tagToFind = NullChecker.orElseThrow(tag);
        return new ResponseQueryShorten(
                shortenRepository.findShortenUrlByTag(Tag.of(tagToFind))
                        .orElseThrow(() -> new IllegalArgumentException("has no value"))
                        .getRedirectUrl()
        );
    }
}
