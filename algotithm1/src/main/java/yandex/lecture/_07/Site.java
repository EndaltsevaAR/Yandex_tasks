package yandex.lecture._07;

import java.util.*;

public class Site {
    public static final int ENTER = -1;
    public static final int OUT = 1;

    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // int n = scanner.nextInt();
    // int[] enters = new int[n];
    // int[] outs = new int[n];
    // for (int i = 0; i < n; i++) {
    // enters[i] = scanner.nextInt();
    // }

    // for (int i = 0; i < n; i++) {
    // outs[i] = scanner.nextInt();
    // }
    // System.out.println(solution(n, enters, outs));
    // scanner.close();
    // }

    public static int solution(int n, int[] enters, int[] outs) {
        int online = 0;
        int max_users = 0;
        int[][] movement = new int[2 * n][];
        for (int i = 0; i < n; i++) {
            movement[2 * i] = new int[2];
            movement[2 * i][0] = enters[i];
            movement[2 * i][1] = ENTER;

            movement[2 * i + 1] = new int[2];
            movement[2 * i + 1][0] = outs[i];
            movement[2 * i + 1][1] = OUT;
        }
        Arrays.sort(movement, Comparator.comparing(arr -> arr[0]));
        for (int i = 0; i < n; i++) {
            if (movement[i][1] == ENTER) {
                online++;
            } else {
                online--;
            }
            max_users = Math.max(online, max_users);
        }
        return max_users;
    }
}
