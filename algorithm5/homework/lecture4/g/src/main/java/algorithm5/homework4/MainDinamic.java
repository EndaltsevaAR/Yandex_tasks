package algorithm5.homework4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MainDinamic {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader("36"))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int nHeight = Integer.parseInt(st.nextToken());
            int mWidth = Integer.parseInt(st.nextToken());
            boolean[][] field = new boolean[nHeight][mWidth];

            int maxWidth = 0;
            for (int i = 0; i < nHeight; i++) {
                String line = reader.readLine();
                int currentWidth = 0;
                for (int j = 0; j < mWidth; j++) {
                    if (line.charAt(j) == '#') {
                        field[i][j] = true;
                        currentWidth++;
                    } else {
                        if (currentWidth != 0) {
                            if (currentWidth > maxWidth) {
                                maxWidth = currentWidth;
                            }
                            currentWidth = 0;
                        }
                    }

                }
            }
            long before = System.currentTimeMillis();
            System.out.println(getScale(nHeight, mWidth, field, maxWidth));
            long end = System.currentTimeMillis();
            System.out.println("After reading " + (before - startTime));
            System.out.println("All is " + (end - startTime));
        }
    }

    public static long getScale(int nHeight, int mLength, boolean[][] field, int maxWidth) {
        int maxScale = Math.min(Math.min(nHeight, mLength) / 3 + 1, maxWidth);
        boolean[][] scaleField = initWithOneScale(field);
        int currentScale = 2;
        while (currentScale <= maxScale) {
            scaleField = checkNextScale(scaleField, field, currentScale);
            if (!isStop(scaleField)) {
                return --currentScale;
            }
            currentScale++;
        }
        return currentScale;
    }

    private static boolean isStop(boolean[][] scaleField) {
        for (int i = 0; i < scaleField.length; i++) {
            if (scaleField[i][scaleField[i].length - 1]) {
                return true;
            } else {
                for (int j = 0; j < scaleField[0].length; j++) {
                    if (scaleField[i][j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean[][] initWithOneScale(boolean[][] field) {
        // последний столбец будет для булеана, есть там 2 элемента или больше или нет
        boolean[][] scaleField = new boolean[field.length][field[0].length + 1];

        for (int i = 1; i < scaleField.length - 1; i++) {
            int numberTrue = 0;
            for (int j = 1; j < scaleField[0].length - 2; j++) {
                if (field[i][j] && field[i - 1][j] && field[i + 1][j] && field[i][j - 1] && field[i][j + 1]) {
                    scaleField[i][j] = true;
                    numberTrue++;
                }
            }
            if (numberTrue >= 2) {
                scaleField[i][scaleField[0].length - 1] = true;
            }
        }
        return scaleField;
    }

    private static boolean[][] checkNextScale(boolean[][] scaleField, boolean[][] field, int currentScale) {
        boolean[][] scaleFieldNext = new boolean[scaleField.length][scaleField[0].length + 1];

        for (int i = 1; i < scaleFieldNext.length - 1; i++) {
            int numberTrue = 0;
            if (!scaleField[i][scaleField[0].length - 1]) { // если там меньше 2 пригодных ячеек, даже не имеет смысл
                                                            // проверять строку
                continue;
            }

            for (int j = 1; j < scaleFieldNext[0].length - 2; j++) {
                if (isCross(scaleField, j, i, currentScale, field)) {
                    scaleFieldNext[i][j] = true;
                    numberTrue++;
                }
            }
            if (numberTrue >= 2) {
                scaleFieldNext[i][scaleFieldNext[0].length - 1] = true;
            }
        }
        return scaleFieldNext;
    }

    private static boolean isCross(boolean[][] scaleField, int x, int y, int currentScale, boolean[][] field) {
        // проверка границ
        if (x - currentScale < 0 || x + (currentScale * 2) > field[0].length ||
                y - currentScale < 0 || y + (currentScale * 2) > field.length) {
            return false;
        }

        // проверка квадрата точек со скейлом на 1 меньше
        if (!(scaleField[y][x + 1] && scaleField[y + 1][x] &&
                scaleField[y + 1][x + 1])) {
            return false;
        }

        // проверка доп точек
        for (int i = 0; i < currentScale; i++) {
            if (!field[y - currentScale][x + i] || !field[y + currentScale * 2 - 1][x + i] ||
                    !field[y + i][x - currentScale] || !field[y + i][x + currentScale * 2 - 1]) {
                return false;
            }
        }

        return true;
    }
}
