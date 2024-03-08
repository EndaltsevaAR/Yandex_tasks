package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        assertTrue("2".equals(Main.getMaxProfit("5 2", "1 2 3 4 5")));
    }

    @Test
    void contextTest2() {
        assertTrue("0".equals(Main.getMaxProfit("5 2", "5 4 3 2 1")));
    }

}
