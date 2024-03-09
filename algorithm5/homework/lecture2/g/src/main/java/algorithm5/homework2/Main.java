package algorithm5.homework2;

/**
Дан массив целых положительных чисел a длины n. Разбейте его на минимально возможное количество отрезков, 
чтобы каждое число было не меньше длины отрезка которому оно принадлежит. Длиной отрезка считается количество чисел в нем.

Разбиение массива на отрезки считается корректным, если каждый элемент принадлежит ровно одному отрезку.

Формат ввода
Первая строка содержит одно целое число t (1 ≤ t ≤ 1 000) — количество наборов тестовых данных. Затем следуют t наборов тестовых данных.

Первая строка набора тестовых данных содержит одно целое число n (1 ≤ n ≤ 105) — длину массива.

Следующая строка содержит n целых чисел a1, a2, …, an (1 ≤ ai ≤ n) — массив a.

Гарантируется, что сумма n по всем наборам тестовых данных не превосходит 2 ⋅ 105.

Формат вывода
Для каждого набора тестовых данных в первой строке выведите число k — количество отрезков в вашем разбиении.

Затем в следующей строке выведите k чисел len1, len2, …, lenk
 */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int numberPairs = Integer.parseInt(scanner.nextLine());
        String[] lengths = new String[numberPairs];
        String[] stringArrays = new String[numberPairs];
        for (int i = 0; i < numberPairs; i++) {
            lengths[i] = scanner.nextLine();
            stringArrays[i] = scanner.nextLine();
        }
        System.out.println(separateArrays(lengths, stringArrays));
        scanner.close();

    }

    public static String separateArrays(String[] lengths, String[] stringArrays) {
        StringBuilder totalBuilder = new StringBuilder();
        for (int i = 0; i < stringArrays.length; i++) {
            StringBuilder currentBuilder = new StringBuilder();
            String[] elementsString = stringArrays[i].split(" ");
            int[] elements = new int[Integer.parseInt(lengths[i])];
            int countSubArrays = 0;
            int minElementValue = 0;
            int currentArrayLength = 0;

            for (int j = 0; j < elementsString.length; j++) {
                elements[j] = Integer.parseInt(elementsString[j]);
                if (currentArrayLength == 0) { // создание с нуля
                    minElementValue = elements[j];
                    currentArrayLength++;
                } else if (currentArrayLength == minElementValue || elements[j] <= currentArrayLength) {
                    countSubArrays += 1;
                    currentBuilder.append(currentArrayLength).append(" ");
                    currentArrayLength = 1;
                    minElementValue = elements[j];
                } else if (elements[j] == currentArrayLength + 1) {
                    currentArrayLength++;
                    countSubArrays += 1;
                    currentBuilder.append(currentArrayLength).append(" ");
                    currentArrayLength = 0;
                    minElementValue = 0;
                } else {
                    currentArrayLength++;
                    if (elements[j] < minElementValue) {
                        minElementValue = elements[j];
                    }
                }
            }
            if (currentArrayLength != 0) {
                totalBuilder.append(countSubArrays + 1).append("\n");
                currentBuilder.append(currentArrayLength).append("\n");
            } else {
                totalBuilder.append(countSubArrays).append("\n");
                currentBuilder.deleteCharAt(currentBuilder.length() - 1).append("\n");
            }
            totalBuilder.append(currentBuilder);
        }
        return totalBuilder.deleteCharAt(totalBuilder.length() - 1).toString();
    }
}