package main.lecture._02LinearSearch;

/*
Description:
Дана последовательность чисел длинной N.
Найти последнее (правое) вхождение положительного числа Х в нее
или вывести -1, если число Х не встречалось
 */

import java.util.Scanner;

public class LinearSearchLastElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int x = scanner.nextInt();
        scanner.close();
        String[] seq = line.split(" ");
        System.out.println(search(seq, x));
    }

    public static int search(String[] seq, int x) {
        int answer = -1;
        for (int i = 0; i < seq.length; i++) {
            if (Integer.parseInt(seq[i]) == x) {
                answer = i;
            }
        }
        return answer;
    }
}
