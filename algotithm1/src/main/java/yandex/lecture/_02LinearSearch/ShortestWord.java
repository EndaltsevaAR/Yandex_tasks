package yandex.lecture._02LinearSearch;
/*
Description:
Дана последовательность слов. Вывести два самых коротких слова через пробел
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShortestWord {
    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // String line = scanner.nextLine();
    // scanner.close();
    // String[] seq = line.split(" ");
    // System.out.println(shortWord(seq));
    // }

    private static String shortWord(String[] seq) {
        int min_len = seq[0].length();
        for (int i = 1; i < seq.length; i++) {
            if (seq[i].length() < min_len) {
                min_len = seq[i].length();
            }
        }
        List<String> result = new ArrayList<>();
        for (String s : seq) {
            if (s.length() == min_len) {
                result.add(s);
            }
        }
        return String.join(" ", result);
    }

}
