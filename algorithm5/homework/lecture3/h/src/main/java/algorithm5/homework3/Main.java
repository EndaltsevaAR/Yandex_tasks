package algorithm5.homework3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static final double EPSILON = 1e-9;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int nNumberPoints = Integer.parseInt(reader.readLine());
            Set<Lighter> lightersBefore = new HashSet<>(nNumberPoints);
            Set<Lighter> lightersAfter = new HashSet<>(nNumberPoints);

            Extremum extremumBefore = new Extremum();
            for (int i = 0; i < nNumberPoints; i++) {
                lightersBefore.add(getLighter(reader.readLine(), extremumBefore));
            }
            Extremum extremumAfter = new Extremum();
            for (int i = 0; i < nNumberPoints; i++) {
                lightersAfter.add(getLighter(reader.readLine(), extremumAfter));
            }
            System.out.println(getNumberMoves(lightersBefore, lightersAfter, extremumBefore, extremumAfter));
        }
    }

    public static Lighter getLighter(String line, Extremum extremum) {
        StringTokenizer st = new StringTokenizer(line);
        double xStart = Double.parseDouble(st.nextToken());
        double yStart = Double.parseDouble(st.nextToken());
        double xEnd = Double.parseDouble(st.nextToken());
        double yEnd = Double.parseDouble(st.nextToken());
        Lighter lighter = new Lighter(xStart, yStart, xEnd, yEnd);

        extremum.xMin = Math.min(lighter.xStart, extremum.xMin);
        extremum.xMax = Math.max(lighter.xEnd, extremum.xMax);

        extremum.yMin = Math.min(lighter.yStart, extremum.yMin);
        extremum.yMax = Math.max(lighter.yStart, extremum.yMax);
        extremum.yMin = Math.min(lighter.yEnd, extremum.yMin);
        extremum.yMax = Math.max(lighter.yEnd, extremum.yMax);
        return lighter;
    }

    public static int getNumberMoves(Set<Lighter> lightersBefore, Set<Lighter> lightersAfter, Extremum extremumBefore,
            Extremum extremumAfter) {

        return 0;
    }

}

class Lighter {
    double xStart;
    double yStart;
    double xEnd;
    double yEnd;

    public Lighter(double xStart, double yStart, double xEnd, double yEnd) {
        if (xStart > xEnd || (xStart == xEnd && yEnd > yStart)) {
            this.xStart = xEnd;
            this.yStart = yEnd;
            this.xEnd = xStart;
            this.yEnd = yStart;
        } else {
            this.xStart = xStart;
            this.yStart = yStart;
            this.xEnd = xEnd;
            this.yEnd = yEnd;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Lighter lighter = (Lighter) o;
        return Double.compare(lighter.xStart, xStart) == 0 &&
                Double.compare(lighter.yStart, yStart) == 0 &&
                Double.compare(lighter.xEnd, xEnd) == 0 &&
                Double.compare(lighter.yEnd, yEnd) == 0;
    }

    @Override
    public int hashCode() {
        long bits = Double.doubleToLongBits(xStart);
        bits = bits * 31 + Double.doubleToLongBits(yStart);
        bits = bits * 31 + Double.doubleToLongBits(xEnd);
        bits = bits * 31 + Double.doubleToLongBits(yEnd);
        return (int) (bits ^ (bits >>> 32));
    }
}

class Extremum {
    double xMin;
    double yMin;
    double xMax;
    double yMax;

    public Extremum() {
        xMin = Double.MAX_VALUE;
        yMin = Double.MAX_VALUE;
        xMax = Double.MIN_VALUE;
        yMax = Double.MIN_VALUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Extremum extremum = (Extremum) o;
        return Double.compare(extremum.xMin, xMin) == 0 &&
                Double.compare(extremum.yMin, yMin) == 0 &&
                Double.compare(extremum.xMax, xMax) == 0 &&
                Double.compare(extremum.yMax, yMax) == 0;
    }

    @Override
    public int hashCode() {
        int result = 31;
        long temp;
        temp = Double.doubleToLongBits(xMin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yMin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(xMax);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yMax);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}