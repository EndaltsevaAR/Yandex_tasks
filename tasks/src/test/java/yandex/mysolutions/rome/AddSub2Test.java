package yandex.mysolutions.rome;

import static org.junit.Assert.*;

import main.my.rome.AddSub2;
import org.junit.Test;

public class AddSub2Test {

    @Test
    public void test() {
        System.out.println("Test 1 from task");
        int number = 5;
        int countChange = 2;
        int[] nums = new int[] { -1, 2, 4, 3, 0 };
        int result = AddSub2.solution(number, countChange, nums);
        assertEquals(result, 3);
    }

    @Test
    public void test2() {
        System.out.println("Test 2 from task: all even");
        int number = 4;
        int countChange = 1;
        int[] nums = new int[] { 2, 4, 6, 8 };
        int result = AddSub2.solution(number, countChange, nums);
        assertEquals(result, 4);
    }

    @Test
    public void test3() {
        System.out.println("Test 3 from task");
        int number = 5;
        int countChange = 1;
        int[] nums = new int[] { 0, 1, 2, 3, 4 };
        int result = AddSub2.solution(number, countChange, nums);
        assertEquals(result, 3);
    }

    @Test
    public void test4() {
        System.out.println("Test from task");
        int number = 6;
        int countChange = 2;
        int[] nums = new int[] { 0, 2, 1, -1, -2, 4 };
        int result = AddSub2.solution(number, countChange, nums);
        assertEquals(result, 4);
    }
}
