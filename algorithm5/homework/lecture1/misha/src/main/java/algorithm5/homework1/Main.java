package algorithm5.homework1;
//

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int numberLines = Integer.parseInt(scanner.nextLine());
        String stringDigits = scanner.nextLine();
        System.out.println(getOperators(numberLines, stringDigits));
        scanner.close();

    }

    public static String getOperators(int numberLines, String stringDigits) {

        StringBuilder builder = new StringBuilder();
        long[] digits = Arrays.stream(stringDigits.split(" ")).mapToLong(Long::parseLong).toArray();

        long sum = digits[0];
        long underSum = 0;
        boolean isMultProcess = false;
        for (int i = 1; i < digits.length; i++) { // смотрим по сумме слева и числу справа
            if (digits[i] == 0) {
                if (sum == 0) {
                    builder.append('x');
                } else {
                    if (underSum != 0) {
                        if (Math.abs(sum - underSum) != 0 && Math.abs(sum - underSum) % 2 == 1) {
                            builder.append('x');
                            sum -= underSum;
                            isMultProcess = false;
                            underSum = 0;
                        } else {
                            builder.append('+');
                        }
                    }
                }
            } else if (isOdd(digits[i])) { // если справа нечетное
                if (isOdd(sum)) { // сумма нечетная
                    builder.append('x');

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
                } else { // сумма четная
                    builder.append('+');
                    if (isMultProcess) {
                        isMultProcess = false;
                        underSum = 0;
                    }
                    sum += digits[i];
                }
            } else { // // если справа четное
                builder.append('+');
                if (isMultProcess) {
                    isMultProcess = false;
                    underSum = 0;
                }
                sum += digits[i];
            }
        }
        return builder.toString();
    }

    public static boolean getOperatorsForTest(int numberLines, String stringDigits) {
        StringBuilder builder = new StringBuilder();
        long[] digits = Arrays.stream(stringDigits.split(" ")).mapToLong(Long::parseLong).toArray();

        long sum = digits[0];
        long underSum = 0;
        boolean isMultProcess = false;
        for (int i = 1; i < digits.length; i++) { // смотрим по сумме слева и числу справа
            if (digits[i] == 0) {
                if (sum == 0) {
                    builder.append('x');
                } else {
                    if (underSum != 0) {
                        if (Math.abs(sum - underSum) != 0 && Math.abs(sum - underSum) % 2 == 1) {
                            builder.append('x');
                            sum -= underSum;
                            isMultProcess = false;
                            underSum = 0;
                        } else {
                            builder.append('+');
                        }
                    }
                }
            } else if (isOdd(digits[i])) { // если справа нечетное
                if (isOdd(sum)) { // сумма нечетная
                    builder.append('x');

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
                } else { // сумма четная
                    builder.append('+');
                    if (isMultProcess) {
                        isMultProcess = false;
                        underSum = 0;
                    }
                    sum += digits[i];
                }
            } else { // // если справа четное
                builder.append('+');
                if (isMultProcess) {
                    isMultProcess = false;
                    underSum = 0;
                }
                sum += digits[i];
            }
        }
        System.out.println("sum is " + sum);
        return Math.abs(sum) % 2 == 1;
    }

    private static boolean isOdd(long number) {
        return (Math.abs(number) % 2 == 1);
    }
}