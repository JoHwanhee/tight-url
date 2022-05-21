package ga.tight.shortenurl.shorten.domain.url;

import lombok.Builder;

public class RandomString {
    private String value;
    private static String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    @Builder
    private RandomString(int size) {
        this.value = generate(size);
    }

    @Override
    public String toString() {
        return value;
    }

    private String generate(int n) {
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length()
                        * Math.random());
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }
}