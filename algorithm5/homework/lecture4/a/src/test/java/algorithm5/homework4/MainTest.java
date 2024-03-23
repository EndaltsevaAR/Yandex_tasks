package algorithm5.homework4;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        int[] digits = Arrays.stream("10 1 10 3 4".split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[][] requests = new int[4][];
        requests[0] = Arrays.stream("1 10".split("\\s+")).mapToInt(Integer::parseInt).toArray();
        requests[1] = Arrays.stream("2 9".split("\\s+")).mapToInt(Integer::parseInt).toArray();
        requests[2] = Arrays.stream("3 4".split("\\s+")).mapToInt(Integer::parseInt).toArray();
        requests[3] = Arrays.stream("2 2".split("\\s+")).mapToInt(Integer::parseInt).toArray();

        assertTrue("5 2 2 0".equals(Main.getRequestsAnswer(digits, requests)));
    }
}
