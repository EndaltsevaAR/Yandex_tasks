package algorithm5.homework4;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        long[] prefix = new long[6];
        prefix[0] = 0;
        prefix[1] = 1;
        prefix[2] = 4;
        prefix[3] = 9;
        prefix[4] = 16;
        prefix[5] = 25;
        long[][] walks = new long[2][2];
        walks[0][0] = 2;
        walks[0][1] = 4;
        walks[1][0] = 1;
        walks[1][1] = 3;
        assertTrue("1\n2".equals(Main.getAnswers(prefix, walks)));
    }

    @Test
    void myTest1() {
        long[] prefix = new long[6];
        prefix[0] = 0;
        prefix[1] = 1;
        prefix[2] = 4;
        prefix[3] = 9;
        prefix[4] = 16;
        prefix[5] = 25;
        long[][] walks = new long[1][2];
        walks[0][0] = 4;
        walks[0][1] = 2;
        assertTrue("-1".equals(Main.getAnswers(prefix, walks)));
    }

    @Test
    void myTest2() {
        long[] prefix = new long[6];
        prefix[0] = 0;
        prefix[1] = 1;
        prefix[2] = 4;
        prefix[3] = 9;
        prefix[4] = 16;
        prefix[5] = 25;
        long[][] walks = new long[1][2];
        walks[0][0] = 1;
        walks[0][1] = 2;
        assertTrue("-1".equals(Main.getAnswers(prefix, walks)));
    }

    @Test
    void myTest3() {
        long[] prefix = new long[6];
        prefix[0] = 0;
        prefix[1] = 1;
        prefix[2] = 4;
        prefix[3] = 9;
        prefix[4] = 16;
        prefix[5] = 25;
        long[][] walks = new long[1][2];
        walks[0][0] = 2;
        walks[0][1] = 2;
        assertTrue("-1".equals(Main.getAnswers(prefix, walks)));
    }

    @Test
    void myTest4() {
        long[] prefix = new long[6];
        prefix[0] = 0;
        prefix[1] = 1;
        prefix[2] = 4;
        prefix[3] = 9;
        prefix[4] = 16;
        prefix[5] = 25;
        long[][] walks = new long[1][2];
        walks[0][0] = 5;
        walks[0][1] = 26;
        assertTrue("-1".equals(Main.getAnswers(prefix, walks)));
    }

}
