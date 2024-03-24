package algorithm5.homework4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int nHeight = Integer.parseInt(st.nextToken());
            int mLength = Integer.parseInt(st.nextToken());
            String[][] field = new String[nHeight][mLength];
            for (int i = 0; i < nHeight; i++) {
                st = new StringTokenizer(reader.readLine());
                for (int j = 0; j < mLength; j++) {
                    field[i][j] = st.nextToken();
                }
            }
            System.out.println(getScale(field));
        }
    }

    public static int getScale(String[][] field) {
        return 0;
    }
}