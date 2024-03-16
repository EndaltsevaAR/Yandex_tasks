package algorithm5.homework2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        String numbers = scanner.nextLine();
        int rows = Integer.parseInt(numbers.split(" ")[0]);
        int cols = Integer.parseInt(numbers.split(" ")[1]);
        char[][] paint = initArray(rows, cols, scanner);

        scanner.close();
        if (rows == 1 && cols == 1) {
            System.out.println("NO");
            return;
        }
        System.out.println(decodePaint(paint));
    }

    public static char[][] initArray(int rows, int cols, Scanner scanner) {
        char[][] paint = new char[rows + 2][cols + 2];

        // границы в -1
        for (int i = 0; i < paint.length; i++) {
            paint[0][i] = '.';
            paint[paint.length - 1][i] = '.';
            paint[i][0] = '.';
            paint[i][paint.length - 1] = '.';
        }

        for (int i = 1; i < paint.length - 1; i++) {
            String[] line = scanner.nextLine().split("");
            for (int j = 0; j < line.length; j++) {
                paint[i][j + 1] = line[j].charAt(0);
            }
        }
        return paint;
    }

    public static String decodePaint(char[][] paint) {
        List<List<Integer>> figures = findFigures(paint);

        if (figures.size() == 2) {
            return printYes(figures, paint);
        } else if (figures.size() == 1) {
            return printAnswerFromOneFigure(figures, paint);
        } else {
            return "NO";
        }
    }

    private static List<List<Integer>> findFigures(char[][] paint) {
        List<List<Integer>> figures = new ArrayList<>();

        int yMin = 0;
        int xMin = 0;
        int yMax = 0;
        int xMax = 0;
        for (int i = 1; i < paint.length - 1; i++) {
            for (int j = 1; j < paint[i].length - 1; j++) {
                if (paint[i][j] == '#') {
                    List<Integer> figure = new ArrayList<>();
                    yMin = i;
                    figure.add(yMin);
                    xMin = j; // yMin
                    figure.add(xMin); // xMin
                    for (int k = j + 1; k < paint[i].length; k++) { // ищем максимум по х
                        if (paint[i][k] == '.' || paint[i][k] == 'N') {
                            xMax = k - 1;
                        }
                    }
                    boolean isYMaxFind = false;

                    // смотрим все строчки вниз до поиска
                    for (int yInFigure = yMin + 1; yInFigure < paint.length && !isYMaxFind; yInFigure++) {
                        // максимум по y
                        for (int xInFigure = xMin; xInFigure <= xMax && !isYMaxFind; xInFigure++) {
                            if (paint[yInFigure][xInFigure] != '#') {
                                yMax = yInFigure - 1;
                                isYMaxFind = true;
                            }
                        }
                    }
                    figure.add(Math.min(yMax, paint.length - 1));
                    figure.add(Math.min(xMax, paint[i].length - 1));

                    // перекрашивание найденного прямоугольника
                    for (int yInFigure = yMin; yInFigure <= yMax; yInFigure++) {
                        for (int xInFigure = xMin; xInFigure <= xMax; xInFigure++) {
                            paint[yInFigure][xInFigure] = 'N';
                        }
                    }
                    figures.add(figure);
                }
            }
        }

        return figures;

    }

    private static String printYes(List<List<Integer>> figures, char[][] paint) {
        char letter = 'a';
        for (List<Integer> figure : figures) {
            for (int i = figure.get(0); i <= figure.get(2); i++) {
                for (int j = figure.get(1); j <= figure.get(3); j++) {
                    paint[i][j] = letter;
                }
            }
            letter++;
        }
        return printPaint(paint);
    }

    private static String printAnswerFromOneFigure(List<List<Integer>> figures, char[][] paint) {
        List<Integer> figure = figures.get(0);
        if (figure.get(0).equals(figure.get(2)) && figure.get(1).equals(figure.get(3))) {
            return "NO";
        }
        if ((figure.get(2) - figure.get(0)) - (figure.get(3) - figure.get(1)) > 0) { // длина по У больше
            for (int i = figure.get(0); i <= figure.get(0) + 1; i++) {
                for (int j = figure.get(1); j <= figure.get(3); j++) {
                    paint[i][j] = 'a';
                }
            }

            for (int i = figure.get(0) + 1; i <= figure.get(2); i++) {
                for (int j = figure.get(1); j <= figure.get(3); j++) {
                    paint[i][j] = 'b';
                }
            }

        } else { // делим по х
            for (int i = figure.get(0); i <= figure.get(2); i++) {
                for (int j = figure.get(1); j <= figure.get(1) + 1; j++) {
                    paint[i][j] = 'a';
                }
            }
            for (int i = figure.get(0); i <= figure.get(2); i++) {
                for (int j = figure.get(1) + 1; j <= figure.get(3); j++) {
                    paint[i][j] = 'b';
                }
            }
        }
        return printPaint(paint);
    }

    private static String printPaint(char[][] paint) {
        StringBuilder builder = new StringBuilder();
        builder.append("YES").append("\n");
        for (int i = 1; i < paint.length - 1; i++) {
            for (int j = 1; j < paint.length - 1; j++) {
                builder.append(paint[i][j]);
            }
            builder.append("\n");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }
}