package algorithm5.homework4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        StringBuilder builder = new StringBuilder();
        for (int[] request : requests) {
            builder.append(getAnswer(digits, request)).append(" ");
        }
        return builder.toString().trim();
    }

    private static int getAnswer(int[] digits, int[] request) {
        int count = 0;
        for (int digit : digits) {
            if (digit >= request[0] && digit <= request[1]) {
                count++;
            }
        }
        return count;
    }
}