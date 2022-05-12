package ga.tight.shortenurl.shorten.domain.statistics;

import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import ga.tight.shortenurl.shorten.domain.url.Tag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Statistics {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "shorten_url_id")
    private ShortenUrl shortenUrl;

    private Long count;

    public Long getCount() {
        return count;
    }

    public Statistics(ShortenUrl shortenUrl, Long count) {
        this.shortenUrl = shortenUrl;
        this.count = count;
    }

    public void increase() {
        this.count += 1;
    }
}
