package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        String[] berries = new String[3];
        berries[0] = "1 5";
        berries[1] = "8 2";
        berries[2] = "4 4";
        assertTrue("10\n2 3 1".equals(Main.snailGo(berries)));
    }

    @Test
    void contextTest2() {
        String[] berries = new String[2];
        berries[0] = "7 6";
        berries[1] = "7 4";
        assertTrue("10\n2 1".equals(Main.snailGo(berries)));
    }

    @Test
    void contextTest7() {
        String[] berries = new String[7];
        berries[0] = "160714711 449656269";
        berries[1] = "822889311 446755913";
        berries[0] = "135599877 389312924";
        berries[1] = "448565595 480845266";
        berries[1] = "561330066 605997004";
        berries[0] = "61020590 573085537";
        berries[1] = "715477619 181424399";
        assertTrue("1471516684\n2 7 5 1 3 4 6".equals(Main.snailGo(berries)));
    }
}
