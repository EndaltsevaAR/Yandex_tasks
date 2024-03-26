package algorithm5.homework4;

/**
Георг Кантор доказал, что множество всех рациональных чисел счетно (т.е. все рациональные числа можно пронумеровать).

Чтобы упорядочить дроби необходимо их положить в таблицу, как показано на рисунке. В строку с номером i этой матрицы 
по порядку записаны дроби с числителем i, а в столбец с номером j дроби с знаменателем j.

Дальше необходимо выписать все дроби в том порядке, как показано на рисунке стрелками.

1/1 2/1 1/2 1/3 2/2 3/1 ...

Вам требуется по числу n найти числитель и знаменатель n-ой дроби.

Формат ввода
Во входном файле дано число n (1 ≤ n ≤ 10^18) — порядковый номер дроби в последовательности.

Формат вывода
В выходной файл требуется вывести через символ / два числа: числитель и знаменатель соответствующей дроби.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            BigInteger n = new BigInteger(reader.readLine());
            if (n.equals(BigInteger.ONE)) {
                System.out.println("1/1");
            } else {
                System.out.println(getNumeratorAndDenominator(n));
            }
        }
    }

    public static String getNumeratorAndDenominator(BigInteger n) {
        BigInteger findDiagonal = findNumerator(n);
        BigInteger diffSum = n.subtract(getSum(findDiagonal.subtract(BigInteger.ONE)));

        BigInteger numerator;
        BigInteger denominator;

        if (findDiagonal.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {
            numerator = diffSum;
            denominator = findDiagonal.add(BigInteger.ONE).subtract(diffSum);
        } else {
            numerator = findDiagonal.add(BigInteger.ONE).subtract(diffSum);
            denominator = diffSum;
        }
        return numerator + "/" + denominator;
    }

    private static BigInteger findNumerator(BigInteger n) {
        BigInteger left = BigInteger.ONE;
        BigInteger right = n;

        while (left.compareTo(right) < 0) {
            BigInteger med = left.add(right).add(BigInteger.ONE);
            med = med.divide(BigInteger.valueOf(2));
            if (getSum(med).compareTo(n) < 0) {
                left = med;
            } else {
                right = med.subtract(BigInteger.ONE);
            }
        }
        return left.add(BigInteger.ONE);
    }

    private static BigInteger getSum(BigInteger med) {
        BigInteger answer = med.pow(2).add(med);
        return answer.divide(BigInteger.valueOf(2));
    }

}