package yandex.lecture._06BinarySearch;

/*
Description:
В управляющий совет школы входят родители, учителя и учащиеся
школы, причем родителей должно быть не менее одной трети от общего
числа членов совета. В настоящий момент в совет входит N человек, из
них K родителей.
Определите, сколько родителей нужно дополнительно ввести в совет, чтобы их число
стало составлять не менее трети от числа членов совета.
Решение:
left = 0, right - N - общее количество людей в совете
 */

import java.util.Scanner;

public class School {
    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // int number = Integer.parseInt(scanner.nextLine());
    // int parents = Integer.parseInt(scanner.nextLine());
    // System.out.println(leftSearch(number, parents));
    // }

    public static int leftSearch(int number, int parents) {
        int left = 0;
        int right = number;
        while (left < right) {
            int m = (left + right) / 2;
            if ((parents + m) * 3 >= number + m) {
                right = m;
            } else {
                left = m + 1;
            }
        }
        return left;
    }
}
