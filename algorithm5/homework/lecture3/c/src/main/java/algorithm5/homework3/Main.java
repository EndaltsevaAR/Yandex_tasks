package algorithm5.homework3;

/**
Дан массив a из n чисел. Найдите минимальное количество чисел, после удаления которых попарная разность 
оставшихся чисел по модулю не будет превышать 1, то есть после удаления ни одно число не должно отличаться от 
какого-либо другого более чем на 1.

Формат ввода
Первая строка содержит одно целое число n (1≤n≤2⋅10^5) — количество элементов массива a.
Вторая строка содержит n целых чисел a1,a2,…,an (0≤ai≤10^5) — элементы массива a.

Формат вывода
Выведите одно число — ответ на задачу. 
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int numberDigits = Integer.parseInt(scanner.nextLine());
        String digits = scanner.nextLine();
        System.out.println(getDeletedNumber(numberDigits, digits));
        scanner.close();
    }

    public static int getDeletedNumber(int numberDigits, String digitsStr) {
        int[] digits = new int[numberDigits];
        String[] splited = digitsStr.split(" ");
        for (int i = 0; i < splited.length; i++) {
            digits[i] = Integer.parseInt(splited[i]);
        }

        TreeMap<Integer, Integer> counts = new TreeMap<>();
        for (int i = 0; i < splited.length; i++) {
            if (!counts.containsKey(digits[i])) {
                counts.put(digits[i], 0);
            }
            counts.put(digits[i], counts.get(digits[i]) + 1);
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = counts.entrySet().iterator();
        Map.Entry<Integer, Integer> firstDigit = null;
        if (iterator.hasNext()) {
            firstDigit = iterator.next();
        }

        Map.Entry<Integer, Integer> secondDigit = null;
        if (iterator.hasNext()) {
            secondDigit = iterator.next();
        }

        if (firstDigit == null) {
            return 0;
        }

        int firstSavedDigit = firstDigit.getKey();
        int secondSavedDigit = firstDigit.getKey();
        int maxSumElements = firstDigit.getValue();
        while (firstDigit != null) {
            int sum = firstDigit.getValue();
            if (isSecondNext(secondDigit, firstDigit)) {
                sum += secondDigit.getValue();
            }
            if (sum >= maxSumElements) {
                firstSavedDigit = firstDigit.getKey();
                if (isSecondNext(secondDigit, firstDigit)) {
                    secondSavedDigit = secondDigit.getKey();
                } else {
                    secondSavedDigit = firstDigit.getKey();
                }
                maxSumElements = sum;
            }
            firstDigit = secondDigit;
            if (iterator.hasNext()) {
                secondDigit = iterator.next();
            } else {
                secondDigit = null;
            }

        }

        int deletedCount = 0;
        for (Map.Entry<Integer, Integer> pair : counts.entrySet()) {
            if (pair.getKey() != firstSavedDigit && pair.getKey() != secondSavedDigit) {
                deletedCount += pair.getValue();
            }
        }

        return deletedCount;
    }

    private static boolean isSecondNext(Entry<Integer, Integer> secondDigit, Entry<Integer, Integer> firstDigit) {
        return (secondDigit != null && secondDigit.getKey() - firstDigit.getKey() == 1);
    }
}