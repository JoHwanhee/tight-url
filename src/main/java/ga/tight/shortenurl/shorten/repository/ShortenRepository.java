package ga.tight.shortenurl.shorten.repository;

import ga.tight.shortenurl.shorten.domain.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortenRepository extends JpaRepository<ShortenUrl, Long> {
    Optional<ShortenUrl> findShortenUrlByTag(Tag tag);
}
