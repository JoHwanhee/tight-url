package ga.tight.shortenurl.gloabl;

import java.util.Optional;

public class NullChecker {
    public static String orElseThrow(String input) {
        String value = Optional.ofNullable(input)
                .orElseThrow(() -> new IllegalArgumentException("input value is null."));

        String tempValue = value.replace(" ", "");
        if(tempValue.isBlank() || tempValue.isEmpty()) {
            throw new IllegalArgumentException("input value is blank or empty.");
        }

        return value;
    }

    public static Long orElseThrow(Long input) {
        return Optional.ofNullable(input)
                .orElseThrow(() -> new IllegalArgumentException("input value is null."));
    }
}
