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

    @Test
    void contextTest22() {
        assertEquals(952, Main.getMaxProfit(1, "952 159 945 463 133 101 767 314", "47 448 28"));
    }

    @Test
    void contextTest11() {
        assertEquals(468, Main.getMaxProfit(4, "744 43 468 382", "20 48 12"));
    }

    @Test
    void contextTest14() {
        assertEquals(1000, Main.getMaxProfit(34,
                "56 1000 528 720 895 209 805 65 370 923 541 431 528 778 670 761 794 49 488 171 438 325 57 717 293 847 535 306 398 757 888 56 916 999",
                "391 901 26"));
    }
}
