package main.my.homework2;

/*
Description:
Два из трех
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Вам даны три списка чисел. Найдите все числа, которые встречаются хотя бы в двух из трёх списков.

Формат ввода
Во входных данных описывается три списка чисел. Первая строка каждого описания списка состоит из длины
списка n (1 ≤ n ≤ 1000). Вторая строка описания содержит список натуральных чисел, записанных через пробел.
Числа не превосходят 109.

Формат вывода
Выведите все числа, которые содержатся хотя бы в двух списках из трёх, в порядке возрастания. Обратите внимание,
что каждое число необходимо выводить только один раз.
 */

import java.util.*;
import java.util.stream.Collectors;

public class TwoOfThree1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lineFLen = Integer.parseInt(scanner.nextLine());
        String line1 = scanner.nextLine();
        int lineSLen = Integer.parseInt(scanner.nextLine());
        String line2 = scanner.nextLine();
        int lineTLen = Integer.parseInt(scanner.nextLine());
        String line3 = scanner.nextLine();
        Set<Integer> result = findTwoOfThree(line1, line2, line3, lineFLen, lineSLen, lineTLen);
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
        scanner.close();
    }

    public static Set<Integer> findTwoOfThree(String line1, String line2, String line3, int lineFLen, int lineSLen, int lineTLen) {
        Set<Integer> set1 = findSet(line1);
        Set<Integer> set2 = findSet(line2);
        Set<Integer> set3 = findSet(line3);
        Set<Integer> result = new TreeSet<>();

        fullResultSets(result, set1, set2, set3);
        fullResultSets(result, set2, set1, set3);
        fullResultSets(result, set3, set2, set1);

        return result;
    }

    private static Set<Integer> findSet(String line1) {
        return Arrays.stream(line1.split(" ")).map(Integer::parseInt).collect(Collectors.toSet());
    }
    private static void fullResultSets(Set<Integer> result, Set<Integer> set, Set<Integer> firstSet, Set<Integer> secondSet) {
        for (Integer set_element : set) {
            if (firstSet.contains(set_element) || secondSet.contains(set_element)) {
                result.add(set_element);
            }
        }
    }
}
