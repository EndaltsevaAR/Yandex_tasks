package yandex.lecture._02LinearSearch;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MaxElementTest {
    private static MaxElement maxElement;

    @BeforeAll
    public static void init() {
        maxElement = new MaxElement();
    }

    @Test
    void sortedLine() {
        assertEquals(5, maxElement.maxElement("1 2 3 4 5"));
    }

    @Test
    void revSortedLine() {
        assertEquals(5, maxElement.maxElement("5 4 3 2 1"));
    }

    @Test
    void eqLine() {
        assertEquals(1, maxElement.maxElement("1 1 1"));
    }
}
