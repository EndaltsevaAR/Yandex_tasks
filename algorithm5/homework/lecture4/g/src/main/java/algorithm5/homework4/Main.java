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
            char[][] field = new char[nHeight][mLength];
            for (int i = 0; i < nHeight; i++) {
                String[] line = reader.readLine().split("");
                for (int j = 0; j < mLength; j++) {
                    field[i][j] = line[j].charAt(0);
                }
            }
            System.out.println(getScale(nHeight, mLength, field));
        }
    }

    public static int getScale(int nHeight, int mLength, char[][] field) {
        int left = 1;
        int right = Math.min(nHeight, mLength);
        int maxScale = left;

        while (left <= right) {
            int med = (left + right + 1) / 2;
            if (isBuilded(med, field)) {
                maxScale = med;
                left = med + 1;
            } else {
                right = med - 1;
            }
        }
        return maxScale;
    }

    private static boolean isBuilded(int scale, char[][] field) {
        for (int i = 0; i <= field.length - scale * 3; i++) {
            for (int j = 0; j <= field[i].length - scale * 2; j++) {
                if (field[i][j] == '#' && isCross(scale, i, j, field)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isCross(int scale, int y, int x, char[][] field) {
        // проверяем столбец
        for (int i = y; i < y + 3 * scale; i++) {
            for (int j = x; j < x + scale; j++) {
                if (i >= field.length || j >= field[0].length || j < 0 || field[i][j] != '#') {
                    return false;
                }
            }
        }

        // проверяем строку
        for (int i = y + scale; i < y + scale * 2; i++) {
            for (int j = x - scale; j < x + scale * 2; j++) {
                if (i >= field.length || j >= field[0].length || j < 0 || field[i][j] != '#') {
                    return false;
                }
            }
        }
        return true;
    }
}