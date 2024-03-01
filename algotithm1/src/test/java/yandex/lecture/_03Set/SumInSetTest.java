package yandex.lecture._03Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SumInSetTest {
    private static SumInSet sumInSet;

    @BeforeAll
    public static void init() {
        sumInSet = new SumInSet();
    }

    @Test
    void SumInLine() {
        assertEquals("2, 5", sumInSet.findSumNumbers("1 3 5 8 2", 7));
    }

    @Test
    void TwoSumInLine() {
        assertEquals("2, 5", sumInSet.findSumNumbers("1 3 5 8 2 6", 7)); // 2+5 and 1+6
    }

    @Test
    void SumNotInLine() {
        assertEquals("0, 0", sumInSet.findSumNumbers("1 3 5 8 2", 123));
    }

    @Test
    void SumEmptyLine() {
        assertThrows(NumberFormatException.class,
                () -> {
                    sumInSet.findSumNumbers("", 123);
                });
    }

    @Test
    void SumInNullLine() {
        assertThrows(NullPointerException.class,
                () -> {
                    sumInSet.findSumNumbers(null, 123);
                });
    }
}
