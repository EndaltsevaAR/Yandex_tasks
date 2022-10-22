package main.lecture._02LinearSearch;

/*
Description:
Дана последовательность чисел длинной N.
Найти максимальное число в последовательности, если гарантированно, что она не пустая
 */

import java.util.Scanner;

public class MaxElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        String[] seq = line.split(" ");
        System.out.println(maxElement(seq));
    }

    public static int maxElement(String[] seq) {
        int answer = Integer.parseInt(seq[0]);
        for (int i = 1; i < seq.length; i++) {
            if (Integer.parseInt(seq[i]) > answer) {
                answer = Integer.parseInt(seq[i]);
            }
        }
        return answer;
    }
}
