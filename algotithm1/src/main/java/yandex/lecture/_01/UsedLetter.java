package yandex.lecture._01;

/*
Description:
Дана строка. Найти в ней самый часто встречающийся символ. Если несколько символов
встречаются одинаково часто, то можно вывести любой
Сложность - O(N)
 */

import java.util.HashMap;
import java.util.Map;

public class UsedLetter {
    public Character findMostUsedLetter(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }
        int answerInt = 0;
        char answerChar = ' ';
        Map<Character, Integer> lettersMap = new HashMap<>();
        for (int i = 0; i < line.length(); i++) {
            if (!lettersMap.containsKey(line.charAt(i))) {
                lettersMap.put(line.charAt(i), 0);
            }
            lettersMap.put(line.charAt(i), lettersMap.get(line.charAt(i)) + 1);
            if (lettersMap.get(line.charAt(i)) > answerInt) {
                answerInt = lettersMap.get(line.charAt(i));
                answerChar = line.charAt(i);
            }
        }
        System.out.println(answerChar);
        return answerChar;
    }
}
