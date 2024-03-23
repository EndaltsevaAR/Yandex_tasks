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
            long[] squads = new long[nNumberSquads];
            for (int i = 0; i < nNumberSquads; i++) {
                squads[i] = (Long.parseLong(st.nextToken()));
            }
            long[][] walks = new long[mNumberWalks][2];
            for (int i = 0; i < mNumberWalks; i++) {
                st = new StringTokenizer(reader.readLine());
                for (int j = 0; j < 2; j++) {
                    walks[i][j] = (Long.parseLong(st.nextToken()));
                }
            }
            System.out.println(getAnswers(squads, walks));
        }
    }

    public static String getAnswers(long[] squads, long[][] walks) {
        return "";
    }
}