package algorithm5.homework4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MainPrefix {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("27"))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int nHeight = Integer.parseInt(st.nextToken());
            int mLength = Integer.parseInt(st.nextToken());
            boolean[][] field = new boolean[nHeight][mLength];

            for (int i = 0; i < nHeight; i++) {
                String line = reader.readLine();
                for (int j = 0; j < mLength; j++) {
                    if (line.charAt(j) == '#') {
                        field[i][j] = true;
                    }
                }
            }

            int[][] prefixHorizontal = new int[nHeight][mLength + 1]; // можно потом будет объединить с вводом
            int[][] prefixVertical = new int[nHeight + 1][mLength];

            for (int i = 0; i < prefixHorizontal.length; i++) {
                for (int j = 1; j < prefixHorizontal[0].length; j++) {
                    prefixHorizontal[i][j] = prefixHorizontal[i][j - 1];
                    if (field[i][j - 1]) {
                        prefixHorizontal[i][j]++;
                    }
                }
            }

            for (int i = 0; i < prefixVertical[0].length; i++) {
                for (int j = 1; j < prefixVertical.length; j++) {
                    prefixVertical[j][i] = prefixVertical[j - 1][i];
                    if (field[j - 1][i]) {
                        prefixVertical[j][i]++;
                    }
                }
            }

            System.out.println(getScale(nHeight, mLength, field, prefixHorizontal, prefixVertical));
        }
    }

    public static int getScale(int nHeight, int mLength, boolean[][] field, int[][] prefixHorizontal,
            int[][] prefixVertical) {
        int left = 1;
        int right = Math.min(nHeight, mLength) / 3;
        int maxScale = left;

        while (left <= right) {
            int med = (left + right + 1) / 2;
            if (isBuilded(med, field, prefixHorizontal, prefixVertical)) {
                maxScale = med;
                left = med + 1;
            } else {
                right = med - 1;
            }
        }
        return maxScale;
    }

    private static boolean isBuilded(int scale, boolean[][] field, int[][] prefixHorizontal, int[][] prefixVertical) {
        for (int i = scale; i <= field.length - scale * 2; i++) {
            for (int j = scale; j <= field[i].length - scale * 2; j++) {
                if (field[i][j] && isCross(scale, i, j, prefixHorizontal, prefixVertical)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isCross(int scale, int y, int x, int[][] prefixHorizontal,
            int[][] prefixVertical) {
        // if (y + 3 * scale > field.length || x + scale > field[0].length || x - scale
        // < 0)
        // return false;

        // проверяем столбец
        for (int i = 0; i < scale; i++) {
            if (!(prefixVertical[y][x + i] - prefixVertical[y - scale][x + i] == scale &&
                    (prefixVertical[y + scale * 2 - 1][x + i] - prefixVertical[y][x + i] == scale * 2 - 1) &&
                    (prefixHorizontal[y + i][x] - prefixHorizontal[y + i][x - scale] == scale) &&
                    (prefixHorizontal[y + i][x + scale * 2 - 1] - prefixHorizontal[y + i][x] == scale * 2 - 1))) {
                return false;
            }
        }

        return true;
    }
}
