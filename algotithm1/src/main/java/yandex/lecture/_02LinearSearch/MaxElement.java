package yandex.lecture._02LinearSearch;

/*
Description:
Дана последовательность чисел длинной N.
Найти максимальное число в последовательности, если гарантированно, что она не пустая
 */

public class MaxElement {

    public int maxElement(String line) {
        String[] seq = line.split(" ");
        int answer = Integer.parseInt(seq[0]);
        for (int i = 1; i < seq.length; i++) {
            if (Integer.parseInt(seq[i]) > answer) {
                answer = Integer.parseInt(seq[i]);
            }
        }
        return answer;
    }
}
