package algorithm5.homework1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberLines = Integer.parseInt(scanner.nextLine());
        int[] linesLenght = new int[numberLines];
        for (int i = 0; i < linesLenght.length; i++) {
            linesLenght[i] = Integer.parseInt(scanner.nextLine());
        }
        int count = getTouchCount(linesLenght);
        System.out.println(count);
        scanner.close();
    }

    public static int getTouchCount(int[] linesLenght) {
        return linesLenght.length;
    }
}