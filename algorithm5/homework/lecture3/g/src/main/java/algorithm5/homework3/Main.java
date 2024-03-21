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
    public static final double BILLION = 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int nNumberPoints = Integer.parseInt(reader.readLine());
        Set<Point> points = new HashSet<>(nNumberPoints);
        for (int i = 0; i < nNumberPoints; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            points.add(new Point(x, y));
        }
        System.out.println(finishSquare(points));
        reader.close();
    }

    public static String finishSquare(Set<Point> points) {
        StringBuilder builder = new StringBuilder();
        Set<Point> oneOutToThreePointIns = new HashSet<>();
        if (points.size() == 1) {
            builder.append(getThreePoints(points));
        } else if (points.size() == 2) {
            builder.append(getTwoPoints(points));
        } else {
            for (Point p1 : points) {
                for (Point p2 : points) {
                    if (!p1.equals(p2)) {
                        List<Point[]> possibleRestTwoSquarePoints = getPossibleRestTwoSquarePoints(p1, p2);
                        for (Point[] pointPair : possibleRestTwoSquarePoints) {
                            if (isPointsLong(pointPair)) {
                                int countPointsInSet = 0;
                                for (Point p : pointPair) {
                                    if (points.contains(p)) {
                                        oneOutToThreePointIns.add(p == p1 ? p2 : p1);
                                        countPointsInSet++;
                                    }
                                }
                                if (countPointsInSet == 2) {
                                    return "0";
                                }
                            }
                        }
                    }
                }
            }
            if (!oneOutToThreePointIns.isEmpty()) {
                Point last = oneOutToThreePointIns.iterator().next();
                builder.append("1").append("\n");
                builder.append((long) last.x).append(" ").append((long) last.y).append("\n");
            } else {
                builder.append(getTwoPoints(points));
            }
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private static StringBuilder getThreePoints(Set<Point> points) {
        Point point = points.iterator().next();
        StringBuilder builder = new StringBuilder();
        builder.append("3").append("\n");
        builder.append((long) (point.x + 1)).append(" ").append((long) point.y).append("\n");
        builder.append((long) point.x).append(" ").append((long) (point.y + 1)).append("\n");
        builder.append((long) (point.x + 1)).append(" ").append((long) (point.y + 1)).append("\n");
        return builder;
    }

    private static StringBuilder getTwoPoints(Set<Point> points) {
        StringBuilder builder = new StringBuilder();
        Point[] pointsArray = points.toArray(new Point[0]);
        for (int i = 0; i < pointsArray.length - 1; i++) {
            for (int j = i + 1; j < pointsArray.length; j++) {
                List<Point[]> possibleRestTwoSquarePoints = getPossibleRestTwoSquarePoints(pointsArray[i],
                        pointsArray[j]);

                for (Point[] pointsPair : possibleRestTwoSquarePoints) {
                    if (isPointsLong(pointsPair)) {
                        builder.append("2").append("\n");
                        for (Point p : pointsPair) {
                            builder.append((long) p.x).append(" ").append((long) p.y).append("\n");
                        }
                        return builder;
                    }
                }
            }
        }
        return builder;
    }

    private static boolean isPointsLong(Point[] points) {
        for (Point p : points) {
            if (p.x != (long) p.x || p.y != (long) p.y || p.x > BILLION || p.y > BILLION) {
                return false;
            }
        }
        return true;
    }

    private static List<Point[]> getPossibleRestTwoSquarePoints(Point point, Point point2) {
        double vectorX = point2.x - point.x;
        double vectorY = point2.y - point.y;

        if (Math.abs(vectorY) < EPSILON) {
            return getPossiblePointsFromHoriz(point, point2);
        } else if (Math.abs(vectorX) < EPSILON) {
            return getPossiblePointsFromVert(point, point2);
        } else {
            return getPossibleDiagonalSquarePoints(point, point2);
        }
    }

    private static List<Point[]> getPossibleDiagonalSquarePoints(Point point, Point point2) {
        double midX = (point.x + point2.x) / 2.0;
        double midY = (point.y + point2.y) / 2.0;
        double vectorX = point.x - midX;
        double vectorY = point.y - midY;

        Point vertex3 = new Point(midX + vectorY, midY - vectorX);
        Point vertex4 = new Point(midX - vectorY, midY + vectorX);

        List<Point[]> possiblePoints = new ArrayList<>();
        possiblePoints.add(new Point[] { vertex3, vertex4 });

        return possiblePoints;
    }

    private static List<Point[]> getPossiblePointsFromHoriz(Point point, Point point2) {
        List<Point[]> possiblePoints = new ArrayList<>();
        double length = Math.abs(point2.x - point.x);
        possiblePoints
                .add(new Point[] { new Point(point.x, point.y + length), new Point(point2.x, point2.y + length) });
        possiblePoints
                .add(new Point[] { new Point(point.x, point.y - length), new Point(point2.x, point2.y - length) });
        return possiblePoints;
    }

    private static List<Point[]> getPossiblePointsFromVert(Point point, Point point2) {
        List<Point[]> possiblePoints = new ArrayList<>();
        double length = Math.abs(point2.y - point.y);
        possiblePoints
                .add(new Point[] { new Point(point.x + length, point.y), new Point(point2.x + length, point2.y) });
        possiblePoints
                .add(new Point[] { new Point(point.x - length, point.y), new Point(point2.x - length, point2.y) });
        return possiblePoints;
    }
}

class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point point = (Point) obj;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result = 17;
        long xBits = Double.doubleToLongBits(x);
        long yBits = Double.doubleToLongBits(y);
        result = 31 * result + (int) (xBits ^ (xBits >>> 32));
        result = 31 * result + (int) (yBits ^ (yBits >>> 32));
        return result;
    }
}