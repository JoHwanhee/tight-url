package ga.tight.shortenurl.shorten.domain.url;

import ga.tight.shortenurl.gloabl.NullChecker;
import ga.tight.shortenurl.gloabl.Utils;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Tag {
    private String tag;

    public static Tag generateRandom() {
        Tag tag = new Tag();
        tag.tag = new RandomString(6).toString();
        return tag;
    }

    public static Tag of(String input) {
        String value = NullChecker.orElseThrow(input);
        Tag tag = new Tag();
        tag.tag = value;
        return tag;
    }

    public long hash() {
        return Utils.hash(tag);
    }
}
