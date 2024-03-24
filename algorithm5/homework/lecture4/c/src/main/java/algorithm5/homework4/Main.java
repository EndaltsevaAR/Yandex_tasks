package algorithm5.homework4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int nNumberSquads = Integer.parseInt(st.nextToken());
            int mNumberWalks = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(reader.readLine());
            long[] squadsPrefix = new long[nNumberSquads + 1];
            for (int i = 1; i <= nNumberSquads; i++) {
                squadsPrefix[i] = squadsPrefix[i - 1] + (Long.parseLong(st.nextToken()));
            }
            long[][] walks = new long[mNumberWalks][2];
            for (int i = 0; i < mNumberWalks; i++) {
                st = new StringTokenizer(reader.readLine());
                for (int j = 0; j < 2; j++) {
                    walks[i][j] = (Long.parseLong(st.nextToken()));
                }
            }
            System.out.println(getAnswers(squadsPrefix, walks));
        }
    }

    public static String getAnswers(long[] squadsPrefix, long[][] walks) {
        StringBuilder builder = new StringBuilder();
        for (long[] walk : walks) {
            builder.append(getAnswerPerRequest(walk, squadsPrefix)).append("\n");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private static int getAnswerPerRequest(long[] walk, long[] squadsPrefix) {
        int count = 0;
        int lengthWalk = (int) walk[0];
        long sumSalk = walk[1];

        int left = Math.max(1, lengthWalk); // 1
        int right = squadsPrefix.length - 1;
        while (left < right) {
            int med = (left + right + 1) / 2;
            if (med - lengthWalk < 0) {
                return -1;
            }
            long sum = squadsPrefix[med] - squadsPrefix[med - lengthWalk];
            if (sum == sumSalk) {
                return med - lengthWalk + 1;
            } else if (sum < sumSalk) {
                left = med;
            } else {
                right = med - 1;
            }
        }
        return -1;
    }
}