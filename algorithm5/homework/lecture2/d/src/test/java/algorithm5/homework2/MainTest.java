package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        String[] cells = new String[3];
        cells[0] = "1 1";
        cells[1] = "1 2";
        cells[2] = "2 1";
        assertEquals(8, Main.getPerimetr(cells));
    }

    @Test
    void contextTest2() {
        String[] cells = new String[1];
        cells[0] = "8 8";
        assertEquals(4, Main.getPerimetr(cells));
    }

}
