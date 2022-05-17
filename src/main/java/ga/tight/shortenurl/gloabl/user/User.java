package ga.tight.shortenurl.gloabl.user;

import ga.tight.shortenurl.shorten.domain.url.ShortenUrl;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String mail;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ShortenUrl> urls;

    @Builder
    public User(String mail) {
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }
}
