package ga.tight.shortenurl.shorten.repository;

import ga.tight.shortenurl.shorten.domain.statistics.Statistics;
import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    @Query("select s from Statistics as s where s.shortenUrl.id = :id")
    Optional<Statistics> findByShortenUrl(@Param("id") Long shortenUrlId);
}
