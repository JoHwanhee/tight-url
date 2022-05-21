package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.gloabl.user.User;
import ga.tight.shortenurl.shorten.domain.statistics.Statistics;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.dto.request.RegisterShortenDto;
import ga.tight.shortenurl.shorten.repository.ShortenRepository;
import ga.tight.shortenurl.shorten.repository.StatisticsRepository;
import ga.tight.shortenurl.shorten.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ShortenRegisterService {
    private final ShortenRepository repository;
    private final UserRepository userRepository;

    public ShortenUrl register(RegisterShortenDto registerShortenDto) {
        User user = null;
        if(registerShortenDto.hasUserId()) {
            user = getUser(registerShortenDto);
        }

        return repository.save(ShortenUrl.of(registerShortenDto.getUrl(), user));
    }

    private User getUser(RegisterShortenDto registerShortenDto) {
        return userRepository
                .findById(registerShortenDto.getUserId())
                .orElse(null);
    }
}
