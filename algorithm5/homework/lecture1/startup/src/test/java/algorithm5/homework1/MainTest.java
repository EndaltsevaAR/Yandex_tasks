package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import algorithm5.honework1.Main;

public class MainTest {

    @Test
    void contextTestOneDay() {
        assertEquals(216, Main.getFinalProfit(21, 108, 1).intValue());
    }

    @Test
    void contextTestDayWithZiros() {
        assertEquals(21600, Main.getFinalProfit(21, 108, 3).intValue());
    }

    @Test
    void contextTestNoProfit() {
        assertEquals(-1, Main.getFinalProfit(5, 12, 4).intValue());
    }

    @Test
    void contextTestMaxStart() {
        assertEquals(294209208, Main.getFinalProfit(29420920, 98069736, 1).intValue());
    }

}
