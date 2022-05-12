package ga.tight.shortenurl.shorten.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ShortenUrl {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "tag", column = @Column(name = "tag"))
    private Tag tag;

    @Embedded
    @AttributeOverride(name = "url", column = @Column(name = "url"))
    private OriginUrl url;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public static ShortenUrl of(String value) {
        ShortenUrl shortenUrl = new ShortenUrl();
        shortenUrl.tag = Tag.generateRandom();
        shortenUrl.url = OriginUrl.of(value);
        return shortenUrl;
    }

    public String getTagValue() {
        return tag.getTag();
    }

    public String getRedirectUrl() {
        return url.getUrl();
    }
}
