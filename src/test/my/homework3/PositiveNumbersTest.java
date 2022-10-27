package test.my.homework3;
import main.my.homework3.PositiveNumvbers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PositiveNumbersTest {
    @Test
    public void test() {
        int len = 5;
        List<Integer> numbers = Arrays.stream("2 -1 2 -2 3".split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int numberQueries = 4;
        List<List<Integer>> queries = new ArrayList<>();
        queries.add(Arrays.stream("1 1".split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        queries.add(Arrays.stream("1 3".split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        queries.add(Arrays.stream("2 4".split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        queries.add(Arrays.stream("1 5".split(" ")).map(Integer::parseInt).collect(Collectors.toList()));


        List<Integer> result = PositiveNumvbers.findPositiveCounters(numbers, queries);
        List<Integer> answer = Arrays.stream("1 2 1 3".split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        assertEquals(result, answer);
    }
}
