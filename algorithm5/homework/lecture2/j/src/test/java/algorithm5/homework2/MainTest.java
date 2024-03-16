package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        String[][] paint = new String[2][1];
        paint[0][0] = "#";
        paint[1][0] = ".";
        assertTrue("NO".equals(Main.decodePaint(paint)));
    }

    @Test
    void contextTest2() {
        char[][] paint = new char[2][2];
        paint[0][0] = '.';
        paint[0][1] = '.';
        paint[1][0] = '#';
        paint[1][1] = '#';
        assertTrue("YES\n..\nab".equals(Main.decodePaint(paint)));
    }

    @Test
    void contextTest3() {
        char[][] paint = new char[1][3];
        paint[0][0] = '#';
        paint[0][1] = '#';
        paint[0][2] = '#';
        assertTrue("YES\nabb".equals(Main.decodePaint(paint)));
    }

    @Test
    void contextTest4() {
        char[][] paint = new char[1][5];
        paint[0][0] = '#';
        paint[0][1] = '#';
        paint[0][2] = '#';
        paint[0][3] = '#';
        paint[0][4] = '.';

        assertTrue("YES\nabbb".equals(Main.decodePaint(paint)));
    }
}
