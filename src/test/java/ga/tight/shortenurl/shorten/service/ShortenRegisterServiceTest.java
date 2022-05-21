package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.shorten.domain.statistics.Statistics;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.url.Tag;
import ga.tight.shortenurl.gloabl.user.User;
import ga.tight.shortenurl.shorten.dto.request.RegisterShortenDto;
import ga.tight.shortenurl.shorten.dto.response.ResponseRegisterShortenDto;
import ga.tight.shortenurl.shorten.repository.ShortenRepository;
import ga.tight.shortenurl.shorten.repository.StatisticsRepository;
import ga.tight.shortenurl.shorten.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
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
        RegisterShortenDto registerShortenDto = new RegisterShortenDto(null, "https://www.naver.com");

        ShortenUrl shortenUrl = registerService.register(registerShortenDto);

        assertThat(shortenUrl.getTagValue().length()).isEqualTo(6);
    }

    @DisplayName("없는 유저 넘버로 요청할 때")
    @Test
    void create2() {
        // given
        RegisterShortenDto registerShortenDto = new RegisterShortenDto(0L, "https://www.naver.com");

        // when
        ShortenUrl res = registerService.register(registerShortenDto);

        // then
        Tag tag = Tag.of(res.getTagValue());
        ShortenUrl saved = repository.findShortenUrlByTagHashCode(tag.hash()).get();

        assertThat(res.getTagValue().length()).isEqualTo(6);
        assertThat(res.getTagValue()).isEqualTo(saved.getTagValue());
        assertThat(saved.hasUser()).isEqualTo(false);
    }


    @DisplayName("있는 유저 넘버로 요청할 때")
    @Test
    void create3() {
        // given
        User user = User.builder().mail("kikiki0611@gmail.com").build();
        User savedUser = userRepository.save(user);
        RegisterShortenDto registerShortenDto = new RegisterShortenDto(savedUser.getId(), "https://www.naver.com");

        // when
        ShortenUrl res = registerService.register(registerShortenDto);

        // then
        Tag tag = Tag.of(res.getTagValue());
        ShortenUrl saved = repository.findShortenUrlByTagHashCode(tag.hash()).get();

        assertThat(res.getTagValue().length()).isEqualTo(6);
        assertThat(res.getTagValue()).isEqualTo(saved.getTagValue());
        assertThat(saved.hasUser()).isEqualTo(true);
        assertThat(saved.getUser().getId()).isEqualTo(savedUser.getId());
    }
}