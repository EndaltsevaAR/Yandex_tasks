package main.my.homework1;
// There is 3 solution:
// 1 - getDaysForBuyAndSell - solution with doubles, can de not correct with certain costs,
// 2 - getDaysForBuyAndSellLong - my solution, correct, but long,
/* 3 - getSolution - total correct solution, but from system (translate from Python to Java)(for example for stress tests)

Description:
Купить и продать
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
У вас есть 1000$, которую вы планируете эффективно вложить. Вам даны цены за 1000 кубометров газа за n дней.
Можно один раз купить газ на все деньги в день i и продать его в один из последующих дней j, i < j.

Определите номера дней для покупки и продажи газа для получения максимальной прибыли.

Формат ввода
В первой строке вводится число дней n (1 ≤ n ≤ 100000).

Во второй строке вводится n чисел — цены за 1000 кубометров газа в каждый из дней. Цена — целое число от 1 до 5000.
Дни нумеруются с единицы.

Формат вывода
Выведите два числа i и j — номера дней для покупки и продажи газа. Если прибыль получить невозможно, выведите два нуля.
 */

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
        System.out.println("Long solution");
        System.out.println(getDaysForBuyAndSellLong(days));
        System.out.println("Solution with double");
        System.out.println(getDaysForBuyAndSell(days));
        System.out.println("Correct solution");
        System.out.println(getSolution(days));
        scanner.close();
    }

    public static String getSolution(int[] days) {
        int bestBuyDay = 0;
        int bestSellDay = 0;
        int minCostDay = 0;
        for (int i = 1; i < days.length; i++) {
            if (days[bestSellDay] * days[minCostDay] < days[bestBuyDay] * days[i]) {
                bestBuyDay = minCostDay;
                bestSellDay = i;
            }
            if (days[minCostDay] > days[i]) {
                minCostDay = i;
            }
        }
        if (bestSellDay == 0 && bestBuyDay == 0) {
            return "0 0";
        } else {
            return (bestBuyDay + 1) + " " + (bestSellDay + 1);
        }
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
