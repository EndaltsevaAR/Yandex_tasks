package yandex.lecture._02LinearSearch;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LinearSearchTest {
    private static LinearSearch linearSearch;

    @BeforeAll
    public static void init() {
        linearSearch = new LinearSearch();
    }

    @Test
    void normalStart() {
        assertEquals(0, linearSearch.search("1 2 3", 1));
    }

    @Test
    void normalMiddle() {
        assertEquals(1, linearSearch.search("1 2 3", 2));
    }

    @Test
    void normalEnd() {
        assertEquals(2, linearSearch.search("1 2 3", 3));
    }

    @Test
    void notInLine() {
        assertEquals(-1, linearSearch.search("1 2 3", 4));
    }

    @Test
    void emptyLine() {
        assertThrows(NumberFormatException.class,
                () -> {
                    linearSearch.search("", 4);
                });
    }

    @Test
    void twoNumbers() {
        assertEquals(-1, linearSearch.search("1 1 3", 4));
    }

}
