package main.my.homework1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SellBuy3 {
    public static final double MONEY = 1.0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int numberDay = Integer.parseInt(line);
        String lineDays = scanner.nextLine();
        int[] days = new int[numberDay];
        String[] temp = lineDays.split(" ");
        for (int i = 0; i < numberDay; i++) {
            days[i] = Integer.parseInt(temp[i]);
        }
        // System.out.println(getDaysForBuyAndSellLong(days));
        System.out.println(getDaysForBuyAndSell(days));
        scanner.close();
    }

    public static String getDaysForBuyAndSell(int[] days) {
        int minIndex = 0;
        double maxGas = MONEY / days[0];
        double maxProfit = 0;
        List<Integer> answers = new ArrayList<>();
        answers.add(0);
        answers.add(0);
        for (int i = 1; i < days.length; i++) {
            if (maxGas * days[i] - MONEY > maxProfit) {
                maxProfit = maxGas * days[i] - MONEY;
                answers.set(0, minIndex + 1);
                answers.set(1, i + 1);
            }
            if (MONEY / days[i] > maxGas) {
                minIndex = i;
                maxGas = MONEY / days[i];
            }
        }
        return answers.get(0) + " " + answers.get(1);
    }

    public static String getDaysForBuyAndSellLong(int[] days) {
        if (days.length == 1) {
            return "0 0";
        }
    int moneyBuyDay = (1000 / days[0]) * days[0];
    int buyDay = 1;
    int moneySellDay = (1000 / days[0] * days[1]);
    int sellDay = 2;
    int profit = moneySellDay - moneyBuyDay;
        for (int i = 0; i < days.length; i++) {
            for (int j = i + 1; j < days.length; j++) {
                if (days[i] <= 1000) {
                    int amount = 1000 / days[i];
                    int tempProfit = days[j] * amount - days[i] * amount;
                    if (tempProfit > profit) {
                        buyDay = i + 1;
                        sellDay = j + 1;
                        profit = tempProfit;
                    }
                }
            }
        }
        if (profit == 0) {
            return "0 0";
        } else {
            return buyDay + " " + sellDay;
        }
    }
}
