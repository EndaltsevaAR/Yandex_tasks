package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        int n = 3;
        String[] ships = new String[n];
        ships[0] = "1 2";
        ships[1] = "3 3";
        ships[2] = "1 1";
        assertEquals(2, Main.getMinMoves(ships));
    }
}
