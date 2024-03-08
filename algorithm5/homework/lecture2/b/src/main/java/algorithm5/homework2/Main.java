package algorithm5.homework2;

/**
Вася решил заняться торговлей рыбой. С помощью методов машинного обучения он предсказал цены на рыбу на N дней вперёд. 
Он решил, что в один день он купит рыбу, а в один из следующих дней — продаст (то есть совершит или ровно одну покупку 
и продажу или вообще не совершит покупок и продаж, если это не принесёт ему прибыли). К сожалению, рыба — товар скоропортящийся 
и разница между номером дня продажи и номером дня покупки не должна превышать K.

Определите, какую максимальную прибыль получит Вася.

Формат ввода
В первой строке входных данных задаются числа N и K (1 ≤ N ≤ 10000, 1 ≤ K ≤ 100).

Во второй строке задаются цены на рыбу в каждый из N дней. Цена — целое число, которое может находится в пределах от 1 до 109.

Формат вывода
Выведите одно число — максимальную прибыль, которую получит Вася.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String commonInfo = scanner.nextLine();
        String prices = scanner.nextLine();
        System.out.println(getMaxProfit(commonInfo, prices));
        scanner.close();
    }

    public static String getMaxProfit(String commonInfo, String stringPrices) {
        int nNumberDay = Integer.parseInt(commonInfo.split(" ")[0]);
        int kExpirationDate = Integer.parseInt(commonInfo.split(" ")[1]);
        String[] parsedPrices = stringPrices.split(" ");
        int[] prices = new int[nNumberDay];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = Integer.parseInt(parsedPrices[i]);
        }

        int maxProfit = 0;
        for (int i = 0; i < nNumberDay; i++) {
            for (int j = 1; j <= kExpirationDate && (i + j) < nNumberDay; j++) {
                int profit = prices[i + j] - prices[i];
                if (profit > 0 && profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return Integer.toString(maxProfit);
    }
}