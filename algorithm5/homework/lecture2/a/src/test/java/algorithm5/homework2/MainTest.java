package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest() {
        String[] dots = new String[4];
        dots[0] = "1 3";
        dots[1] = "3 1";
        dots[2] = "3 5";
        dots[3] = "6 3";
        assertTrue("1 1 6 5".equals(Main.getFigure(dots)));
    }
}
