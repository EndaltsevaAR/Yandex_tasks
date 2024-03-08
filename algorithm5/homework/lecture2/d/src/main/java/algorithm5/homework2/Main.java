package algorithm5.homework2;

/**
На клетчатой плоскости закрашено K клеток. Требуется найти минимальный по площади прямоугольник, 
со сторонами, параллельными линиям сетки, покрывающий все закрашенные клетки.

Формат ввода
Во входном файле, на первой строке, находится число K (1 ≤ K ≤ 100). На следующих 
K строках находятся пары чисел Xi и Yi — координаты закрашенных клеток (|Xi|, |Yi| ≤ 109).

Формат вывода
Выведите в выходной файл координаты левого нижнего и правого верхнего углов прямоугольника.
 */

import java.util.Scanner;

public class Main {
    private static final int FIELD_LENGTH = 8;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberCells = Integer.parseInt(scanner.nextLine());
        String[] cells = new String[numberCells];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = scanner.nextLine();
        }
        System.out.println(getPerimetr(cells));
        scanner.close();
    }

    public static int getPerimetr(String[] cells) {
        boolean[][] chessBoard = new boolean[FIELD_LENGTH][FIELD_LENGTH];
        for (int i = 0; i < cells.length; i++) {
            int y = Integer.parseInt(cells[i].split(" ")[0]) - 1;
            int x = Integer.parseInt(cells[i].split(" ")[1]) - 1;
            chessBoard[y][x] = true;
        }

        int perimetr = 0;
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                if (chessBoard[i][j]) {
                    perimetr += 4 - numberOfNeighbours(i, j, chessBoard);
                }
            }
        }
        return perimetr;
    }

    private static int numberOfNeighbours(int i, int j, boolean[][] chessBoard) {
        int count = 0;
        int[] xs = { -1, 0, 1, 0 };
        int[] ys = { 0, 1, 0, -1 };
        boolean[] neighbours = new boolean[4];
        for (int k = 0; k < neighbours.length; k++) {
            if (i + ys[k] < FIELD_LENGTH && j + xs[k] < FIELD_LENGTH && i + ys[k] >= 0 && j + xs[k] >= 0) {
                neighbours[k] = chessBoard[i + ys[k]][j + xs[k]];
                if (neighbours[k]) {
                    count++;
                }
            }
        }
        return count;
    }
}