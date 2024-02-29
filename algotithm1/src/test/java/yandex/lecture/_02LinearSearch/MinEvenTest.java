package yandex.lecture._02LinearSearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MinEvenTest {
    private static MinEven min;

    @BeforeAll
    public static void init() {
        min = new MinEven();
    }

    @Test
    void leftEven() {
        assertEquals(2, min.minEven("2 3 4 6"));
    }

    @Test
    void rightEven() {
        assertEquals(6, min.minEven("1 3 5 6"));
    }

    @Test
    void notEven() {
        assertEquals(-1, min.minEven("1 3 5 7"));
    }

    @Test
    void emptyLine() {
        assertThrows(NumberFormatException.class,
                () -> {
                    min.minEven("");
                });
    }

    @Test
    void nullLine() {
        assertThrows(NullPointerException.class,
                () -> {
                    min.minEven(null);
                });
    }

}
