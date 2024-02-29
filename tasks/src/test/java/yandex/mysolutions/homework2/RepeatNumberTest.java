package yandex.mysolutions.homework2;

import main.my.homework2.RepeatNumber2;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RepeatNumberTest {
    @Test
    public void test() {
        String line = "4 2";
        String[] lenAndRepeat = line.split(" ");
        String lineNums = "1 2 3 1";
        List<Integer> numbers = Arrays.stream(lineNums.split(" ")).map(Integer::parseInt).toList();
        String result = RepeatNumber2.solution(lenAndRepeat, numbers);
        String answer = "NO";
        assertEquals(result, answer);
    }

    @Test
    public void test2() {
        System.out.println("Test from task");
        String line = "6 2";
        String[] lenAndRepeat = line.split(" ");
        String lineNums = "1 2 3 1 2 3";
        List<Integer> numbers = Arrays.stream(lineNums.split(" ")).map(Integer::parseInt).toList();
        String result = RepeatNumber2.solution(lenAndRepeat, numbers);
        String answer = "NO";
        assertEquals(result, answer);
    }

    @Test
    public void test3() {
        System.out.println("Test from task");
        String line = "4 1";
        String[] lenAndRepeat = line.split(" ");
        String lineNums = "1 0 1 1";
        List<Integer> numbers = Arrays.stream(lineNums.split(" ")).map(Integer::parseInt).toList();
        String result = RepeatNumber2.solution(lenAndRepeat, numbers);
        String answer = "YES";
        assertEquals(result, answer);
    }
}
