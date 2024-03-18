package algorithm5.homework3;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        String[][] names = new String[1][2];
        names[0][0] = "GoGetIt";
        names[0][1] = "Life";
        assertTrue("2\nGoGetIt Life".equals(Main.printPlayList(names)));
    }

    @Test
    void contextTest2() {
        String[][] names = new String[2][2];
        names[0][0] = "Love";
        names[0][1] = "Life";
        names[1][0] = "Life";
        names[1][1] = "GoodDay";
        assertTrue("1\nLife".equals(Main.printPlayList(names)));
    }

    @Test
    void contextTest5() {
        String[][] names = new String[2][];
        names[0] = new String[3];
        names[0][0] = "a";
        names[0][1] = "b";
        names[0][2] = "c";
        names[1] = new String[2];
        names[1][0] = "c";
        names[1][1] = "a";
        assertTrue("2\na c".equals(Main.printPlayList(names)));
    }
}
