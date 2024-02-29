package yandex.lecture._06BinarySearch;
/*
"""
Description:
Юра решил подготовиться к собеседованию в Яндексе. Он
выбрал на сайте leetcode N задач. В первый день Юра решил K
задач, а в каждый следующий день Юра решал на одну задачу больше, чем в предыдущий
день. Определите, сколько дней уйдет у Юры на подготовку к
собеседованию
Решение: left = 0, right = N - количество задач
"""
 */

import java.util.Scanner;

public class Interview {
    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // int number = Integer.parseInt(scanner.nextLine());
    // int tasks = Integer.parseInt(scanner.nextLine());
    // System.out.println(leftSearch(number, tasks));
    // }

    public static int leftSearch(int number, int tasks) {
        int left = 0;
        int right = number;
        while (left < right) {
            int m = (left + right) / 2;
            if ((tasks + (tasks + m - 1)) * m / 2 >= number) {
                right = m;
            } else {
                left = m + 1;
            }
        }
        return left;
    }
}
