package test.lecture.thirdSet;
import static org.junit.Assert.*;

import main.lecture.thirdSet.SumInSet;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SumInSetTest {
    @Test
    public void test1() {
        System.out.println("Test from task");
        String line = "1 3 5 8 2";
        List<Integer> nums = Arrays.stream(line.split(" ")).map(s -> Integer.parseInt(s.trim())).toList();
        int x = 7;
        String result = SumInSet.solution(nums, x);
        assertEquals(result, "2, 5");
    }
}
