package algorithm5.honework1;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String enterData = scanner.nextLine();
        String[] values = enterData.split(" ");
        int nStartProfit = Integer.parseInt(values[0]);
        int kNumberOwners = Integer.parseInt(values[1]);
        int dNimerDays = Integer.parseInt(values[2]);

        System.out.println(getFinalProfit(nStartProfit, kNumberOwners, dNimerDays));
        scanner.close();
    }

    public static long getFinalProfit(int nStartProfit, int kNumberOwners, int dNimerDays) {
        TreeSet<Long> startProfits = new TreeSet<>();
        TreeSet<Long> endProfits = new TreeSet<>();
        startProfits.add(Long.valueOf(nStartProfit));
        for (int i = 0; i < dNimerDays; i++) {
            for (Long profit : startProfits) {
                Long nextDayProfit = profit * 10;
                for (int lastNumber = 0; lastNumber < 10; lastNumber++) {
                    if (((nextDayProfit + lastNumber) % kNumberOwners) == 0) {
                        endProfits.add(nextDayProfit + lastNumber);
                    }
                }
            }
            if (endProfits.isEmpty()) {
                return -1L;
            }
            startProfits = endProfits;
            endProfits = new TreeSet<>();
        }
        return startProfits.first();
    }
}