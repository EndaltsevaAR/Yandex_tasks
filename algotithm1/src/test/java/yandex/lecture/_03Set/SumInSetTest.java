package yandex.lecture._03Set;

import static org.junit.Assert.*;

import org.junit.Test;

import yandex.lecture._03Set.SumInSet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SumInSetTest {
    @Test
    public void test1() {
        System.out.println("Test from task");
        String line = "1 3 5 8 2";
        List<Integer> nums = Arrays.stream(line.split(" ")).map(s -> Integer.parseInt(s.trim()))
                .collect(Collectors.toList());
        int x = 7;
        String result = SumInSet.solution(nums, x);
        assertEquals(result, "2, 5");
    }
}
