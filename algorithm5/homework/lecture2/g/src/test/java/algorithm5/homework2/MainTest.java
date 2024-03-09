package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        String[] lengths = new String[3];
        String[] stringArrays = new String[3];
        lengths[0] = "5";
        stringArrays[0] = "1 3 3 3 2";
        lengths[1] = "16";
        stringArrays[1] = "1 9 8 7 6 7 8 9 9 9 9 9 9 9 9 9";
        lengths[2] = "7";
        stringArrays[2] = "7 2 3 4 3 2 7";

        assertTrue("3\n1 3 1\n3\n1 6 9\n3\n2 3 2".equals(Main.separateArrays(lengths, stringArrays)));
    }

    @Test
    void contextTest2() {
        String[] lengths = new String[10];
        String[] stringArrays = new String[10];
        lengths[0] = "9";
        stringArrays[0] = "1 1 9 2 9 9 9 5 8";
        lengths[1] = "10";
        stringArrays[1] = "10 9 9 10 3 4 1 8 2 7";
        lengths[2] = "10";
        stringArrays[2] = "10 10 10 1 5 8 4 8 9 8";
        lengths[3] = "10";
        stringArrays[3] = "1 3 10 4 6 4 3 7 6 10";
        lengths[4] = "10";
        stringArrays[4] = "4 3 8 3 7 1 10 5 1 4";
        lengths[5] = "10";
        stringArrays[5] = "5 2 5 5 10 10 10 1 6 3";
        lengths[6] = "10";
        stringArrays[6] = "9 2 1 4 1 9 8 3 1 1";
        lengths[7] = "7";
        stringArrays[7] = "4 1 6 4 7 1 7";
        lengths[8] = "2";
        stringArrays[8] = "2 2";
        lengths[9] = "2";
        stringArrays[9] = "2 1";

        assertTrue(
                "4\n1 1 2 5\n5\n4 2 1 2 1\n4\n3 1 4 2\n4\n1 3 3 3\n6\n3 2 1 2 1 1\n4\n2 5 1 2\n7\n2 1 1 1 3 1 1\n5\n1 1 3 1 1\n1\n2\n2\n1 1"
                        .equals(Main.separateArrays(lengths, stringArrays)));
    }
}
