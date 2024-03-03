package algorithm5.honework1;

/**
k друзей организовали стартап по производству укулеле для кошек. На сегодняшний день прибыль составила n рублей. 
Вы, как главный бухгалтер компании, хотите в каждый из ближайших d дней приписывать по одной цифре в конец числа, выражающего прибыль. 
При этом в каждый из дней прибыль должна делиться на k.

Формат ввода
В единственной строке входных данных через пробел записаны три числа: n, k, d — изначальная прибыль, 
количество учредителей компании и количество дней, которое вы собираетесь следить за прибылью (1≤n,k≤10^9, 1≤d≤10^5). 
НЕ гарантируется, что n делится на k.

Формат вывода
Выведите одно целое число x — прибыль компании через d дней. Первые цифры числа x должны совпадать с числом n. Все префиксы числа x, 
которые длиннее числа n на 1, 2, … , d цифр, должны делиться на k. Если возможных ответов несколько, выведите любой из них. 
Если ответа не существует, выведите −1.
 */

import java.math.BigInteger;
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

    public static BigInteger getFinalProfit(int nStartProfit, int kNumberOwners, int dNimerDays) {
        TreeSet<BigInteger> startProfits = new TreeSet<>();
        TreeSet<BigInteger> endProfits = new TreeSet<>();
        startProfits.add(BigInteger.valueOf(nStartProfit));
        for (int i = 0; i < dNimerDays; i++) {
            for (BigInteger profit : startProfits) {
                BigInteger nextDayProfit = profit.multiply(BigInteger.TEN);
                for (int lastNumber = 0; lastNumber < 10; lastNumber++) {
                    BigInteger possibleHoleNewProfit = nextDayProfit.add(BigInteger.valueOf(lastNumber));
                    if (possibleHoleNewProfit.mod(BigInteger.valueOf(kNumberOwners)).equals(BigInteger.ZERO)) {
                        if (lastNumber == 0 && dNimerDays > 1) {
                            return possibleHoleNewProfit
                                    .multiply(BigInteger.TEN.pow(dNimerDays - i - 1));

                        }
                        endProfits.add(possibleHoleNewProfit);
                    }
                }
            }
            if (endProfits.isEmpty()) {
                return BigInteger.valueOf(-1);
            }
            startProfits = endProfits;
            endProfits = new TreeSet<>();
        }
        return startProfits.first();
    }
}