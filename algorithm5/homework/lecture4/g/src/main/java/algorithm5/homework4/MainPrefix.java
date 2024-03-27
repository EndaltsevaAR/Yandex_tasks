package algorithm5.homework4;

/**
Сервис Тындекс.Плюс так быстро растет, что для сотрудников и серверов потребовалось потребовалось построить новый офис.
Участок под застройку представляет из себя клетчатое поле n×m, часть клеток которого пригодна для строительства, а часть нет.
Новый офис должен выглядеть как знак "плюс"какого-то целого положительного размера k. Знак "плюс"размера k  — это такая 
клетчатая фигура, состоящая из пяти квадратов k×k клеток, при этом есть один центральный квадрат, 
а остальные четыре являются его соседями по стороне.

Новый офис должен быть как можно больше, поэтому необходимо найти максимальное k, такое что офис удастся 
разместить на участке под застройку.

Определите максимальное k. Гарантируется, что он можно построить офис хотя бы с k=1.

Формат ввода
В первой строке задано два целых числа n и m (1≤n,m≤2000) — длина и ширина участка под застройку.
В каждой из последующих n строк задана строка, состоящая из m символов, j-й символ в i-й строке равен #, 
если клетка с координатами (i,j) пригодна для строительства и . иначе.

Формат вывода
Выведите одно целое положительное число — максимально возможное k.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MainPrefix {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
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

            int[][] prefixHorizontal = new int[nHeight][mLength + 1];
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
        for (int i = 0; i < scale; i++) {
            if (!(prefixVertical[y][x + i] - prefixVertical[y - scale][x + i] == scale &&
                    (prefixVertical[y + scale * 2][x + i] - prefixVertical[y][x + i] == scale * 2) &&
                    (prefixHorizontal[y + i][x] - prefixHorizontal[y + i][x - scale] == scale) &&
                    (prefixHorizontal[y + i][x + scale * 2] - prefixHorizontal[y + i][x] == scale * 2))) {
                return false;
            }
        }
        return true;
    }
}
