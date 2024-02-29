package yandex.lecture._04Tables;
/*
Description:
На шахматной доске N*N находятся M ладей (ладья бьет клетки на той же
горизонтали или вертикали до ближайшей занятой)

Определите, сколько пар ладей бьют друг друга.
Ладьи задаются парой чисел Х и У, обозначающих координаты клетки.
1<= N <= 10^9
0 <= M <= 2 * 10^5
 сложность О(М)
для ферзя нужны еще 2 словаря для i - j и i + j ля поиска по диагонали
 */

import java.util.*;

public class Chess {
    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // String line = scanner.nextLine();
    // List<String> rocks = Arrays.stream(line.split(" ")).toList();
    // List<List<Integer>> rocksCoordinates = new ArrayList<>();
    // for (String rock : rocks) {
    // List<Integer> coordinates =
    // Arrays.stream(rock.split(",")).map(Integer::parseInt).toList();
    // rocksCoordinates.add(coordinates);
    // }
    // System.out.println(findRockPairs(rocksCoordinates));
    // }

    public static int findRockPairs(List<List<Integer>> rocksCoordinates) {
        Map<Integer, Integer> coordinates_x = new HashMap<>();
        Map<Integer, Integer> coordinates_y = new HashMap<>();
        for (List<Integer> rock : rocksCoordinates) {
            addToMap(coordinates_x, rock.get(0));
            addToMap(coordinates_y, rock.get(1));
        }
        int x_pairs = countPairs(coordinates_x);
        int y_pairs = countPairs(coordinates_y);
        return x_pairs + y_pairs;
    }

    private static int countPairs(Map<Integer, Integer> coordinates) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> pair : coordinates.entrySet()) {
            sum += pair.getValue() - 1;
        }
        return sum;
    }

    private static void addToMap(Map<Integer, Integer> coordinates, Integer integer) {
        if (!coordinates.containsKey(integer)) {
            coordinates.put(integer, 0);
        }
        coordinates.put(integer, coordinates.get(integer) + 1);
    }

}
