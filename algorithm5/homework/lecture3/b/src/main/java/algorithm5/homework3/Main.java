package algorithm5.homework3;

/**
Задано две строки, нужно проверить, является ли одна анаграммой другой. Анаграммой называется строка, полученная из другой перестановкой букв.

Формат ввода
Строки состоят из строчных латинских букв, их длина не превосходит 100000. Каждая записана в отдельной строке.

Формат вывода
Выведите "YES" если одна из строк является анаграммой другой и "NO" в противном случае.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        String word1 = scanner.nextLine();
        String word2 = scanner.nextLine();
        System.out.println(isAnagram(word1, word2));
        scanner.close();
    }

    public static String isAnagram(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return "NO";
        }
        List<Character> word1List = word1.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> word2List = word2.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        Collections.sort(word1List);
        Collections.sort(word2List);

        if (word1List.equals(word2List)) {
            return "YES";
        } else {
            return "NO";
        }
    }
}