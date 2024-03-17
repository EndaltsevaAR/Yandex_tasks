package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        String[] lines = new String[2];
        lines[0] = "#";
        lines[1] = ".";
        assertTrue("NO".equals(Main.decodePaint(2, 1, lines)));
    }

    @Test
    void contextTest2() {
        String[] lines = new String[2];
        lines[0] = "..";
        lines[1] = "##";
        assertTrue("YES\n..\nab".equals(Main.decodePaint(2, 2, lines)));
    }

    @Test
    void contextTest3() {
        String[] lines = new String[1];
        lines[0] = "###";
        assertTrue("YES\nabb".equals(Main.decodePaint(1, 3, lines)));
    }

    @Test
    void contextTest4() {
        String[] lines = new String[1];
        lines[0] = "####.";
        assertTrue("YES\nabbb.".equals(Main.decodePaint(1, 5, lines)));
    }

    @Test
    void contextTest5() {
        String[] lines = new String[3];
        lines[0] = ".";
        lines[1] = "#";
        lines[2] = "#";
        assertTrue("YES\n.\na\nb".equals(Main.decodePaint(3, 1, lines)));
    }

    @Test
    void contextTest23() {
        String[] lines = new String[2];
        lines[0] = ".#..";
        lines[1] = "###.";
        assertTrue("YES\n.a..\nbbb.".equals(Main.decodePaint(2, 4, lines)));
    }

    @Test
    void contextTest24() {
        String[] lines = new String[4];
        lines[0] = ".#";
        lines[1] = "##";
        lines[2] = ".#";
        lines[3] = "..";
        assertTrue("YES\n.a\nba\n.a\n..".equals(Main.decodePaint(4, 2, lines)));
    }

    @Test
    void contextTest29() {
        String[] lines = new String[4];
        lines[0] = "..#";
        lines[1] = ".##";
        lines[2] = ".#.";
        lines[3] = "...";
        assertTrue("YES\n..a\n.ba\n.b.\n...".equals(Main.decodePaint(4, 3, lines)));
    }

}
