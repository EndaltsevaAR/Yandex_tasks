package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        assertEquals(5, Main.getMaxProfit(5, "1 2 3 4 5", "3 5 2"));
    }

    @Test
    void contextTest2() {
        assertEquals(4, Main.getMaxProfit(5, "1 2 3 4 5", "15 15 2"));
    }

    @Test
    void contextTest3() {
        assertEquals(5, Main.getMaxProfit(5, "5 4 3 2 1", "2 5 2"));
    }

    @Test
    void contextTestSpeeds() {
        assertEquals(1, Main.getMaxProfit(1, "1 2 3 4 5", "1 1 2"));
    }

    // 8
    // 952 159 945 463 133 101 767 314
    // 47 448 28
    @Test
    void contextTest22() {
        assertEquals(952, Main.getMaxProfit(1, "952 159 945 463 133 101 767 314", "47 448 28"));
    }
}
