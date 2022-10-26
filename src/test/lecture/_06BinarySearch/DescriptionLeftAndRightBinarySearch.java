package test.lecture._06BinarySearch;

/* Время поиска O(log)"""
Левый бинпоиск - первое подходящее значение
Правый бинпоиск - последнее подходящее значение
 */

import java.util.List;

public class DescriptionLeftAndRightBinarySearch {
    public int leftSearch(int left, int right, List<Integer> params) {
        while (left < right) {
            int m = (left + right) / 2;
            if (someCheck(m, params)) {
                right = m;
            } else {
                left = m + 1;
            }
        }
        return left;
    }

    public int rightSearch(int left, int right, List<Integer> params) {
        while (left < right) {
            int m = (left + right + 1) / 2;
            if (someCheck(m, params)) {
                left = m;
            } else {
                right = m - 1;
            }
        }
        return left;
    }

    private boolean someCheck(int m, List<Integer> params) {
        return true;
    }

}
