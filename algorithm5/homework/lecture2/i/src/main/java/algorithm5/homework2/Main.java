package algorithm5.homework2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int numberShips = Integer.parseInt(scanner.nextLine());
        String[] ships = new String[numberShips];
        for (int i = 0; i < ships.length; i++) {
            ships[i] = scanner.nextLine();
        }
        System.out.println(getMinMoves(numberShips, ships));
        scanner.close();
    }

    public static int getMinMoves(int numberShips, String[] ships) {
        if (numberShips == 1) {
            return 0;
        }

        Integer countMoves = 0;
        int[][] field = initField(numberShips, ships);
        // movingShipsToOneAtALine(field, countMoves);
        movingShipsToVertical(field, countMoves);

        return countMoves;
    }

    private static int[][] initField(int numberShips, String[] ships) {
        int[][] field = new int[numberShips + 2][numberShips + 2];

        // boundaries
        for (int i = 0; i < field.length; i++) {
            field[0][i] = -1;
            field[field.length - 1][i] = -1;
            field[i][0] = -1;
            field[i][field.length - 1] = -1;
        }

        for (String ship : ships) {
            String[] coordinates = ship.split(" ");
            field[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])] = 1;
        }

        return field;
    }

    private static void movingShipsToVertical(int[][] field, Integer countMoves) {
        int[] cols = new int[field.length - 2];

        for (int y = 1; y < field.length - 1; y++) {
            boolean isLine = false;
            for (int x = 1; x < field[0].length - 1 && !isLine; x++) {
                if (field[y][x] == 1) {
                    cols[y - 1] = x - 1;
                    isLine = true;
                }
            }
        }
        int[] sortedCols = cols.clone();
        Arrays.sort(sortedCols);
        int median;
        if (sortedCols.length % 2 == 1) {
            median = sortedCols[sortedCols.length / 2];
        } else {
            median = (sortedCols[sortedCols.length / 2 - 1] + sortedCols[sortedCols.length / 2]) / 2;
        }

        for (int col : cols) {
            countMoves += Math.abs(col - median);
        }
    }
}