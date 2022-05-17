package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.gloabl.user.User;
import ga.tight.shortenurl.shorten.dto.request.RegisterUserDto;
import ga.tight.shortenurl.shorten.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRegisterServiceTest {
    @Autowired private UserRegisterService userRegisterService;
    @Autowired private UserRepository userRepository;

    @DisplayName("유저 기본 생성")
    @Test
    void createUser() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setEmail("kikiki0611@naer.com2");

        userRegisterService.register(registerUserDto);

        User user = userRepository.findByMail("kikiki0611@naer.com2").get();
        assertThat(user.getMail()).isEqualTo("kikiki0611@naer.com2");
    }

    @DisplayName("유저 중복 생성")
    @Test
    void createUser2() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setEmail("kikiki0611@naer.com");

        Exception exception = assertThrows(Exception.class, () -> {
            userRegisterService.register(registerUserDto);
        });
    }
}