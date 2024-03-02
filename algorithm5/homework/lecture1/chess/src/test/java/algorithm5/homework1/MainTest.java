package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void contextTestOneRock() {
        char[][] field = new char[Main.FIELD_LEN][Main.FIELD_LEN];
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
        char[][] field = new char[Main.FIELD_LEN][Main.FIELD_LEN];
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
        char[][] field = new char[Main.FIELD_LEN][Main.FIELD_LEN];
        for (int i = 0; i < Main.FIELD_LEN; i++) {
            for (int j = 0; j < Main.FIELD_LEN; j++) {
                field[i][j] = '*';
            }
        }
        field[1][1] = 'R';
        field[3][5] = 'B';
        assertEquals(40, Main.getUnusedCells(field));
    }

    @Test
    void contextTestRockAndSomeBishops() {
        char[][] field = new char[Main.FIELD_LEN][Main.FIELD_LEN];
        for (int i = 0; i < Main.FIELD_LEN; i++) {
            for (int j = 0; j < Main.FIELD_LEN; j++) {
                field[i][j] = '*';
            }
        }
        field[2][3] = 'R';
        field[1][3] = 'B';
        field[2][2] = 'B';
        field[2][4] = 'B';
        field[3][3] = 'B';
        assertEquals(41, Main.getUnusedCells(field));
    }

}
