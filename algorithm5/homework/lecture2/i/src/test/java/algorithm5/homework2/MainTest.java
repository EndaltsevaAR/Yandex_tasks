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
        assertEquals(3, Main.getMinMoves(ships));
    }

    @Test
    void contextTest9() {
        int n = 5;
        String[] ships = new String[n];
        ships[0] = "1 1";
        ships[1] = "2 2";
        ships[2] = "3 3";
        ships[3] = "4 4";
        ships[4] = "5 5";
        assertEquals(6, Main.getMinMoves(ships));
    }

    @Test
    void contextTest13() {
        int n = 10;
        String[] ships = new String[n];
        ships[0] = "9 4";
        ships[1] = "8 9";
        ships[2] = "5 4";
        ships[3] = "10 8";
        ships[4] = "7 9";
        ships[5] = "10 5";
        ships[6] = "9 2";
        ships[7] = "8 10";
        ships[8] = "3 9";
        ships[9] = "6 2";
        assertEquals(48, Main.getMinMoves(ships));
    }
}
