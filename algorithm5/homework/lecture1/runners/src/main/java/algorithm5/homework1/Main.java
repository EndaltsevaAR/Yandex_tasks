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

        if (Math.abs(v1) < EPSILON && Math.abs(v2) < EPSILON) { // они стоят на месте
            if (dx < EPSILON || // в одной точке
                    (Math.abs(L - Math.max(x1, x2)) - Math.abs(0 + Math.min(x1, x2)) < EPSILON)) { // симметрично
                return "YES\n0.0000000000";
            } else {
                return "NO";
            }
        } else if (Math.abs(v1 - v2) < EPSILON) { // они двигаются с одинаковой скоростью
            double xMax = Math.max(x1, x2);
            double xMin = Math.min(x1, x2);
            double xSum = x1 + x2;
            if (v1 > 0) { // вправо
                double tMax = 0;
                if (Math.abs((L - xMax)) <= xSum / 2) {
                    tMax = Math.abs((L + (xSum / 2) - xMax) / v1);
                } else {
                    tMax = Math.abs((L - (xSum / 2) - xMax) / v1);
                }
                while (Math.abs((xMin + v1 * tMax) % L) - xSum / 2 > EPSILON) {
                    tMax += xSum / v1;
                }
                return "YES\n" + String.format("%.10f", tMax);
            } else { // влево
                double tMin = 0;
                if (Math.abs((xMin)) <= dx / 2) {
                    tMin = Math.abs(((dx / 2) + xMin) / v1);
                } else {
                    tMin = Math.abs((xMin - (dx / 2)) / v1);
                }
                while (Math.abs(Math.abs(xMin + v1 * tMin) % L - dx / 2) > EPSILON) {
                    tMin += Math.abs(dx / v1);
                }
                return "YES\n" + String.format("%.10f", tMin);
            }
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