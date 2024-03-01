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

    public String findSumNumbers(String line, int x) {
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
