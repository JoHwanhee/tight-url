package ga.tight.shortenurl.shorten.service;

import ga.tight.shortenurl.gloabl.user.User;
import ga.tight.shortenurl.shorten.dto.request.RegisterUserDto;
import ga.tight.shortenurl.shorten.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserRegisterService {
    private final UserRepository userRepository;

    public User register(RegisterUserDto dto) {
        assert dto != null : "user dto must be not null.";

        User user = new User(dto.getEmail());
        userRepository.save(user);

        return user;
    }
}
