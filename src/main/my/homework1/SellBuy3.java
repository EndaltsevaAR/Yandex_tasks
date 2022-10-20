package main.my.homework1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SellBuy3 {
    public static final int MONEY = 1000;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int numberDay = Integer.parseInt(line);
        String lineDays = scanner.nextLine();
        List<Integer> days = new ArrayList<>();
        String[] temp = lineDays.split(" ");
        for (String s :temp) {
            days.add(Integer.parseInt(s));
        }
        System.out.println(getDaysForBuyAndSell(numberDay, days));
        scanner.close();
    }

    public static String getDaysForBuyAndSell(int numberDay, List<Integer> days) {
        if (days.size() == 1) {
            return "0 0";
        }
    int moneyBuyDay = (MONEY / days.get(0)) * days.get(0);
    int buyDay = 1;
    int moneySellDay = (MONEY / days.get(0) * days.get(1));
    int sellDay = 2;
    int profit = moneySellDay - moneyBuyDay;
        for (int i = 0; i < days.size(); i++) {
            for (int j = i + 1; j < days.size(); j++) {
                if (days.get(i) <= MONEY) {
                    int amount = MONEY / days.get(i);
                    if (days.get(j) * amount - days.get(i) * amount > profit) {
                        buyDay = i + 1;
                        sellDay = j + 1;
                        profit = days.get(j) * amount - days.get(i) * amount;
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
