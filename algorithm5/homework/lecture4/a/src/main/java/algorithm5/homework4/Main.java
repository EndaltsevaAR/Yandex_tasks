package algorithm5.homework4;

/**
Дан массив из N целых чисел. Все числа от −10^9 до 10^9.
Нужно уметь отвечать на запросы вида “Cколько чисел имеют значения от L до R?”.

Формат ввода
Число N (1≤N≤10^5). Далее N целых чисел.
Затем число запросов K (1≤K≤10^5).
Далее K пар чисел L, R (−10^9≤L≤R≤10^9) — собственно запросы.

Формат вывода
Выведите K чисел — ответы на запросы.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int nNumberDigits = Integer.parseInt(reader.readLine());
            long[] digits = new long[nNumberDigits];
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < nNumberDigits; i++) {
                digits[i] = (Long.parseLong(st.nextToken()));
            }
            int nNumberRequests = Integer.parseInt(reader.readLine());
            long[][] requests = new long[nNumberRequests][];
            for (int i = 0; i < nNumberRequests; i++) {
                StringTokenizer stRequest = new StringTokenizer(reader.readLine());
                requests[i] = new long[2];
                for (int j = 0; j < requests[i].length; j++) {
                    requests[i][j] = (Long.parseLong(stRequest.nextToken()));
                }
            }
            System.out.println(getRequestsAnswer(digits, requests));
        }
    }

    public static String getRequestsAnswer(long[] digits, long[][] requests) {
        Arrays.sort(digits);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < requests.length; i++) {
            int leftIndex = binarySearchLeft(digits, requests[i][0]);
            int rightIndex = binarySearchRight(digits, requests[i][1]);
            if (leftIndex == rightIndex && leftIndex == -1) {
                builder.append(0).append(" ");
            } else {
                if (leftIndex == -1) {
                    leftIndex = 0;
                }
                if (rightIndex == -1) {
                    rightIndex = digits.length - 1;
                }
                if (rightIndex < leftIndex) {
                    builder.append(0).append(" ");
                } else {
                    builder.append(rightIndex - leftIndex + 1).append(" ");
                }

            }

        }
        return builder.toString().trim();
    }

    private static int binarySearchLeft(long[] digits, long i) {
        int left = 0;
        int right = digits.length - 1;

        if (i < digits[left] || i > digits[right]) {
            return -1;
        }
        while (left < right) {
            int med = (left + right) / 2;
            if (digits[med] >= i) {
                right = med;
            } else {
                left = med + 1;
            }
        }
        return left;
    }

    private static int binarySearchRight(long[] digits, long i) {
        int left = 0;
        int right = digits.length - 1;

        if (i < digits[left] || i > digits[right]) {
            return -1;
        }
        while (left < right) {
            int med = (left + right + 1) / 2;
            if (digits[med] <= i) {
                left = med;
            } else {
                right = med - 1;
            }
        }
        return right;
    }
}