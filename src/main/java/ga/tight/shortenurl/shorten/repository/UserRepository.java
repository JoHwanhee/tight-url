package ga.tight.shortenurl.shorten.repository;

import ga.tight.shortenurl.shorten.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { }
