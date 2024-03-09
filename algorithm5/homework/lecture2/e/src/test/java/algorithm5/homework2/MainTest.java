package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        String[] berries = new String[3];
        berries[0] = "1 5";
        berries[1] = "8 2";
        berries[2] = "4 4";
        assertTrue("10\n2 3 1".equals(Main.snailGo(berries)));
    }

    @Test
    void contextTest2() {
        String[] berries = new String[2];
        berries[0] = "7 6";
        berries[1] = "7 4";
        assertTrue("10\n2 1".equals(Main.snailGo(berries)));
    }

    @Test
    void contextTest4() {
        String[] berries = new String[3];
        berries[0] = "100 99";
        berries[1] = "8 2";
        berries[2] = "4 4";
        assertTrue("106\n2 3 1".equals(Main.snailGo(berries)));
    }

}
