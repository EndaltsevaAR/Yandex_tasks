package yandex.lecture._04Tables;

import static org.junit.Assert.*;

import org.junit.Test;

import yandex.lecture._04Tables.CountingSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CountingSortTest {
    @Test
    public void test1() {
        System.out.println("Test from task");
        String line = "1 3 2 5 8 2";
        List<Integer> nums = Arrays.stream(line.split(" ")).map(s -> Integer.parseInt(s.trim()))
                .collect(Collectors.toList());
        nums = CountingSort.Count(nums);
        List<Integer> tests = Arrays.stream(line.split(" ")).map(s -> Integer.parseInt(s.trim()))
                .collect(Collectors.toList());
        tests = tests.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        assertEquals(nums, tests);
    }
}
