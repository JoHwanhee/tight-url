package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.shorten.domain.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.Tag;
import ga.tight.shortenurl.shorten.dto.response.ResponseQueryShorten;
import ga.tight.shortenurl.shorten.dto.response.ResponseRegisterShorten;
import ga.tight.shortenurl.shorten.repository.ShortenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShortenRegisterService {
    private final ShortenRepository repository;

    public ShortenRegisterService(ShortenRepository repository) {
        this.repository = repository;
    }

    public ResponseRegisterShorten register(String input) {
        return new ResponseRegisterShorten(
                repository.save(ShortenUrl.of(input))
                        .getTagValue()
        );
    }
}
