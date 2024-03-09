package algorithm5.homework2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberCells = Integer.parseInt(scanner.nextLine());
        String cells = scanner.nextLine();
        String speeds = scanner.nextLine();
        System.out.println(getMaxProfit(numberCells, cells, speeds));
        scanner.close();
    }

    public static long getMaxProfit(int numberCells, String sCells, String speeds) {
        String[] splitedSCells = sCells.split(" ");
        int[] cells = new int[numberCells];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = Integer.parseInt(splitedSCells[i]);
        }
        long aMinSpeed = Long.parseLong(speeds.split(" ")[0]);
        long bMaxSpeed = Long.parseLong(speeds.split(" ")[1]);
        long kDeltaSpeed = Long.parseLong(speeds.split(" ")[2]);

        long maxProfit = 0L;

        long minMoves = (aMinSpeed / kDeltaSpeed) % numberCells; // убрать в отдельный метод
        if (aMinSpeed % kDeltaSpeed == 0 && minMoves > 0) {
            minMoves--;
        }

        for (long speed = aMinSpeed; speed <= bMaxSpeed; speed++) {
            long moves = (speed / kDeltaSpeed) % numberCells;
            if (speed % kDeltaSpeed == 0 && moves > 0) {
                moves--;
            }
            for (long i = minMoves; i <= moves; i++) {
                long clockwise = cells[(int) i];
                if (clockwise > maxProfit) {
                    maxProfit = clockwise;
                }

                long counterclockwise;
                if (i == 0) {
                    counterclockwise = cells[0];
                } else {
                    counterclockwise = cells[numberCells - (int) i];
                }
                if (counterclockwise > maxProfit) {
                    maxProfit = counterclockwise;
                }
            }
        }
        return maxProfit;
    }
}