package test.lecture._05TwoPointers;

import main.lecture._05TwoPointers.DescriptionMethod;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DescriptionMethodTest {
    @Test
    public void test() {
        int[] nums = {1, 3, 7, 8};
        int k = 4;
        int result = DescriptionMethod.solution(nums, k);
        assertEquals(result, 3);
    }
}
