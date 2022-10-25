package main.lecture._05PrefixSum;

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
        int left = Integer.parseInt(scanner.nextLine());
        int right = Integer.parseInt(scanner.nextLine());
        int[] prefix = makePrefixSum(nums);
        System.out.println(rsq(prefix, left, right));
        scanner.close();
    }

    public static int[] makePrefixSum(int[] nums) {
        int[] prefix = new int[nums.length + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            prefix[i] = prefix[i-1] + nums[i-1];
        }
        return prefix;
    }

    public static int rsq(int[] prefix, int left, int right) {
        return prefix[right] - prefix[left];
    }
}
