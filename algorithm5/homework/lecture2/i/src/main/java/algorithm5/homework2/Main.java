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
        System.out.println(getMinMoves(ships));
        scanner.close();
    }

    public static int getMinMoves(String[] ships) {
        int countMoves = 0;
        int[][] fields = initField(ships);

        if (isAllColumnHasAllElement(fields)) {
            return 0;
        }

        // Чтобы выйти к медиане, нам надо выйти к состоянию, когда в каждой строчке
        // было по 1 кораблю
        countMoves += movingShips(fields);

        return countMoves;
    }

    private static int[][] initField(String[] ships) {
        int[][] fields = new int[ships.length + 2][ships.length + 2];
        // границы в -1
        for (int i = 0; i < fields.length; i++) {
            fields[0][i] = -1;
            fields[fields.length - 1][i] = -1;
            fields[i][0] = -1;
            fields[i][fields.length - 1] = -1;
        }

        for (String ship : ships) {
            String[] coordinates = ship.split(" ");
            fields[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])] = 1;
        }
        return fields;
    }

    private static boolean isAllColumnHasAllElement(int[][] fields) {
        boolean isAllColumnHasAllElement = false;
        for (int i = 1; i < fields[0].length - 1; i++) {
            if (getColumnSum(i, fields) == fields.length) {
                return true;
            }
        }
        return isAllColumnHasAllElement;
    }

    private static int getColumnSum(int columnNumber, int[][] fields) {
        int sum = 0;
        for (int i = 1; i < fields.length - 1; i++) {
            sum += fields[i][columnNumber];
        }
        return sum;
    }

    private static int movingShips(int[][] fields) {
        // сначала создаем ситуацию, когда в одной строке по 1 кораблю
        int countSteps = 0;
        while (!isAllLineHasOneElement(fields)) {
            int emptyLinesNumber = 0;
            for (int i = 1; i < fields.length - 1; i++) {
                int sumLine = Arrays.stream(fields[i]).sum() + 2;
                if (sumLine == 1) {
                    continue;
                } else if (sumLine == 0) {
                    emptyLinesNumber++;
                } else {
                    int numberShipsToMove = sumLine - 1;
                    for (int j = 0; j < fields[i].length && numberShipsToMove > 0; j++) {
                        if (emptyLinesNumber != 0) { // try up
                            if (fields[i - 1][j] != 1 && fields[i - 1][j] != -1) {
                                fields[i - 1][j] = 1;
                                fields[i][j] = 0;
                                numberShipsToMove--;
                                emptyLinesNumber--;
                                countSteps++;
                            }
                        } else { // try down
                            if (fields[i + 1][j] != 1 && fields[i + 1][j] != -1) {
                                fields[i + 1][j] = 1;
                                fields[i][j] = 0;
                                numberShipsToMove--;
                                countSteps++;
                            }
                        }
                    }
                }
            }
        }

        // сам расчет из медианы
        int[] cols = new int[fields.length - 2];
        for (int y = 1; y < fields.length - 1; y++) {
            boolean isLine = false;
            for (int x = 1; x < fields[0].length - 1 && !isLine; x++) {
                if (fields[y][x] == 1) {
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
            countSteps += Math.abs(col - median);
        }

        return countSteps;
    }

    private static boolean isAllLineHasOneElement(int[][] fields) {
        boolean isAllLineHasOneElement = true;
        for (int i = 1; i < fields.length - 1; i++) {
            if (Arrays.stream(fields[i]).sum() != -1) {
                return false;
            }
        }
        return isAllLineHasOneElement;
    }

}