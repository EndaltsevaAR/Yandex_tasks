package test.my.homework2;
import static org.junit.Assert.*;
import main.my.homework2.TwoOfThree1;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TwoOfThree1Test {
    @Test
    public void test1() {
        System.out.println("Test from task");
        int x1 = 2;
        String line1 = "3 1";
        int x2 = 2;
        String line2 = "1 3";
        int x3 = 2;
        String line3 = "1 2";
        Set<Integer> result = TwoOfThree1.findTwoOfThree(line1, line2, line3, x1, x2, x3);
        Set<Integer> answer = new TreeSet<>(Arrays.asList(1, 3));
        assertEquals(result, answer);
        System.out.println("result is " + result.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }

    @Test
    public void test2() {
        System.out.println("Test from task");
        int x1 = 3;
        String line1 = "1 2 2";
        int x2 = 3;
        String line2 = "3 4 3";
        int x3 = 1;
        String line3 = "5";
        Set<Integer> result = TwoOfThree1.findTwoOfThree(line1, line2, line3, x1, x2, x3);
        Set<Integer> answer = new TreeSet<>();
        assertEquals(result, answer);
        System.out.println("result is " + result.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}
