package algorithm5.homework3;

/**
Вам даны три списка чисел. Найдите все числа, которые встречаются хотя бы в двух из трёх списков.

Формат ввода
Во входных данных описывается три списка чисел. Первая строка каждого описания списка состоит из длины списка n (1 ≤ n ≤ 1000). 
Вторая строка описания содержит список натуральных чисел, записанных через пробел. Числа не превосходят 109.

Формат вывода
Выведите все числа, которые содержатся хотя бы в двух списках из трёх, в порядке возрастания. Обратите внимание, 
что каждое число необходимо выводить только один раз.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int number1 = Integer.parseInt(scanner.nextLine());
        String[] numbers1 = scanner.nextLine().split(" ");
        int number2 = Integer.parseInt(scanner.nextLine());
        String[] numbers2 = scanner.nextLine().split(" ");
        int number3 = Integer.parseInt(scanner.nextLine());
        String[] numbers3 = scanner.nextLine().split(" ");

        List<String[]> lists = new ArrayList<>();
        lists.add(numbers1);
        lists.add(numbers2);
        lists.add(numbers3);
        System.out.println(getTotalList(lists));
        scanner.close();
    }

    public static String getTotalList(List<String[]> lists) {

        Map<Integer, Integer> counts = getMap(lists);
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Integer> pair : counts.entrySet()) {
            if (pair.getValue() > 1) {
                builder.append(pair.getKey()).append(" ");
            }
        }
        return builder.toString().trim();
    }

    private static Map<Integer, Integer> getMap(List<String[]> lists) {
        Map<Integer, Integer> counts = new TreeMap<>();
        for (int i = 0; i < lists.size(); i++) {
            Set<String> set = new HashSet<>(Arrays.asList(lists.get(i)));
            for (String elementSet : set) {
                Integer element = Integer.parseInt(elementSet);
                counts.put(element, counts.getOrDefault(element, 0) + 1);
            }
        }
        return counts;
    }
}