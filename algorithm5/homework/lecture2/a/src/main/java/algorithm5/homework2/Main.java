package algorithm5.homework2;

/**
 На клетчатой плоскости закрашено K клеток. Требуется найти минимальный по площади прямоугольник,
  со сторонами, параллельными линиям сетки, покрывающий все закрашенные клетки.

Формат ввода
Во входном файле, на первой строке, находится число K (1 ≤ K ≤ 100). 
На следующих K строках находятся пары чисел Xi и Yi — координаты закрашенных клеток (|Xi|, |Yi| ≤ 10^9).

Формат вывода
Выведите в выходной файл координаты левого нижнего и правого верхнего углов прямоугольника.
 */

import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberDots = Integer.parseInt(scanner.nextLine());
        String[] dots = new String[numberDots];
        for (int i = 0; i < numberDots; i++) {
            dots[i] = scanner.nextLine();
        }
        System.out.println(getFigure(dots));
        scanner.close();
    }

    public static String getFigure(String[] dots) {
        TreeSet<Integer> xs = new TreeSet<>();
        TreeSet<Integer> ys = new TreeSet<>();
        for (int i = 0; i < dots.length; i++) {
            xs.add(Integer.parseInt(dots[i].split(" ")[0]));
            ys.add(Integer.parseInt(dots[i].split(" ")[1]));
        }

        return xs.first() + " " + ys.first() + " " + xs.last() + " " + ys.last();
    }
}