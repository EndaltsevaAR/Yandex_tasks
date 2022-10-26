package main.lecture._06BinarySearch;

import java.util.Scanner;

/*
Description:
Михаил читает лекции по алгоритмам. За кадром стоит доска размером
W * H сантиметров. Михаилу нужно разместить на доске N квадртаных
стикеров со шпаргалками, при этом длина стороны стикера в сантиметрах должна быть
целым числом.
Определите максимальную длину стикера, чтобы все стикеры поместились на доске.
 */
public class Lecture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int w = Integer.parseInt(scanner.nextLine());
        int h = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());
        System.out.println(rightSearch(w, h, n));
    }

    public static int rightSearch(int w, int h, int n) {
        int right = Math.max(w, h);
        int left = 0;
        while (left < right) {
            int m = (left + right + 1) / 2;
            if ((w / m) * (h / m) >= n) {
                left = m;
            } else {
                right = m -1;
            }
        }
        return left;
    }
}

