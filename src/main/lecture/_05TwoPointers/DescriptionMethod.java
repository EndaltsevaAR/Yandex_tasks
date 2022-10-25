package main.lecture._05TwoPointers;
/*
Description:
Дана отсортированная последовательность чисел длиной N и число K.
Необходимо найти количество пар чисел А, В, таких, что В - А > K
O(N)
 */

import java.util.Scanner;

public class DescriptionMethod {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] lines = line.split(" ");
        int[] nums = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            nums[i] = Integer.parseInt(lines[i]);
        }
        int k = Integer.parseInt(scanner.nextLine());
        System.out.println(solution(nums, k));
        scanner.close();
    }

    public static int solution(int[] nums, int k) {
        int count = 0;
        int right = 0;
        for (int num : nums) {
            while (right < nums.length && (nums[right] - num) <= k) {
                right++;
            }
            count += nums.length - right;
        }
        return count;
    }
}
