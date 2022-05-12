package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.shorten.domain.statistics.Statistics;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.user.User;
import ga.tight.shortenurl.shorten.dto.request.RequestRegisterShortenDto;
import ga.tight.shortenurl.shorten.dto.response.ResponseRegisterShortenDto;
import ga.tight.shortenurl.shorten.repository.ShortenRepository;
import ga.tight.shortenurl.shorten.repository.StatisticsRepository;
import ga.tight.shortenurl.shorten.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ShortenRegisterService {
    private final ShortenRepository repository;
    private final UserRepository userRepository;
    private final StatisticsRepository statisticsRepository;

    public ShortenRegisterService(ShortenRepository repository, UserRepository userRepository, StatisticsRepository statisticsRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.statisticsRepository = statisticsRepository;
    }

    public ResponseRegisterShortenDto register(RequestRegisterShortenDto registerShortenDto) {
        User user = null;
        if(registerShortenDto.hasUserId()) {
            user = getUser(registerShortenDto);
        }

        ShortenUrl url = repository.save(ShortenUrl.of(registerShortenDto.getUrl(), user));
        statisticsRepository.save(new Statistics(url, 0L));

        Long userId = null;
        if(url.hasUser())
            userId = url.getUser().getId();

        return new ResponseRegisterShortenDto(
                url.getTagValue(),
                url.getRedirectUrl(),
                userId
        );
    }

    private User getUser(RequestRegisterShortenDto registerShortenDto) {
        return userRepository
                .findById(registerShortenDto.getUserId())
                .orElse(null);
    }
}
