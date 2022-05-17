package ga.tight.shortenurl.shorten.repository;

import ga.tight.shortenurl.gloabl.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMail(String mail);
}
