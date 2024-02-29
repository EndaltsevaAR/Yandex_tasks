package yandex.lecture._05PrefixSum;

import static org.junit.Assert.*;

import org.junit.Test;

import yandex.lecture._05PrefixSum.FindZeros;

public class FindZerosTest {
    @Test
    public void test() {
        int[] nums = { 1, 1, 0, 1, 0, 0, 1 };
        int left = 2;
        int right = 5;
        int[] prefix = FindZeros.makePrefixZeros(nums);
        int result = FindZeros.countZeros(prefix, left, right);
        assertEquals(result, 2);
    }
}
