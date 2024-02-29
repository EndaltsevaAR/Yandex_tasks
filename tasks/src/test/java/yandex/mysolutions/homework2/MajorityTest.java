package yandex.mysolutions.homework2;

import main.my.homework2.Majority4;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class MajorityTest {
    @Test
    public void test() {
        int x = 3;
        System.out.println("Test 1 from task");
        List<Integer> list = Arrays.asList(1, 2, 1);
        int result = Majority4.solution(x, list);
        assertEquals(result, 1);
    }

    @Test
    public void test2() {
        int x = 7;
        System.out.println("Test 2 from task");
        List<Integer> list = Arrays.asList(7, 5, 5, 5, 5, 4, 5);
        int result = Majority4.solution(x, list);
        assertEquals(result, 5);
    }

    @Test
    public void test3() {
        int x = 4;
        System.out.println("Test 3 from task");
        List<Integer> list = Arrays.asList(3, 3, 3, 1);
        int result = Majority4.solution(x, list);
        assertEquals(result, 3);
    }

}
