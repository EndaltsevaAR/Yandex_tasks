package test.lecture._05PrefixSum;
import static org.junit.Assert.*;

import main.lecture._05PrefixSum.DescriptionMethod;
import org.junit.Test;

public class DescriptionMethodTest {
    @Test
    public void test() {
        int[] nums = {5, 3, 8, 1, 4, 6};
        int left = 2;
        int right = 5;
        int[] prefix = DescriptionMethod.makePrefixSum(nums);
        int result = DescriptionMethod.rsq(prefix, left, right);
        assertEquals(result, 13);
    }
}
