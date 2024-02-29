package yandex.lecture._04Tables;

import java.util.*;

public class CountingSort {
    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // String line = scanner.nextLine();
    // List<Integer> nums = Arrays.stream(line.split(" ")).map(s ->
    // Integer.parseInt(s.trim())).toList();
    // System.out.println(Count(nums));
    // }

    public static List<Integer> Count(List<Integer> nums) {
        int minList = nums.stream().min(Comparator.naturalOrder()).get();
        int maxList = nums.stream().max(Comparator.naturalOrder()).get();
        int length = maxList - minList + 1;
        int[] counts = new int[length];
        for (Integer num : nums) {
            counts[num - minList] += 1;
        }
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                answer.add(i + minList);
            }
        }
        return answer;
    }
}
