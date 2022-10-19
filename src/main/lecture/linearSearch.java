package main.lecture;

import java.util.*;

public class linearSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int x = scanner.nextInt();
        scanner.close();
        String[] seq = line.split(" ");
        System.out.println(search(seq, x));

    }

    public static int search(String[] seq, int x) {
        int answer = -1;
        for (int i = 0; i < seq.length; i++) {
            if (Integer.parseInt(seq[i]) == x) {
                return i;
            }
        }
        return answer;
    }
}
