package main.lecture._04Tables;

/*
Description:
Дано два числа Х и У без ведущих нулей.
Необходимо проверить, можно ли получить первое из второго перестановкой цифр
 */

import java.util.Scanner;

public class CompareNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        System.out.println(solution(x, y));
    }

    public static boolean solution(int x, int y) {
        int[] digits_x = findMapNumber(x);
        int[] digits_y = findMapNumber(y);
        for (int i = 0; i < 10; i++) {
            if (digits_x[i] != digits_y[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] findMapNumber(int number) {
        int[] digits = new int[10];
        while (number > 0){
            int digit = number % 10;
            digits[digit] += 1;
            number /= 10;
        }
        return digits;
    }
}
