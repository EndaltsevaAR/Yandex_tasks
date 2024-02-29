package main.my.homework3;

/*
Description:
Анаграмма?
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Задано две строки, необходимо проверить, является ли одна анаграммой другой. Анаграммами называются строки,
состоящие из одних и тех же букв.

Формат ввода
Строки состоят из маленьких латинских букв, их длина не превосходит 100000. Каждая записана в отдельной строке.

Формат вывода
Выведите YES если одна из строк является анаграммой другой и NO в противном случае.
 */

import java.util.Map;
import java.util.Scanner;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Anagram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();
        System.out.println(solution(line1, line2));
        scanner.close();
    }

    public static String solution(String line1, String line2) {
        Map<Character, Long> line1Map = findMap(line1);
        Map<Character, Long> line2Map = findMap(line2);
        if (!line1Map.equals(line2Map)) {
            return "NO";
        }
        return "YES";
    }

    private static Map<Character, Long> findMap(String text) {
       return text.codePoints()
                .map(Character::toLowerCase)
                .mapToObj(c -> (char) c)
                .collect(groupingBy(identity(), counting()));
    }
}
