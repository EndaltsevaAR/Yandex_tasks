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
        assertEquals(5, Main.getMaxProfit(4, "1 2 3 4 5", "15 15 2"));
    }

    @Test
    void contextTest3() {
        assertEquals(5, Main.getMaxProfit(5, "5 4 3 2 1", "2 5 2"));
    }

}
