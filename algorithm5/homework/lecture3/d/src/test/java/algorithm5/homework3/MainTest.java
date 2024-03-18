package algorithm5.homework3;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        assertTrue("NO".equals(Main.getAnswer("4 2", "1 2 3 1")));
    }

    @Test
    void contextTest2() {
        assertTrue("YES".equals(Main.getAnswer("4 1", "1 0 1 1")));
    }

    @Test
    void contextTest3() {
        assertTrue("NO".equals(Main.getAnswer("6 2", "1 2 3 1 2 3")));
    }

}
