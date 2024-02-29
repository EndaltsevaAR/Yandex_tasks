package yandex.lecture._05TwoPointers;

import java.util.Arrays;
import java.util.Scanner;

public class Merge {
    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // String line1 = scanner.nextLine();
    // String line2 = scanner.nextLine();
    // int[] nums1 = parseLine(line1);
    // int[] nums2 = parseLine(line2);
    // int[] result = mergeArrays(nums1, nums2);
    // System.out.println(Arrays.toString(result));
    // scanner.close();
    // }

    public static int[] mergeArrays(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        int first1 = 0, first2 = 0;
        for (int i = 0; i < nums1.length + nums2.length; i++) {
            if (first1 != nums1.length && first2 == nums2.length ||
                    nums1[first1] < nums2[first2]) {
                result[i] = nums1[first1++];
            } else {
                result[i] = nums2[first2++];
            }
        }
        return result;
    }

    private static int[] parseLine(String line) {
        String[] lines = line.split(" ");
        int[] nums = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            nums[i] = Integer.parseInt(lines[i]);
        }
        return nums;
    }
}
