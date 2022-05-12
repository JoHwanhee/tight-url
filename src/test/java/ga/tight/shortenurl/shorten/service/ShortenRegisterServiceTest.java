package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.shorten.domain.statistics.Statistics;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.url.Tag;
import ga.tight.shortenurl.shorten.domain.user.User;
import ga.tight.shortenurl.shorten.dto.request.RequestRegisterShortenDto;
import ga.tight.shortenurl.shorten.dto.response.ResponseRegisterShortenDto;
import ga.tight.shortenurl.shorten.repository.ShortenRepository;
import ga.tight.shortenurl.shorten.repository.StatisticsRepository;
import ga.tight.shortenurl.shorten.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ShortenRegisterServiceTest {

    @Autowired
    ShortenRegisterService registerService;

    @Autowired
    ShortenRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatisticsRepository statisticsRepository;


    @DisplayName("유저 가 null 일때")
    @Test
    void create() {
        RequestRegisterShortenDto requestRegisterShortenDto = new RequestRegisterShortenDto(null, "https://www.naver.com");

        ResponseRegisterShortenDto responseDto = registerService.register(requestRegisterShortenDto);

        assertThat(responseDto.getTag().length()).isEqualTo(6);
    }

    @DisplayName("없는 유저 넘버로 요청할 때")
    @Test
    void create2() {
        // given
        RequestRegisterShortenDto requestRegisterShortenDto = new RequestRegisterShortenDto(0L, "https://www.naver.com");

        // when
        ResponseRegisterShortenDto responseDto = registerService.register(requestRegisterShortenDto);

        // then
        Tag tag = Tag.of(responseDto.getTag());
        ShortenUrl saved = repository.findShortenUrlByTag(tag).get();
        Statistics statistics = statisticsRepository.findByShortenUrl(saved).get();

        assertThat(responseDto.getTag().length()).isEqualTo(6);
        assertThat(responseDto.getTag()).isEqualTo(saved.getTagValue());
        assertThat(saved.hasUser()).isEqualTo(false);
        assertThat(statistics.getCount()).isEqualTo(0);
    }


    @DisplayName("있는 유저 넘버로 요청할 때")
    @Test
    void create3() {
        // given
        User user = User.builder().mail("kikiki0611@gmail.com").build();
        User savedUser = userRepository.save(user);
        RequestRegisterShortenDto requestRegisterShortenDto = new RequestRegisterShortenDto(savedUser.getId(), "https://www.naver.com");

        // when
        ResponseRegisterShortenDto responseDto = registerService.register(requestRegisterShortenDto);

        // then
        Tag tag = Tag.of(responseDto.getTag());
        ShortenUrl saved = repository.findShortenUrlByTag(tag).get();
        Statistics statistics = statisticsRepository.findByShortenUrl(saved).get();

        assertThat(responseDto.getTag().length()).isEqualTo(6);
        assertThat(responseDto.getTag()).isEqualTo(saved.getTagValue());
        assertThat(saved.hasUser()).isEqualTo(true);
        assertThat(saved.getUser().getId()).isEqualTo(savedUser.getId());
        assertThat(statistics.getCount()).isEqualTo(0);
    }
}