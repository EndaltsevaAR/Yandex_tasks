package algorithm5.homework4;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {

        Party[] parties = new Party[3];
        parties[0] = new Party(0, 7, 1000001);
        parties[1] = new Party(1, 2, 8);
        parties[2] = new Party(2, 1, 2);
        String answer = Main.getElection(3, 10, parties);
        assertTrue("6\n3\n3 2 5".equals(answer));
    }

    @Test
    void contextTest2() {
        Party[] parties = new Party[1];
        parties[0] = new Party(0, 239, 239);
        String answer = Main.getElection(1, 239, parties);
        assertTrue("239\n1\n239".equals(answer));
    }

    @Test
    void contextTest5() {
        Party[] parties = new Party[2];
        parties[0] = new Party(0, 239, 239);
        parties[1] = new Party(1, 240, 1000001);
        String answer = Main.getElection(2, 479, parties);
        assertTrue("240\n1\n240 239".equals(answer));
    }

    @Test
    void contextTest3() {
        Party[] parties = new Party[2];
        parties[0] = new Party(0, 239, 239);
        parties[1] = new Party(1, 238, 1000001);
        String answer = Main.getElection(2, 477, parties);
        assertTrue("239\n1\n239 238".equals(answer));
    }
}
