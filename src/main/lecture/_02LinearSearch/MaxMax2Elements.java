package main.lecture._02LinearSearch;

/*
Description:
Дана последовательность чисел длинной N > 1.
Найти максимальное число в последовательности и второе по величине число
(такое, которое будет максимальным, если вычеркнуть из последовательности
1 максимальное число
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaxMax2Elements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        String[] seq = line.split(" ");
        for (Integer integer :maxMax(seq)) {
            System.out.println(integer);
        }
    }

    private static List<Integer> maxMax(String[] seq) {
        int max1 = Math.max(Integer.parseInt(seq[0]), Integer.parseInt(seq[1]));
        int max2 = Math.min(Integer.parseInt(seq[0]), Integer.parseInt(seq[1]));
        for (int i = 2; i < seq.length; i++) {
            if (Integer.parseInt(seq[i]) > max1) {
                max2 = max1;
                max1 = Integer.parseInt(seq[i]);
            } else if (Integer.parseInt(seq[i]) > max2) {
                max2 = Integer.parseInt(seq[i]);
            }
        }
        List<Integer> maxs = new ArrayList<>();

        maxs.add(max1);
        maxs.add(max2);
        return maxs;
    }

}
