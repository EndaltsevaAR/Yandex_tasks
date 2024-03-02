package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest() {
        int[] array = { 1, 4, 12, 9, 0 };
        assertEquals(8, Main.getTouchCount(array));
    }

    @Test
    void emptyTest() {
        int[] array = { 0 };
        assertEquals(0, Main.getTouchCount(array));
    }

    @Test
    void eqLines() {
        int[] array = { 1, 1, 1 };
        assertEquals(3, Main.getTouchCount(array));
    }

    @Test
    void diffLines() {
        int[] array = { 1, 2, 3, 4 };
        assertEquals(6, Main.getTouchCount(array));
    }

    @Test
    void startZiroLines() {
        int[] array = { 0, 1, 1 };
        assertEquals(2, Main.getTouchCount(array));
    }

    @Test
    void finishZiroLines() {
        int[] array = { 1, 1, 0 };
        assertEquals(2, Main.getTouchCount(array));
    }

    @Test
    void allZiroLines() {
        int[] array = { 0, 0, 0 };
        assertEquals(0, Main.getTouchCount(array));
    }

    @Test
    void oneLines() {
        int[] array = { 1601 };
        assertEquals(401, Main.getTouchCount(array));
    }

}
