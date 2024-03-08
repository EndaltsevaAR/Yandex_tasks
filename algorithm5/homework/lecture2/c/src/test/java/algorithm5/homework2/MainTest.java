package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        assertEquals(1, Main.getMinRope(4, "1 5 2 1"));
    }

    @Test
    void contextTest2() {
        assertEquals(24, Main.getMinRope(4, "5 12 4 3"));
    }

}
