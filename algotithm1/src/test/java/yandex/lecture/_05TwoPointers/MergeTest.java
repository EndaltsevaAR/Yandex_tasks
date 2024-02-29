package yandex.lecture._05TwoPointers;

import org.junit.Test;

import yandex.lecture._05TwoPointers.Merge;

import static org.junit.Assert.*;

public class MergeTest {
    @Test
    public void test() {
        int[] nums1 = { 1, 3, 7, 8 };
        int[] nums2 = { 2, 4, 5, 6 };
        int[] result = Merge.mergeArrays(nums1, nums2);
        int[] answer = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        assertArrayEquals(result, answer);
    }
}