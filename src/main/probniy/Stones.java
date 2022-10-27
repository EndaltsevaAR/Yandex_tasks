package main.probniy;
/*
Description:
Камни и украшения [тестовая задача]
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Даны две строки строчных латинских символов: строка J и строка S. Символы, входящие в строку J, — «драгоценности»,
входящие в строку S — «камни». Нужно определить, какое количество символов из S одновременно являются «драгоценностями».
Проще говоря, нужно проверить, какое количество символов из S входит в J.

Формат ввода
На двух первых строках входного файла содержатся две строки строчных латинских символов: строка J и строка S.
Длина каждой не превосходит 100 символов.
Формат вывода
Выходной файл должен содержать единственное число — количество камней, являющихся драгоценностями.
 */
import java.util.Map;
import java.util.Scanner;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Stones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int result = 0;
        String jewel = scanner.nextLine().trim();
        String stone = scanner.nextLine().trim();
        Map<Character, Long> jewelMap = jewel.codePoints()
                .map(Character::toLowerCase)
                .mapToObj(c -> (char) c)
                .collect(groupingBy(identity(), counting()));
        Map<Character, Long> stoneMap = stone.codePoints()
                .map(Character::toLowerCase)
                .mapToObj(c -> (char) c)
                .collect(groupingBy(identity(), counting()));
        for (Map.Entry<Character, Long> pair :jewelMap.entrySet()) {
            if (stoneMap.containsKey(pair.getKey())) {
                result += Math.min(stoneMap.get(pair.getKey()), pair.getKey());
            }
        }
        System.out.println(result);

    }
}
