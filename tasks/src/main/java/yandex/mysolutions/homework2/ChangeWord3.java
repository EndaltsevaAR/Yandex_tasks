package main.my.homework2;

/*
Description:
Замена слов
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
С целью экономии чернил в картридже принтера было принято решение укоротить некоторые слова в тексте.
Для этого был составлен словарь слов, до которых можно сокращать более длинные слова. Слово из текста можно сократить,
если в словаре найдется слово, являющееся началом слова из текста. Например, если в списке есть слово "лом", то слова
из текста "ломбард", "ломоносов" и другие слова, начинающиеся на "лом", можно сократить до "лом".

Если слово из текста можно сократить до нескольких слов из словаря, то следует сокращать его до самого короткого слова.

Формат ввода
В первой строке через пробел вводятся слова из словаря, слова состоят из маленьких латинских букв. Гарантируется,
что словарь не пуст и количество слов в словаре не превышет 1000, а длина слов — 100 символов.

Во второй строке через пробел вводятся слова текста (они также состоят только из маленьких латинских букв). Количество
слов в тексте не превосходит 105, а суммарное количество букв в них — 106.

Формат вывода
Выведите текст, в котором осуществлены замены.
 */

import java.util.*;
import java.util.stream.Collectors;

public class ChangeWord3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dict = scanner.nextLine();
        String[] dicts = dict.split(" ");
        String text = scanner.nextLine();
        String[] words = text.split(" ");
        System.out.println(solution(dicts, words));
    }

    public static String solution(String[] dicts, String[] words) {
        Set<String> dictSet = Arrays.stream(dicts).collect(Collectors.toCollection(TreeSet::new));
        List<String> result = new ArrayList<>();
        for (String word : words) {
            boolean isFind = false;
            for (int j = 0; j < word.length() && !isFind; j++) {
                String subStr = word.substring(0, j);
                if (dictSet.contains(subStr)) {
                    result.add(subStr);
                    isFind = true;
                }
            }
            if (!isFind) {
                result.add(word);
            }
        }
        return String.join(" ", result);
    }
}
