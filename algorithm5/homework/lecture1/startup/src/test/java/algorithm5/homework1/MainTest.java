package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import algorithm5.honework1.Main;

public class MainTest {

    @Test
    void contextTestOneDay() {
        assertEquals(216, Main.getFinalProfit(21, 108, 1));
    }

    @Test
    void contextTestNoProfit() {
        assertEquals(-1, Main.getFinalProfit(5, 12, 4));
    }

    @Test
    void maxEnters() {
        assertEquals(-1, Main.getFinalProfit(1000000000, 1000000000, 100000));
    }
}
