package algorithm5.homework3;

/**
Вам дана последовательность измерений некоторой величины. Требуется определить, повторялась ли какое-либо число, 
причём расстояние между повторами не превосходило k.

Формат ввода
В первой строке задаются два числа n и k (1 ≤ n, k ≤ 10^5).

Во второй строке задаются n чисел, по модулю не превосходящих 10^9.

Формат вывода
Выведите YES, если найдется повторяющееся число и расстояние между повторами не превосходит k и NO в противном случае.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        String numbers = scanner.nextLine();
        String digits = scanner.nextLine();
        System.out.println(getAnswer(numbers, digits));
        scanner.close();
        ;
    }

    public static String getAnswer(String numbers, String digitsLine) {
        int allNumbers = Integer.parseInt(numbers.split(" ")[0]);
        int diff = Integer.parseInt(numbers.split(" ")[1]);
        String[] digits = digitsLine.split(" ");
        Map<String, List<Integer>> digitsMap = getDigitsMap(digits);
        return searchMap(diff, digitsMap);
    }

    private static Map<String, List<Integer>> getDigitsMap(String[] digits) {
        Map<String, List<Integer>> digitsMap = new HashMap<>();
        for (int i = 0; i < digits.length; i++) {
            List<Integer> indexes = null;
            if (!digitsMap.containsKey(digits[i])) {
                indexes = new ArrayList<>();
            } else {
                indexes = digitsMap.get(digits[i]);
            }
            indexes.add(i);
            digitsMap.put(digits[i], indexes);
        }

        return digitsMap;
    }

    private static String searchMap(int diff, Map<String, List<Integer>> digitsMap) {
        for (Map.Entry<String, List<Integer>> pair : digitsMap.entrySet()) {
            if (pair.getValue().size() > 1) {
                for (int i = 1; i < pair.getValue().size(); i++) {
                    if (pair.getValue().get(i) - pair.getValue().get(i - 1) <= diff) {
                        return "YES";
                    }
                }
            }
        }
        return "NO";
    }
}