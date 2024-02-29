package yandex.lecture._02LinearSearch;

import java.util.*;

/*
Description:
Дана последовательность чисел длинной N.
Найти первое (левое) вхождение положительного числа Х в нее
или вывести -1, если число Х не встречалось
 */

public class LinearSearch {
    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // String line = scanner.nextLine();
    // int x = scanner.nextInt();
    // scanner.close();
    // String[] seq = line.split(" ");
    // System.out.println(search(seq, x));
    // }

    public static int search(String[] seq, int x) {
        int answer = -1;
        for (int i = 0; i < seq.length; i++) {
            if (Integer.parseInt(seq[i]) == x && answer == -1) {
                answer = i;
            }
        }
        return answer;
    }
}
