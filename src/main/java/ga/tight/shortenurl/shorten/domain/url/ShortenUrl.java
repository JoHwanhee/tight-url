package ga.tight.shortenurl.shorten.domain.url;

import ga.tight.shortenurl.gloabl.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShortenUrl {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "tag", column = @Column(name = "tag"))
    private Tag tag;

    private Long tagHashCode;

    @Embedded
    @AttributeOverride(name = "url", column = @Column(name = "url"))
    private OriginUrl url;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder()
    public ShortenUrl(Tag tag, OriginUrl url, User user) {
        this.tag = tag;
        this.tagHashCode = tag.hash();
        this.url = url;
        this.user = user;
    }

    public static ShortenUrl of(String value) {
        return of(value, null);
    }

    public static ShortenUrl of(String value, User user) {
        return ShortenUrl.builder()
                .tag(Tag.generateRandom())
                .url(OriginUrl.of(value))
                .user(user)
                .build();
    }

    public String getTagValue() {
        return tag.getTag();
    }

    public boolean hasUser() {
        return user != null;
    }

    public User getUser() {
        return user;
    }

    public String getRedirectUrl() {
        return url.getUrl();
    }

    public Tag getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "ShortenUrl{" +
                "id=" + id +
                ", tag=" + tag +
                ", url=" + url +
                ", createdAt=" + createdAt +
                ", user=" + user +
                '}';
    }
}
