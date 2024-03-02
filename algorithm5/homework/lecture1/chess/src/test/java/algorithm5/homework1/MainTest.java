package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void contextTestOneRock() {
        char[][] field = new char[8][8];
        for (int i = 0; i < Main.FIELD_LEN; i++) {
            for (int j = 0; j < Main.FIELD_LEN; j++) {
                field[i][j] = '*';
            }
        }
        field[2][1] = 'R';
        assertEquals(49, Main.getUnusedCells(field));
    }

    @Test
    void contextTestOneBishop() {
        char[][] field = new char[8][8];
        for (int i = 0; i < Main.FIELD_LEN; i++) {
            for (int j = 0; j < Main.FIELD_LEN; j++) {
                field[i][j] = '*';
            }
        }
        field[2][6] = 'B';
        assertEquals(54, Main.getUnusedCells(field));
    }

    @Test
    void contextTestRockAndBishop() {
        char[][] field = new char[8][8];
        for (int i = 0; i < Main.FIELD_LEN; i++) {
            for (int j = 0; j < Main.FIELD_LEN; j++) {
                field[i][j] = '*';
            }
        }
        field[1][1] = 'R';
        field[3][5] = 'B';
        assertEquals(40, Main.getUnusedCells(field));
    }

}
