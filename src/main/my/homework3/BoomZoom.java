package main.my.homework3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BoomZoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = Integer.parseInt(scanner.nextLine());
        String lineAges = scanner.nextLine();
        List<Integer> ages = Arrays.stream(lineAges.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(solution(len, ages));
    }

    public static int solution(int len, List<Integer> ages) {
        Collections.sort(ages);
        return 0;
    }
}
