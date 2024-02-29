package yandex.lecture._02LinearSearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LinearSearchLastElementTest {
    private static LinearSearchLastElement linearSearchLastElement;

    @BeforeAll
    public static void init() {
        linearSearchLastElement = new LinearSearchLastElement();
    }

    @Test
    void normalStart() {
        assertEquals(0, linearSearchLastElement.search("1 2 3", 1));
    }

    @Test
    void normalMiddle() {
        assertEquals(1, linearSearchLastElement.search("1 2 3", 2));
    }

    @Test
    void normalEnd() {
        assertEquals(2, linearSearchLastElement.search("1 2 3", 3));
    }

    @Test
    void notInLine() {
        assertEquals(-1, linearSearchLastElement.search("1 2 3", 4));
    }

    @Test
    void emptyLine() {
        assertThrows(NumberFormatException.class,
                () -> {
                    linearSearchLastElement.search("", 4);
                });
    }

    @Test
    void twoNumbers() {
        assertEquals(1, linearSearchLastElement.search("1 1 3", 4));
    }

}
