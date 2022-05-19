package ga.tight.shortenurl.gloabl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NullCheckerTest {

    @Test
    void nullCheck() {
        assertThrows(IllegalArgumentException.class, () -> {
            String data = NullChecker.orElseThrow(null);
        });
    }

    @Test
    void return_not_null() {
        String data = NullChecker.orElseThrow("hello");
        assertEquals(data, "hello");
    }
}