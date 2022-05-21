package ga.tight.shortenurl.shorten.domain.url;

import ga.tight.shortenurl.gloabl.NullChecker;
import ga.tight.shortenurl.gloabl.Utils;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Tag {
    private String tag;

    public static Tag generateRandom() {
        Tag tag = new Tag();

        // todo : make the policy pattern.
        tag.tag = RandomString.builder()
                .size(6)
                .build()
                .toString();
        return tag;
    }

    public static Tag of(String input) {
        String value = NullChecker.orElseThrow(input);
        if(value.length() > 6 || value.length() <= 0) {
            throw new IllegalStateException();
        }

        Tag tag = new Tag();
        tag.tag = value;
        return tag;
    }

    public long hash() {
        return Utils.hash(tag);
    }
}
