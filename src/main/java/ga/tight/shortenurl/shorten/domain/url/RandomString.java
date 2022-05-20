package ga.tight.shortenurl.shorten.domain.url;

public class RandomString {
    private String value;
    private static String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    public RandomString(int n) {
        this.value = generate(n);
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