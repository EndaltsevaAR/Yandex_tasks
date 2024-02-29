package yandex.lecture._02LinearSearch;

/*
Description:
Дана последовательность чисел длинной N.
Найти минимальное четное число в последовательности или вывести -1, если такое
число не существует
 */

public class MinEven {

    public int minEven(String line) {
        String[] seq = line.split(" ");
        int answer = -1;
        boolean flag = false;
        for (String s : seq) {
            if (Integer.parseInt(s) % 2 == 0 &&
                    (!flag || Integer.parseInt(s) < answer)) {
                answer = Integer.parseInt(s);
                flag = true;
            }
        }
        return answer;
    }
}
