package main.my.homework2;

/*
Description:
Повторяющееся число
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Вам дана последовательность измерений некоторой величины. Требуется определить, повторялась ли какое-либо число,
причём расстояние между повторами не превосходило k.

Формат ввода
В первой строке задаются два числа n и k (1 ≤ n, k ≤ 105).

Во второй строке задаются n чисел, по модулю не превосходящих 109.

Формат вывода
Выведите YES, если найдется повторяющееся число и расстояние между повторами не превосходит k и NO в противном случае.
 */

import java.util.*;
import java.util.stream.Collectors;

public class RepeatNumber2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] lenAndRepeat = line.split(" ");
        String lineNums = scanner.nextLine();
        List<Integer> numbers = Arrays.stream(lineNums.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(solution(lenAndRepeat, numbers));
    }

    public static String solution(String[] lenAndRepeat, List<Integer> numbers) {
        Map<Integer, List<Integer>> numMap = findMap(numbers);
        for (Map.Entry<Integer, List<Integer>> pair: numMap.entrySet()) {
            if (pair.getValue().size() > 1) {
                for (int i = 1; i < pair.getValue().size(); i++) {
                    if (pair.getValue().get(i) - pair.getValue().get(i - 1) <= Integer.parseInt(lenAndRepeat[1])) {
                        return "YES";
                    }
                }
            }
        }
        return "NO";
    }

    private static Map<Integer, List<Integer>> findMap(List<Integer> numbers) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < numbers.size(); i++) {
            map.putIfAbsent(numbers.get(i), new ArrayList<>());
            map.get(numbers.get(i)).add(i);
        }
        return map;
    }
}
