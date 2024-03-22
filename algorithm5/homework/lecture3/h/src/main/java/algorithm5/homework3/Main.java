package algorithm5.homework3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static final double EPSILON = 1e-9;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int nNumberPoints = Integer.parseInt(reader.readLine());
            List<Lighter> lightersBefore = new ArrayList<>(nNumberPoints);
            List<Lighter> lightersAfter = new ArrayList<>(nNumberPoints);
            for (int i = 0; i < nNumberPoints; i++) {
                lightersBefore.add(getLighter(reader.readLine()));
            }
            for (int i = 0; i < nNumberPoints; i++) {
                lightersAfter.add(getLighter(reader.readLine()));
            }
            System.out.println(getNumberMoves(lightersBefore, lightersAfter));
        }
    }

    private static int getNumberMoves(List<Lighter> lightersBefore, List<Lighter> lightersAfter) {
        int maxCounterMatches = 0;
        for (int i = 0; i < lightersBefore.size(); i++) {
            for (int j = 0; j < lightersAfter.size(); j++) {
                int iStop = i;
                int jStop = j;

                if (isMatch(lightersBefore.get(i), lightersAfter.get(j))) {
                    int currentCounterMatches = 0;
                    while (j < lightersAfter.size()) {
                        if (isMatch(lightersBefore.get(i), lightersAfter.get(j))) {
                            currentCounterMatches++;
                        }
                        j++;
                        i++;
                    }
                    if (currentCounterMatches == lightersAfter.size()) {
                        return 0;
                    }
                    if (currentCounterMatches > maxCounterMatches) {
                        maxCounterMatches = currentCounterMatches;
                    }
                }

                i = iStop;
                j = ++jStop;
            }
        }
        return lightersAfter.size() - maxCounterMatches;
    }

    private static boolean isMatch(Lighter lighterBefore, Lighter lighterAfter) {
        double diffX = lighterBefore.xStart - lighterAfter.xStart;
        double diffY = lighterBefore.yStart - lighterAfter.yStart;
        return (lighterBefore.xEnd - lighterAfter.xEnd - diffX < EPSILON
                || lighterBefore.yEnd - lighterAfter.yEnd - diffY < EPSILON);
    }

    private static Lighter getLighter(String line) {
        StringTokenizer st = new StringTokenizer(line);
        double xStart = Double.parseDouble(st.nextToken());
        double yStart = Double.parseDouble(st.nextToken());
        double xEnd = Double.parseDouble(st.nextToken());
        double yEnd = Double.parseDouble(st.nextToken());
        return new Lighter(xStart, yStart, xEnd, yEnd);
    }
}

class Lighter {
    double xStart;
    double yStart;
    double xEnd;
    double yEnd;

    public Lighter(double xStart, double yStart, double xEnd, double yEnd) {
        if (xStart > xEnd || (xStart == xEnd && yStart > yEnd)) {
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