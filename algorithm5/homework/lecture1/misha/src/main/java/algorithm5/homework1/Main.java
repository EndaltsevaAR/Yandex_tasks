package algorithm5.homework1;

/**
Миша сидел на занятиях математики в Высшей школе экономики и решал следующую задачу: дано n целых чисел и нужно расставить между ними знаки 
+ и x так, чтобы результат полученного арифметического выражения был нечётным (например, между числами 5, 7, 2, можно расставить 
арифметические знаки следующим образом: 5 x 7 + 2 = 37). 
Так как примеры становились все больше и больше, а Миша срочно убегает в гости, от вас требуется написать программу решающую данную задачу.

Формат ввода
В первой строке содержится единственное число n (2≤n≤10^5). Во второй строке содержится n целых чисел ai, разделённых пробелами (−10^9≤ai≤10^9). 
Гарантируется, что решение существует.

Формат вывода
В одной строке выведите n−1 символ + или x, в результате применения которых получается нечётный результат. 
(Для вывода используйте соответственно знаки «+» (ASCII код—43) и «x» (ASCII код—120), без кавычек).
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final char SUM_SIGN = '+';
    public static final char MULT_SIGN = 'x';

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int numberLines = Integer.parseInt(scanner.nextLine());
        String stringDigits = scanner.nextLine();
        System.out.println(getOperators(stringDigits));
        scanner.close();
    }

    public static String getOperators(String stringDigits) {
        StringBuilder builder = new StringBuilder();
        long[] digits = Arrays.stream(stringDigits.split(" ")).mapToLong(Long::parseLong).toArray();

        long sum = digits[0];
        long underSum = 0;
        boolean isMultProcess = false;
        for (int i = 1; i < digits.length; i++) { // we analyse summ at left and right number
            if (digits[i] == 0) {
                if (sum == 0) {
                    builder.append(MULT_SIGN);
                } else {
                    if (underSum != 0) {
                        if (Math.abs(sum - underSum) != 0 && Math.abs(sum - underSum) % 2 == 1) {
                            builder.append(MULT_SIGN);
                            sum -= underSum;
                            isMultProcess = false;
                            underSum = 0;
                        } else {
                            builder.append(SUM_SIGN);
                        }
                    } else {
                        builder.append(SUM_SIGN);
                    }
                }
            } else if (isOdd(digits[i])) { // number at right is odd
                if (isOdd(sum)) { // sum is odd
                    builder.append(MULT_SIGN);

                    if (!isMultProcess) {
                        isMultProcess = true;
                        sum -= digits[i - 1];
                        underSum = digits[i - 1] * digits[i];
                        sum += underSum;
                    } else {
                        sum -= underSum;
                        underSum *= digits[i];
                        sum += underSum;
                    }
                } else { // sum is even
                    builder.append(SUM_SIGN);
                    if (isMultProcess) {
                        isMultProcess = false;
                        underSum = 0;
                    }
                    sum += digits[i];
                }
            } else { // right number is even
                builder.append(SUM_SIGN);
                if (isMultProcess) {
                    isMultProcess = false;
                    underSum = 0;
                }
                sum += digits[i];
            }
        }
        return builder.toString();
    }

    /**
     * We can not expect particular string becose answers are many, and for testing
     * we check sum is odd or not
     */
    public static boolean getOperatorsForTest(String stringDigits) {
        StringBuilder builder = new StringBuilder();
        long[] digits = Arrays.stream(stringDigits.split(" ")).mapToLong(Long::parseLong).toArray();

        long sum = digits[0];
        long underSum = 0;
        boolean isMultProcess = false;
        for (int i = 1; i < digits.length; i++) { // we analyse summ at left and right number
            if (digits[i] == 0) {
                if (sum == 0) {
                    builder.append(MULT_SIGN);
                } else {
                    if (underSum != 0) {
                        if (Math.abs(sum - underSum) != 0 && Math.abs(sum - underSum) % 2 == 1) {
                            builder.append(MULT_SIGN);
                            sum -= underSum;
                            isMultProcess = false;
                            underSum = 0;
                        } else {
                            builder.append(SUM_SIGN);
                        }
                    } else {
                        builder.append(SUM_SIGN);
                    }
                }
            } else if (isOdd(digits[i])) { // number at right is odd
                if (isOdd(sum)) { // sum is odd
                    builder.append(MULT_SIGN);

                    if (!isMultProcess) {
                        isMultProcess = true;
                        sum -= digits[i - 1];
                        underSum = digits[i - 1] * digits[i];
                        sum += underSum;
                    } else {
                        sum -= underSum;
                        underSum *= digits[i];
                        sum += underSum;
                    }
                } else { // sum is even
                    builder.append(SUM_SIGN);
                    if (isMultProcess) {
                        isMultProcess = false;
                        underSum = 0;
                    }
                    sum += digits[i];
                }
            } else { // right number is even
                builder.append(SUM_SIGN);
                if (isMultProcess) {
                    isMultProcess = false;
                    underSum = 0;
                }
                sum += digits[i];
            }
        }
        return Math.abs(sum) % 2 == 1;
    }

    private static boolean isOdd(long number) {
        return (Math.abs(number) % 2 == 1);
    }
}