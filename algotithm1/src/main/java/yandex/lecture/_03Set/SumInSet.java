package yandex.lecture._03Set;
/*
Description:
Дана последовательность положительных чисел длиной N и число Х.
Нужно найти два различных сила A и В из последовательности, таких что А + В = Х
или вернуть пару 0,0, если таких пары чисел нет
 */

import java.util.*;
import java.util.stream.Collectors;

public class SumInSet {
    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // String line = scanner.nextLine();
    // List<Integer> nums = Arrays.stream(line.split(" ")).map(s ->
    // Integer.parseInt(s.trim())).toList();
    // int x = scanner.nextInt();
    // scanner.close();
    // System.out.println(solution(nums, x));
    // }

    public String solution(String line, int x) {
        Set<Integer> nums = Arrays.stream(line.split(" ")).map(s -> Integer.parseInt(s.trim()))
                .collect(Collectors.toSet());
        Set<Integer> prevNums = new HashSet<>();
        for (Integer num : nums) {
            if (prevNums.contains(x - num)) {
                return (x - num) + ", " + num;
            }
            prevNums.add(num);
        }
        return "0, 0";
    }
}
