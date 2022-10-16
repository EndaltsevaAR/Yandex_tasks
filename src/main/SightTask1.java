package main;

/*
Description:
A. Достопримечательности
Ограничение времени 1 секунда
Ограничение памяти 256Mb
Ввод стандартный ввод или input.txt
Вывод стандартный вывод или output.txt
Тур по городу N для Кати, Маши и Наташи будет удачным, если они посмотрят самые ожидаемые достопримечательности в
нужном порядке. Составьте кратчайшую последовательность достопримечательностей для посещения, чтобы пожелания были
выполнены.
Даны три последовательности чисел. В любой из последовательностей числа могут повторяться.
Вам нужно найти супер-последовательность. Супер-последовательность – это последовательность минимальной длины, чтобы в
ней были все три последовательности непрерывными фрагментами.

Формат ввода: Ввод состоит из трех строк. Первый элемент каждой строки ni(1≤ni≤100) – число элементов в i-й
последовательности. Далее идут ni положительных чисел, не превосходящих 100, – элементы последовательности.
Все числа в строках разделены пробелами.

Формат вывода
Первая строка вывода содержит одно число  k – длина супер-последовательности.
Вторая строка содержит k разделенных пробелами чисел – элементы супер-последовательности.
 */

import java.util.*;

public class SightTask1 {
    public static final int NUMBER_GIRLS = 3;
    public static void main(String[] args) {
        List<List<Integer>> ways = new ArrayList<>();
        int numberOfLongestWay = input(ways);
        List<Integer> resultList = new ArrayList<>(ways.get(numberOfLongestWay));
        dynamicSolution(ways, resultList, numberOfLongestWay);
        output(resultList);
    }

    private static int input(List<List<Integer>> ways) {
        Scanner in = new Scanner(System.in);
        int maxWaySize = 0;
        int numberOfLongestWay = 0;
        for (int i = 0; i < NUMBER_GIRLS; i++) {
            List<Integer> way = new ArrayList<>();
            int size = in.nextInt();
            if (size > maxWaySize) {
                maxWaySize = size;
                numberOfLongestWay = i;
            }
            if (size < 1 || size > 100) {
                System.exit(1);
            }
            for (int j = 0; j < size; j++) {
                int temp = in.nextInt();
                if (temp < 0 || temp > 100) {
                    System.exit(1);
                }
                way.add(temp);
            }
            ways.add(way);
        }
        in.close();
        return numberOfLongestWay;
    }

    private static void dynamicSolution(List<List<Integer>> ways, List<Integer> resultList, int numberOfLongestWay) {
        boolean[] isWayChecked = new boolean[NUMBER_GIRLS];
        isWayChecked[numberOfLongestWay] = true;

        while (hasAnChecked(isWayChecked)) {
            //List: first - length, second - start at the result, third - end at the result
            Map<Integer, List<Integer>> variants = new HashMap<>();

            for (int i = 0; i < NUMBER_GIRLS; i++) {
                if (!isWayChecked[i]) {
                    doTable(resultList, ways.get(i), variants, i);
                }
            }
            if (hasAnChecked(isWayChecked)) {
                int way = findRowWithMax(variants);
                if (way == -1) {
                    System.exit(1);
                }
                if (variants.get(way).get(2).equals(resultList.size() - 1)) {
                    //way - key to list, at the list start at the second position
                    resultList.addAll(ways.get(way).subList(variants.get(way).get(0), ways.get(way).size()));
                } else {
                    resultList.addAll(0, ways.get(way).
                            subList(0, ways.get(way).size() - variants.get(way).get(0)));
                }
                isWayChecked[way] = true;
            }
        }
    }
    private static boolean hasAnChecked(boolean[] isWayChecked) {
        for (boolean flag : isWayChecked) {
            if (!flag) {
                return true;
            }
        }
        return false;
    }

    private static void doTable(List<Integer> resultList, List<Integer> way,
                                Map<Integer, List<Integer>> variants, int wayNumber) {
        int max = 0;
        int[][] table = new int[way.size()][resultList.size()];
        //for first row to protect from out of array exception
        for (int i = 0; i < resultList.size(); i++) {
            if (!way.get(0).equals(resultList.get(i))) {
                table[0][i] = 0;
            } else {
                if (max == 0) {
                    max = 1;
                }
                table[0][i] = 1;
            }
        }

        //for first column to protect from out of array exception
        for (int i = 0; i < way.size(); i++) {
            if (!way.get(i).equals(resultList.get(0))) {
                table[i][0] = 0;
            } else {
                if (max == 0) {
                    max = 1;
                }
                table[i][0] = 1;
            }
        }

        for (int i = 1; i < way.size(); i++) {
            for (int j = 1; j < resultList.size(); j++) {
                if (way.get(i).equals(resultList.get(j))) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                    if (max < table[i][j]) {
                        max = table[i][j];
                    }
                } else {
                    table[i][j] = 0;
                }
            }
        }

        int tempMax = max;  // if max substring in the inside string, not with boundaries, we can take another substrings
        while (tempMax >= 0) {
            for (int i = 0; i < way.size(); i++) {
                for (int j = 0; j < resultList.size(); j++) {
                    if (table[i][j] == tempMax && (j == resultList.size() - 1 || i == way.size() - 1)) {
                        List<Integer> row = new ArrayList<>();
                        row.add(tempMax);
                        row.add(j - tempMax);
                        row.add(j);
                        variants.put(wayNumber, row);
                        return;
                    }
                }
            }
            tempMax--;
        }
    }
    private static int findRowWithMax(Map<Integer, List<Integer>> variants) {
        int max = 0;
        for (List<Integer> integers : variants.values()) {
            if (integers.get(0).compareTo(max) > 0) {
                max = integers.get(0);
            }
        }
        for (Map.Entry<Integer, List<Integer>> pair : variants.entrySet()) {
            if (pair.getValue().get(0).equals(max)) {
                return pair.getKey();
            }
        }
        return -1;
    }

    private static void output(List<Integer> resultList) {
        System.out.println(resultList.size());
        for (Integer integers : resultList) {
            System.out.print(integers + " ");
        }
    }
}
