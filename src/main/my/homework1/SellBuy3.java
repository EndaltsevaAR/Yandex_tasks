package main.my.homework1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SellBuy3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int numberDay = Integer.parseInt(line);
        String lineDays = scanner.nextLine();
        List<Integer> days = Arrays.stream(lineDays.split(" ")).
                map(s -> Integer.parseInt(s.trim())).toList();
        System.out.println(getDaysForBuyAndSell(numberDay, days));
        scanner.close();
    }

    public static String getDaysForBuyAndSell(int numberDay, List<Integer> days) {
        int buyDay = 0;
        int sellDay = 1;
        int profit = days.get(sellDay) - days.get(buyDay);
        for (int i = 0; i < days.size(); i++) {
            for (int j = i; j < days.size(); j++) {
                if (days.get(j) - days.get(i) > profit) {
                    buyDay = i;
                    sellDay = j;
                    profit = days.get(j) - days.get(i);
                }
            }
        }
        return  (buyDay + 1) +  " " + (sellDay + 1);
    }


}
