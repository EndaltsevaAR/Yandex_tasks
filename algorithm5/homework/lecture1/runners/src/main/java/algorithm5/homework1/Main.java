package algorithm5.homework1;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long L = scanner.nextLong();
        long x1 = scanner.nextLong();
        long v1 = scanner.nextLong();
        long x2 = scanner.nextLong();
        long v2 = scanner.nextLong();
        System.out.println(isBoyMeetings(L, x1, v1, x2, v2));
        scanner.close();
    }

    public static String isBoyMeetings(long L, long x1, long v1, long x2, long v2) {
        long dx = x1 - x2;
        long dv = v2 - v1;

        if (dv == 0) {
            if (dx == 0) {
                return "YES\n0";
            } else {
                return "NO";
            }
        } else {
            if ((dx % dv == 0) && (dx / dv >= 0) && (dx / dv * v1 + x1 >= 0) && (dx / dv * v2 + x2 >= 0)
                    && (dx / dv * v1 + x1) % L == 0) {
                double time = (double) dx / dv;
                DecimalFormat df = new DecimalFormat("#.#########");
                return "YES\nd" + df.format(time);
            } else {
                return "NO";
            }
        }
    }
}