package main.my;

import java.util.Scanner;

public class probniy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        long answer = x + y;
        System.out.println(answer);
        scanner.close();
    }
}
