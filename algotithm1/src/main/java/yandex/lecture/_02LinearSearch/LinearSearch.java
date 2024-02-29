package yandex.lecture._02LinearSearch;

/*
Description:
Дана последовательность чисел длинной N.
Найти первое (левое) вхождение положительного числа Х в нее
или вывести -1, если число Х не встречалось
 */

public class LinearSearch {

    public int search(String line, int x) {
        String[] seq = line.split(" ");
        int answer = -1;
        for (int i = 0; i < seq.length; i++) {
            if (Integer.parseInt(seq[i]) == x && answer == -1) {
                answer = i;
            }
        }
        return answer;
    }
}
