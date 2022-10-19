package main.lecture.secondLinearSearch;

/*
Description:
Дана последовательность чисел длинной N.
Найти минимальное четное число в последовательности или вывести -1, если такое
число не существует
 */
import java.util.Scanner;

public class MinEven {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        String[] seq = line.split(" ");
        System.out.println(minEven(seq));
    }

    public static int minEven(String[] seq) {
        int answer = -1;
        boolean flag = false;
        for (String s : seq) {
            if (Integer.parseInt(s) % 2 == 0 &&
                    (!flag || Integer.parseInt(s) < answer)) {
                answer = Integer.parseInt(s);
                flag = true;
            }
        }
        return answer;
    }
}
