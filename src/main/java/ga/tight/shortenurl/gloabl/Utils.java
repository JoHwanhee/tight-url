package ga.tight.shortenurl.gloabl;

public class Utils {
    public static long hash(String string) {
        long h = 1125899906842597L;
        int len = string.length();

        for (int i = 0; i < len; i++) {
            h = 31*h + string.charAt(i);
        }
        return h;
    }
}
