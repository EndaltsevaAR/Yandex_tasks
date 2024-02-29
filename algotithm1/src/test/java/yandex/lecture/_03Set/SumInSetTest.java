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
        assertEquals("2, 5", sumInSet.solution("1 3 5 8 2", 7));
    }

    @Test
    void SumNotInLine() {
        assertEquals("0, 0", sumInSet.solution("1 3 5 8 2", 123));
    }

    @Test
    void SumEmptyLine() {
        assertThrows(NumberFormatException.class,
                () -> {
                    sumInSet.solution("", 123);
                });
    }

    @Test
    void SumInNullLine() {
        assertThrows(NullPointerException.class,
                () -> {
                    sumInSet.solution(null, 123);
                });
    }
}
