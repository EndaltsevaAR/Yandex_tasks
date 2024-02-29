package yandex.lecture._05PrefixSum;
/*
Description:
Дана последовательность числе длиной N и M запросов.
Запросы: "Сколько нулей на полуинтервале [L, R)?
Сложность O(N + M)
 */

import java.util.Scanner;

public class FindZeros {
    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // String line = scanner.nextLine();
    // String[] lines = line.split(" ");
    // int[] nums = new int[lines.length];
    // for (int i = 0; i < lines.length; i++) {
    // nums[i] = Integer.parseInt(lines[i]);
    // }
    // int left = Integer.parseInt(scanner.nextLine());
    // int right = Integer.parseInt(scanner.nextLine());
    // int[] prefix = makePrefixZeros(nums);
    // System.out.println(countZeros(prefix, left, right));
    // scanner.close();
    // }

    public static int[] makePrefixZeros(int[] nums) {
        int[] prefix = new int[nums.length + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            if (nums[i - 1] == 0) {
                prefix[i] = prefix[i - 1] + 1;
            } else {
                prefix[i] = prefix[i - 1];
            }
        }
        return prefix;
    }

    public static int countZeros(int[] prefix, int left, int right) {
        return prefix[right] - prefix[left];
    }
}
