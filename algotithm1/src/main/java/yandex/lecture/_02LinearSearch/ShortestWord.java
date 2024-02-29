package yandex.lecture._02LinearSearch;
/*
Description:
Дана последовательность слов. Вывести два самых коротких слова через пробел
*/

import java.util.ArrayList;
import java.util.List;

public class ShortestWord {

    public String shortWord(String line) {
        String[] seq = line.split(" ");
        int minLen = seq[0].length();
        for (int i = 1; i < seq.length; i++) {
            if (seq[i].length() < minLen) {
                minLen = seq[i].length();
            }
        }
        List<String> result = new ArrayList<>();
        for (String s : seq) {
            if (s.length() == minLen) {
                result.add(s);
            }
        }
        return String.join(" ", result);
    }

}
