package algorithm5.homework2;

/**
Недавно один известный художник-абстракционист произвел на свет новый шедевр — картину «Два черных непересекающихся прямоугольника». 
Картина представляет собой прямоугольник m× n, разбитый на квадраты 1× 1, некоторые из которых закрашены любимым цветом автора — черным. 
Федя — не любитель абстрактных картин, однако ему стало интересно, действительно ли на картине изображены два непересекающихся прямоугольника. 
Помогите ему это узнать. Прямоугольники не пересекаются в том смысле, что они не имеют общих клеток.

Формат ввода
Первая строка входного файла содержит числа m и n (1 ≤ m, n ≤ 200). Следующие m строк содержат описание рисунка. Каждая строка содержит 
ровно n символов. Символ «.» обозначает пустой квадрат, а символ «#» — закрашенный.

Формат вывода
Если рисунок можно представить как два непересекающихся прямоугольника, выведите в первой строке «YES», а в следующих m строках выведите 
рисунок в том же виде, в каком он задан во входном файле, заменив квадраты, соответствующие первому прямоугольнику на символ «a», 
а второму — на символ «b». Если решений несколько, выведите любое.

Если же этого сделать нельзя, выведите в выходной файл «NO».
 */

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
        String[] lines = new String[rows];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = scanner.nextLine();
        }
        System.out.println(decodePaint(rows, cols, lines));
        scanner.close();
    }

    public static String decodePaint(int rows, int cols, String[] lines) {
        char[][] paint = initArray(rows, cols, lines);
        int total = getTotalColor(paint);

        // заглушка для того, чтобы функцию можно было бы использовать при поиске второй
        // фигуры
        List<Integer> stup = new ArrayList<>();
        for (int k = 0; k < 5; k++) {
            stup.add(-1);
        }

        List<List<Integer>> firstRectangle = findPossibleFirstRectangle(paint, stup, false, total);
        if (firstRectangle.isEmpty()) {
            return "NO";
        }
        return findSecondRectangleAndFullAllField(firstRectangle, paint, total);
    }

    private static int getTotalColor(char[][] paint) {
        int count = 0;
        for (int i = 1; i < paint.length - 1; i++) {
            for (int j = 1; j < paint[i].length - 1; j++) {
                if (paint[i][j] == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private static List<List<Integer>> findPossibleFirstRectangle(char[][] paint, List<Integer> figureReserved,
            boolean isSecond, int total) {
        for (int i = 1; i < paint.length - 1; i++) {
            for (int j = 1; j < paint[i].length - 1; j++) {
                if (paint[i][j] == '#') {
                    if (i >= figureReserved.get(0) && i <= figureReserved.get(2) && j >= figureReserved.get(1)
                            && j <= figureReserved.get(3)) {
                        continue;
                    } else {
                        return findAllPossibleFigures(i, j, paint, figureReserved, isSecond, total);
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private static List<List<Integer>> findAllPossibleFigures(int y, int x, char[][] paint,
            List<Integer> figureReserved, boolean isSecond, int total) {
        List<List<Integer>> figures = new ArrayList<>();
        int yMin = y;
        int xMin = x;
        int xTotalMax = paint[0].length - 1;
        for (int i = y; i < paint.length - 1 && xTotalMax > xMin; i++) {
            boolean isLineEnd = false;
            for (int j = x; j < paint[0].length - 1 && j < xTotalMax && !isLineEnd; j++) {
                if (paint[i][j] == '#'
                        && (!(i >= figureReserved.get(0) && i <= figureReserved.get(2) && j >= figureReserved.get(1)
                                && j <= figureReserved.get(3)))) {
                    if (!isSecond || (isSecond && ((i - yMin + 1) * (j - xMin + 1) + figureReserved.get(4) == total))) {

                        List<Integer> figure = new ArrayList<>();
                        figure.add(yMin); // ymin
                        figure.add(xMin); // xmin
                        figure.add(i); // ymax
                        figure.add(j); // xmax
                        figure.add((i - yMin + 1) * (j - xMin + 1)); // количество элементов
                        figures.add(figure);
                    }
                } else {
                    isLineEnd = true;
                    xTotalMax = j;
                }
            }
        }
        return figures;
    }

    private static String findSecondRectangleAndFullAllField(List<List<Integer>> firstFigures, char[][] paint,
            int total) {
        String answer = "NO";

        for (List<Integer> firstFigure : firstFigures) {
            List<List<Integer>> secondFigures = findPossibleFirstRectangle(paint, firstFigure, true, total);
            if (secondFigures.isEmpty()) {
                continue;
            }
            for (List<Integer> secondFigure : secondFigures) {
                return printYes(paint, firstFigure, secondFigure);
            }

        }
        return answer;
    }

    private static String printYes(char[][] paint, List<Integer> firstFigure, List<Integer> secondFigure) {
        StringBuilder builder = new StringBuilder();
        builder.append("YES").append("\n");
        for (int i = 1; i < paint.length - 1; i++) {
            for (int j = 1; j < paint[0].length - 1; j++) {
                if (i >= firstFigure.get(0) && i <= firstFigure.get(2) && j >= firstFigure.get(1)
                        && j <= firstFigure.get(3)) {
                    builder.append('a');
                } else if (i >= secondFigure.get(0) && i <= secondFigure.get(2) && j >= secondFigure.get(1)
                        && j <= secondFigure.get(3)) {
                    builder.append('b');
                } else {
                    builder.append(paint[i][j]);
                }
            }
            builder.append("\n");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    public static char[][] initArray(int rows, int cols, String[] lines) {
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
            String[] line = lines[i - 1].split("");
            for (int j = 0; j < line.length; j++) {
                paint[i][j + 1] = line[j].charAt(0);
            }
        }
        return paint;
    }

}