package algorithm5.homework4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MainDinamic {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
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

            System.out.println(getScale(nHeight, mWidth, field, maxWidth));
        }
    }

    public static long getScale(int nHeight, int mLength, boolean[][] field, int maxWidth) {
        int maxScale = Math.min(Math.min(nHeight, mLength) / 3 + 1, maxWidth);
        boolean[][] initWithOneScale = initWithOneScale(field);

        return 0L;
    }

    private static boolean[][] initWithOneScale(boolean[][] field) {
        // последний столбец будет для булеана, есть там 2 элемента или больше или нет
        boolean[][] initWithOneScale = new boolean[field.length][field[0].length + 1];

        for (int i = 1; i < initWithOneScale.length - 1; i++) {
            int numberTrue = 0;
            for (int j = 1; j < initWithOneScale[0].length - 2; j++) {
                if (field[i][j] && field[i - 1][j] && field[i + 1][j] && field[i][j - 1] && field[i][j + 1]) {
                    initWithOneScale[i][j] = true;
                    numberTrue++;
                }
            }
            if (numberTrue >= 2) {
                initWithOneScale[i][initWithOneScale[0].length - 1] = true;
            }
        }
        return initWithOneScale;
    }
}
