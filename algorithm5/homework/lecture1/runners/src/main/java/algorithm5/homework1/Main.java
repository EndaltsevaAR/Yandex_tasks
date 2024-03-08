package algorithm5.homework1;

import java.util.Scanner;

public class Main {
    public static final double EPSILON = 0.0000000001;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double L = scanner.nextDouble();
        double x1 = scanner.nextDouble();
        double v1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double v2 = scanner.nextDouble();
        System.out.println(isBoyMeetings(L, x1, v1, x2, v2));
        scanner.close();
    }

    public static String isBoyMeetings(double L, double x1, double v1, double x2, double v2) {
        double dx = Math.abs(x1 - x2);

        if (v1 < EPSILON && v2 < EPSILON) { // они стоят на месте
            if (dx < EPSILON || // в одной точке
                    (Math.abs(L - Math.max(x1, x2)) - Math.abs(0 + Math.min(x1, x2)) < EPSILON)) { // симметрично
                return "YES\n0.0000000000";
            } else {
                return "NO";
            }
        } else if (Math.abs(v1 - v2) < EPSILON) { // они двигаются с одинаковой скоростью
            double time2 = Math.abs((x2 - x1) / (v1 + v2));
            return "YES\n" + String.format("%.10f", time2);
        } else {
            if ((x1 < EPSILON || x2 < EPSILON) && (!(x1 < EPSILON && x2 < EPSILON))) { // один стоят на старте
                double time1 = Math.abs((L - Math.max(x1, x2)) / Math.max(v1, v2));
                return "YES\n" + String.format("%.10f", time1);
            } else {
                double time1 = Math.abs((x2 - x1) / (v1 - v2));
                return "YES\n" + String.format("%.10f", time1);
            }

        }
    }
}