package algorithm5.homework3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        assertEquals(3, Main.getDeletedNumber(5, "1 2 3 4 5"));
    }

    @Test
    void contextTest2() {
        assertEquals(4, Main.getDeletedNumber(10, "1 1 2 3 5 5 2 2 1 5"));
    }

    @Test
    void contextTest6() {
        assertEquals(9, Main.getDeletedNumber(10, "22238 38788 73611 22861 18865 52721 85325 98901 72035 74803"));
    }

}
