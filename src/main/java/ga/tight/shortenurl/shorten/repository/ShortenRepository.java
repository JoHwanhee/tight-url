package ga.tight.shortenurl.shorten.repository;

import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.url.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShortenRepository extends JpaRepository<ShortenUrl, Long> {
    Optional<ShortenUrl> findShortenUrlByTagHashCode(Long tagHashCode);

    @Query("SELECT s FROM ShortenUrl AS s " +
           "WHERE s.user.id =:id")
    List<ShortenUrl> findByUserId(@Param("id") Long id);
}

