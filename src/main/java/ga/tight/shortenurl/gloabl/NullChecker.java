package ga.tight.shortenurl.gloabl;

import java.util.Optional;

public class NullChecker {
    public static String orElseThrow(String input) {
        String value = Optional.ofNullable(input)
                .orElseThrow(() -> new IllegalArgumentException("input value is null."));
        value = value.replace(" ", "");
        if(value.isBlank() || value.isEmpty()) {
            throw new IllegalArgumentException("input value is blank or empty.");
        }

        return value;
    }
}
