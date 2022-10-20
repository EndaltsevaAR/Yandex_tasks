package main.my.homework1;

import java.util.Scanner;

public class SellBuy3 {
    public static final int MONEY = 1000;
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
        System.out.println(getDaysForBuyAndSell(days));
        scanner.close();
    }

    public static String getDaysForBuyAndSell(int[] days) {
        if (days.length == 1) {
            return "0 0";
        }
    int moneyBuyDay = (MONEY / days[0]) * days[0];
    int buyDay = 1;
    int moneySellDay = (MONEY / days[0] * days[1]);
    int sellDay = 2;
    int profit = moneySellDay - moneyBuyDay;
        for (int i = 0; i < days.length; i++) {
            for (int j = i + 1; j < days.length; j++) {
                if (days[i] <= MONEY) {
                    int amount = MONEY / days[i];
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
