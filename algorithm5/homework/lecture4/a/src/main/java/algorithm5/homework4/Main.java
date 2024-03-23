package algorithm5.homework4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int nNumberDigits = Integer.parseInt(reader.readLine());
            int[] digits = new int[nNumberDigits];
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < nNumberDigits; i++) {
                digits[i] = (Integer.parseInt(st.nextToken()));
            }
            int nNumberRequests = Integer.parseInt(reader.readLine());
            int[][] requests = new int[nNumberRequests][];
            for (int i = 0; i < nNumberRequests; i++) {
                StringTokenizer stRequest = new StringTokenizer(reader.readLine());
                requests[i] = new int[2];
                for (int j = 0; j < requests[i].length; j++) {
                    requests[i][j] = (Integer.parseInt(stRequest.nextToken()));
                }
            }
            System.out.println(getRequestsAnswer(digits, requests));
        }
    }

    public static String getRequestsAnswer(int[] digits, int[][] requests) {
        Arrays.sort(digits);
        StringBuilder builder = new StringBuilder();
        for (int[] request : requests) {
            int leftIndex = binarySearchLeft(digits, request[0]);

            int rightIndex = binarySearchRight(digits, request[1]);

            if (rightIndex < leftIndex || (leftIndex == rightIndex && leftIndex == -1)) {
                builder.append(0).append(" ");
            } else {
                if (leftIndex == -1) {
                    leftIndex = 0;
                }
                if (rightIndex == -1) {
                    rightIndex = digits.length - 1;
                }
                builder.append(rightIndex - leftIndex + 1).append(" ");
            }

        }
        return builder.toString().trim();
    }

    private static int binarySearchLeft(int[] digits, int i) {
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

    private static int binarySearchRight(int[] digits, int i) {
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