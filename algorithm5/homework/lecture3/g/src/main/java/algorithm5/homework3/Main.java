package algorithm5.homework3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static final double EPSILON = 1e-9;
    public static final double BILLION = 1e9;

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        Set<Point> points = new HashSet<>();
        int nNumberPoints = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < nNumberPoints; i++) {
            String line = scanner.nextLine();
            String[] splited = line.split(" ");
            Point point = new Point(Double.parseDouble(splited[0]), Double.parseDouble(splited[1]));
            points.add(point);
        }
        System.out.println(finishSquare(points));
        long end = System.currentTimeMillis();
        System.out.println("Time is " + (end - startTime));
        scanner.close();
    }

    public static String finishSquare(Set<Point> pointsSet) {
        StringBuilder builder = new StringBuilder();
        List<Point> pointsList = new ArrayList<>(pointsSet);
        Set<Point> oneOutToThreePointIns = new HashSet<>();
        if (pointsSet.size() == 1) {
            builder.append(getThreePoints(pointsList));
        } else if (pointsSet.size() == 2) {
            builder.append(getTwoPoints(pointsList));
        } else {
            // поиск есть ли 4 точки, заодно создаем сет из 3 точек
            for (int i = 0; i < pointsList.size() - 1; i++) {
                for (int j = i + 1; j < pointsList.size(); j++) {
                    List<Point[]> possibleRestTwoSquarePoints = getPossibleRestTwoSquarePoints(pointsList.get(i),
                            pointsList.get(j));
                    for (Point[] pointPair : possibleRestTwoSquarePoints) {
                        if (isPointsLong(pointPair)) {
                            int countPointsInSet = 0;
                            for (int k = 0; k < pointPair.length; k++) {
                                if (pointsList.contains(pointPair[k])) {
                                    Point lastPoint = null;
                                    if (k == 0) {
                                        lastPoint = pointPair[1];
                                    } else {
                                        lastPoint = pointPair[0];
                                    }
                                    oneOutToThreePointIns.add(lastPoint);
                                    countPointsInSet++;
                                }
                            }
                            if (countPointsInSet == 2) {
                                return "0"; // все 4 точки уже в сете
                            }
                        }

                    }
                }
            }
            // рассматриваем сет из 3 точек, если он пуст, берем любой из двух точек
            if (!oneOutToThreePointIns.isEmpty()) {
                Point last = oneOutToThreePointIns.iterator().next();
                builder.append("1").append("\n");
                builder.append((long) last.x).append(" ").append((long) last.y).append("\n");
            } else {
                builder.append(getTwoPoints(pointsList));
            }

        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private static StringBuilder getThreePoints(List<Point> pointsList) {
        Point point = pointsList.get(0);
        StringBuilder builder = new StringBuilder();
        builder.append("3").append("\n");
        builder.append((long) (point.x + 1)).append(" ").append((long) point.y).append("\n");
        builder.append((long) point.x).append(" ").append((long) (point.y + 1)).append("\n");
        builder.append((long) (point.x + 1)).append(" ").append((long) (point.y + 1)).append("\n");
        return builder;
    }

    private static StringBuilder getTwoPoints(List<Point> pointsList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pointsList.size() - 1; i++) {
            for (int j = i + 1; j < pointsList.size(); j++) {
                // if (pointsList.get(i).equals(pointsList.get(j))) {
                // continue;
                // } else {
                List<Point[]> possibleRestTwoSquarePoints = getPossibleRestTwoSquarePoints(pointsList.get(i),
                        pointsList.get(j));

                for (Point[] points : possibleRestTwoSquarePoints) {
                    if (isPointsLong(points)) {
                        builder.append("2").append("\n");
                        for (Point point : points) {
                            builder.append((long) point.x).append(" ").append((long) point.y).append("\n");
                        }
                        return builder;
                    }
                }
                // }
            }
        }
        return builder; // по идее до сюда не должно дойти
    }

    private static boolean isPointsLong(Point[] points) {
        for (Point point : points) {
            if (point.x != (long) point.x || point.y != (long) point.y) {
                return false;
            }
            if (point.x > BILLION || point.y > BILLION) {
                return false;
            }
        }
        return true;
    }

    private static List<Point[]> getPossibleRestTwoSquarePoints(Point point, Point point2) {

        // Находим вектор между двумя заданными точками
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

        // Находим вектор от середины диагонали к одной из диагональных точек
        double vectorX = point.x - midX;
        double vectorY = point.y - midY;

        // Находим вторую вершину квадрата
        Point vertex3 = new Point(midX + vectorY, midY - vectorX);
        // Находим третью вершину квадрата
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
        // если будет добавить, поставить еще на ромб
        return possiblePoints;
    }

    private static List<Point[]> getPossiblePointsFromVert(Point point, Point point2) {
        List<Point[]> possiblePoints = new ArrayList<>();
        double length = Math.abs(point2.y - point.y);
        possiblePoints
                .add(new Point[] { new Point(point.x + length, point.y), new Point(point2.x + length, point2.y) });
        possiblePoints
                .add(new Point[] { new Point(point.x - length, point.y), new Point(point2.x - length, point2.y) });
        // если будет добавить, поставить еще на ромб
        return possiblePoints;
    }

}

class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.y = y;
        this.x = x;
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

}