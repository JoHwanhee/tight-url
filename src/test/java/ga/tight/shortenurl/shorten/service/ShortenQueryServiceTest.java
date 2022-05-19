package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.gloabl.user.User;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.dto.request.RegisterShortenDto;
import ga.tight.shortenurl.shorten.dto.request.RegisterUserDto;
import ga.tight.shortenurl.shorten.dto.response.ResponseQueryShorten;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ShortenQueryServiceTest {
    @Autowired private ShortenQueryService shortenQueryService;
    @Autowired private ShortenRegisterService shortenRegisterService;
    @Autowired private UserRegisterService userRegisterService;

    @Test
    void selectByName() {
        String mail = "asdf@asdf.com";
        RegisterUserDto userDto = new RegisterUserDto(mail);
        User user = userRegisterService.register(userDto);


        shortenRegisterService.register(RegisterShortenDto.builder()
                        .userId(user.getId())
                        .url("hello.com")
                .build());

        List<ResponseQueryShorten> shortenUrlList = shortenQueryService.findByUserId(user.getId());

        assertThat(shortenUrlList).isNotNull();
        assertThat(shortenUrlList.size()).isNotZero();
    }
}