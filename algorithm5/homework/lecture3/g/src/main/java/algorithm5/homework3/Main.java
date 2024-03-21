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

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        Set<Point> points = new HashSet<>();
        int nNumberPoints = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < nNumberPoints; i++) {
            String line = scanner.nextLine();
            String[] splited = line.split(" ");
            Point point = new Point(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]));
            points.add(point);
        }
        System.out.println(finishSquare(points));
    }

    public static String finishSquare(Set<Point> pointsSet) {
        StringBuilder builder = new StringBuilder();
        List<Point> pointsList = new ArrayList<>(pointsSet);
        Set<Point[]> threePoints = new HashSet<>();
        if (pointsSet.size() == 1) {
            builder.append(getThreePoints(pointsList));
        } else if (pointsSet.size() == 2) {
            builder.append(getTwoPoints(pointsList));
        } else {
            // поиск есть ли 4 точки, заодно создаем сет из 3 точек
            for (int i = 0; i < pointsList.size() - 1; i++) {
                for (int j = 0; j < pointsList.size(); j++) {
                    if (pointsList.get(i).equals(pointsList.get(j))) {
                        continue;
                    } else {
                        List<Point[]> possibleRestTwoSquarePoints = getPossibleRestTwoSquarePoints(pointsList.get(i),
                                pointsList.get(j));
                        for (Point[] pointPair : possibleRestTwoSquarePoints) {
                            int countPointsInSet = 0;
                            for (int k = 0; k < pointPair.length; k++) {
                                if (pointsSet.contains(pointPair[k])) {
                                    Point[] threePointArray = new Point[3];
                                    threePointArray[0] = pointsList.get(i);
                                    threePointArray[1] = pointsList.get(j);
                                    threePointArray[2] = pointPair[k];
                                    threePoints.add(threePointArray);
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
            if (!threePoints.isEmpty()) {
                for (Point[] points : threePoints) {
                    if (isPointsInt(points)) {
                        return builder.append(getOnePoints(points)).toString();
                    }
                }

            }
            builder.append(getTwoPoints(pointsList));
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private static StringBuilder getThreePoints(List<Point> pointsList) {
        Point point = pointsList.get(0);
        StringBuilder builder = new StringBuilder();
        builder.append("3").append("\n");
        builder.append(point.y + 1).append(" ").append(point.x).append("\n");
        builder.append(point.y).append(" ").append(point.x + 1).append("\n");
        builder.append(point.y + 1).append(" ").append(point.x + 1).append("\n");
        return builder;
    }

    private static StringBuilder getTwoPoints(List<Point> pointsList) {
        StringBuilder builder = new StringBuilder();
        List<Point[]> possibleRestTwoSquarePoints = getPossibleRestTwoSquarePoints(pointsList.get(0),
                pointsList.get(1));
        builder.append("2").append("\n");
        for (Point[] points : possibleRestTwoSquarePoints) {
            if (isPointsInt(points)) {
                for (Point point : points) {
                    builder.append(point.y).append(" ").append(point.x).append("\n");
                }
                return builder;
            }

        }
        return builder; // по идее до сюда не должно дойти
    }

    private static boolean isPointsInt(Point[] points) {
        for (Point point : points) {
            if (point.x != (int) point.x || point.y != (int) point.y) {
                return false;
            }
        }
        return true;
    }

    private static List<Point[]> getPossibleRestTwoSquarePoints(Point point, Point point2) {
        List<Point[]> possiblePoints = new ArrayList<>();

        // Находим вектор между двумя заданными точками
        double vectorX = point2.x - point.x;
        double vectorY = point2.y - point.y;

        // Длина вектора
        double length = Math.sqrt(vectorX * vectorX + vectorY * vectorY);

        if (Math.abs(length - Math.sqrt(2)) < EPSILON) { // Если длина вектора равна длине диагонали квадрата
            // Вызываем метод для случая, когда точки на диагонали квадрата
            return getPossibleDiagonalSquarePoints(point, point2);
        } else {
            // Вызываем метод для случая, когда точки на одной стороне квадрата
            return getPossibleSideSquarePoints(point, point2);
        }
    }

    private static List<Point[]> getPossibleDiagonalSquarePoints(Point point, Point point2) {
        // Реализация метода для случая, когда точки на диагонали квадрата
        List<Point[]> possiblePoints = new ArrayList<>();

        // Находим вектор между двумя заданными точками
        double vectorX = point2.x - point.x;
        double vectorY = point2.y - point.y;

        // Находим середину вектора
        double centerX = (point.x + point2.x) / 2;
        double centerY = (point.y + point2.y) / 2;

        // Поворачиваем вектор на 90 градусов
        double rotatedVectorX = -vectorY;
        double rotatedVectorY = vectorX;

        // Добавляем к середине вектора повернутый вектор, чтобы найти координаты
        // оставшихся двух углов квадрата
        possiblePoints.add(new Point[] { new Point(centerY + rotatedVectorY, centerX + rotatedVectorX),
                new Point(centerY - rotatedVectorY, centerX - rotatedVectorX) });
        possiblePoints.add(new Point[] { new Point(centerY - rotatedVectorY, centerX - rotatedVectorX),
                new Point(centerY + rotatedVectorY, centerX + rotatedVectorX) });

        return possiblePoints;
    }

    private static List<Point[]> getPossibleSideSquarePoints(Point point, Point point2) {
        // Реализация метода для случая, когда точки на одной стороне квадрата
        List<Point[]> possiblePoints = new ArrayList<>();

        // Находим вектор между двумя заданными точками
        double vectorX = point2.x - point.x;
        double vectorY = point2.y - point.y;

        // Находим второй вектор, перпендикулярный первому
        double secondVectorX = -vectorY;
        double secondVectorY = vectorX;

        // Находим координаты оставшихся двух углов квадрата
        possiblePoints.add(new Point[] { new Point(point.x + secondVectorX, point.y + secondVectorY),
                new Point(point.x - secondVectorX, point.y - secondVectorY) });
        possiblePoints.add(new Point[] { new Point(point2.x + secondVectorX, point2.y + secondVectorY),
                new Point(point2.x - secondVectorX, point2.y - secondVectorY) });

        return possiblePoints;
    }

    private static StringBuilder getOnePoints(Point[] threePoint) {
        double sumX = 0, sumY = 0;
        double minX = Double.MAX_VALUE, maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE, maxY = Double.MIN_VALUE;
        for (Point point : threePoint) {
            sumX += point.x;
            sumY += point.y;
            minX = Math.min(minX, point.x);
            maxX = Math.max(maxX, point.x);
            minY = Math.min(minY, point.y);
            maxY = Math.max(maxY, point.y);
        }
        double lastX = sumX - minX - maxX;
        double lastY = sumY - minY - maxY;

        StringBuilder builder = new StringBuilder();
        builder.append("1").append("\n");
        builder.append(lastY).append(" ").append(lastX);
        return builder;
    }
}

class Point {
    public double y;
    public double x;

    public Point(double y, double x) {
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
        return Double.compare(point.y, y) == 0 && Double.compare(point.x, x) == 0;
    }

}