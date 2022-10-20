package main.my.homework1;

/*
Description:
Строительство лесенок
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Вася занимается строительством лесенок из блоков. Лесенка состоит из ступенек, при этом i-ая ступенька должна состоять ровно из i блоков.

По заданному числу блоков n определите максимальное количество ступенек в лесенке, которую можно построить из этих блоков.

Формат ввода
Ввводится одно число n (1 ≤ n ≤ 2^31 - 1).

Формат вывода
Выведите одно число — количество ступенек в лесенке.
 */

import java.util.Scanner;

public class Stage1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();
        long count = 1;
        long sum = 0;

        while (true) {
            sum += count;
            if (sum <= number) {
                count++;
            } else {
                break;
            }
        }
        System.out.println(--count);
        scanner.close();
    }
}
