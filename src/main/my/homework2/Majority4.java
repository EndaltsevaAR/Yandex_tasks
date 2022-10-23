package main.my.homework2;

/*
Description:
Majority
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Majority (в дословном переводе "большинство") — это значение элемента, который в массиве длиной n встречается более
чем n / 2 раз. Определите majority массива, если гарантируется, что такой элемент существует.

Формат ввода
В первой строке вводится число n (1 ≤ n ≤ 5 × 104).
Во второй строке вводится n чисел через пробел, числа не превосходят 109 по модулю.

Формат вывода
Выведите majority массива.
 */

import java.util.*;
import java.util.stream.Collectors;

public class Majority4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = Integer.parseInt(scanner.nextLine());
        String lineNums = scanner.nextLine();
        List<Integer> numbers = Arrays.stream(lineNums.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(solution(len, numbers));
    }

    public static int solution(int len, List<Integer> numbers) {
        Map<Integer, Integer> numMap = findMap(numbers);
        for (Map.Entry<Integer, Integer> pair : numMap.entrySet()) {
            if (pair.getValue() > len / 2) {
                return pair.getKey();
            }
        }
        return -1;
    }

    private static Map<Integer, Integer> findMap(List<Integer> numbers) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer number : numbers) {
            map.putIfAbsent(number, 0);
            map.put(number, map.get(number) + 1);
        }
        return map;
    }
}
