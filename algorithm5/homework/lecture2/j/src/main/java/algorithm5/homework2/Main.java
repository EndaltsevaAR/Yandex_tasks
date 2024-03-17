package algorithm5.homework2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
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
        if (rows == 1 && cols == 1 || numberCells(paint) < 2) {
            System.out.println("NO");
            return;
        }
        System.out.println(decodePaint(paint));
    }

    public static char[][] initArray(int rows, int cols, Scanner scanner) {
        char[][] paint = new char[rows + 2][cols + 2];

        // границы в -1 строки
        for (int i = 0; i < paint[0].length; i++) {
            paint[0][i] = '.';
            paint[paint.length - 1][i] = '.';
        }
        // границы в -1 столбцы
        for (int i = 0; i < paint.length; i++) {
            paint[i][0] = '.';
            paint[i][paint[0].length - 1] = '.';
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
        List<List<Integer>> firstRectangle = findPossibleFirstRectangle(paint);
        return findSecondRectangleAndFullAllField(firstRectangle, paint);
    }

    private static List<List<Integer>> findPossibleFirstRectangle(char[][] paint) {
        for (int i = 1; i < paint.length - 1; i++) {
            for (int j = 1; j < paint[i].length - 1; j++) {
                if (paint[i][j] == '#') {
                    return findAllPossibleFigures(i, j, paint);
                }
            }
        }
        return Collections.emptyList();
    }

    private static List<List<Integer>> findAllPossibleFigures(int y, int x, char[][] paint) {
        List<List<Integer>> figures = new ArrayList<>();
        int yMin = y;
        int xMin = x;
        int xTotalMax = paint[0].length - 1;
        for (int i = y; i < paint.length - 1 && xTotalMax > xMin; i++) {
            boolean isLineEnd = false;
            for (int j = x; j < paint[0].length - 1 && y < xTotalMax && !isLineEnd; j++) {
                if (paint[i][j] == '#') {
                    List<Integer> figure = new ArrayList<>();
                    figure.add(yMin); // ymin
                    figure.add(xMin); // xmin
                    figure.add(i); // ymax
                    figure.add(j); // xmax
                    figures.add(figure);
                } else {
                    isLineEnd = true;
                    xTotalMax = j - 1;
                }
            }
        }

        return figures;
    }

    private static String findSecondRectangleAndFullAllField(List<List<Integer>> firstRectangle, char[][] paint) {
        String answer = "NO";

        for (List<Integer> firstFigure : firstRectangle) {
            char[][] testField = createLettedField(paint, firstFigure, 'a');
            List<List<Integer>> secondFigures = findPossibleFirstRectangle(testField);
            for (List<Integer> secondFigure : secondFigures) {
                char[][] finalField = createLettedField(testField, secondFigure, 'b');
                if (numberCells(finalField) == 0) {
                    return printYes(finalField);
                }
            }
        }

        return answer;
    }

    private static char[][] createLettedField(char[][] paint, List<Integer> figure, char letter) {
        char[][] testField = new char[paint.length][];
        for (int i = 0; i < paint.length; i++) {
            testField[i] = paint[i].clone();
        }
        for (int i = figure.get(0); i <= figure.get(2); i++) {
            for (int j = figure.get(1); j <= figure.get(3); j++) {
                testField[i][j] = letter;
            }
        }
        return testField;
    }

    private static String printYes(char[][] paint) {
        StringBuilder builder = new StringBuilder();
        builder.append("YES").append("\n");
        for (int i = 1; i < paint.length - 1; i++) {
            for (int j = 1; j < paint[0].length - 1; j++) {
                builder.append(paint[i][j]);
            }
            builder.append("\n");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private static int numberCells(char[][] paint) {
        int count = 0;
        for (int i = 1; i < paint.length - 1; i++) {
            for (int j = 1; j < paint[0].length - 1; j++) {
                if (paint[i][j] == '#') {
                    count++;
                }
            }
        }
        return count;
    }
}