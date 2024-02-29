package main.my.homework2;
/*
Description:
Отбраковка
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
На заводе по производству химических соединений есть реактор, в результате работы которого получаются химические
вещества, формула которых записывается в виде строки состоящей из заглавных и строчных латинских букв.

Также у завода есть список желательных веществ (веществ первого сорта), которые хотелось бы получить.
Кроме того заводу также интересны вещества второго и третьего сорта.

Вещество называется веществом первого сорта, если его формула в точности совпадает с формулой из списка
желательных веществ.

Вещество называется веществом второго сорта, если из него можно получить вещество из списка желательных в
результате замены некоторых заглавных букв на строчные и наоборот.

Вещество называется веществом третьего сорта, если в нем возможны как замены букв с заглавных на строчные и наоборот,
так и замены любых гласных букв (aeiou) на другие гласные буквы, таким образом чтобы получилось вещество из списка желательных.

В связи со сложной экономической ситуацией было принято решение не выбрасывать вещества второго и третьего сорта и для
каждого из них найти похожее вещество из списка желательных, причем, если преобразованиями из вещества второго или
третьего сорта можно получить несколько веществ из списка желательных, то его следует преобразовывать к тому веществу,
которое находится раньше в списке желательных.

Формат ввода
В первой строке вводится число n (1 ≤ n ≤ 5000) — количество веществ в списке желательных.
Во второй строке вводится n слов через пробел — список желательных веществ.
В третьей строке вводится число k (1 ≤ k ≤ 5000) — количество веществ, полученных в реакторе.
Во второй строке вводится k слов через пробел — список веществ, полученных в реакторе.

Длина всех слов не превосходит 7.

Формат вывода
Для каждого вещества выведите:
Если оно является веществом первого сорта — название этого вещества.
Если оно не является веществом первого сорта, но является веществом второго сорта — первое из списка желательных
веществ, к которому можно преобразовать это вещество.
Если оно не является веществом первого или второго сорта, но является веществом третьего сорта — первое из списка
желательных веществ, к которому можно преобразовать это вещество.

В противном случае выведите пустую строку.
Все слова в вывод должны быть разделены одним пробелом.
 */

import java.util.*;
import java.util.stream.Collectors;

public class Rejection5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = Integer.parseInt(scanner.nextLine());
        String dict = scanner.nextLine();
        String[] dicts = dict.trim().split(" ");
        int y = Integer.parseInt(scanner.nextLine());
        String text = scanner.nextLine();
        String[] words = text.trim().split(" ");
        System.out.println(solution(dicts, words));
    }

    public static String solution(String[] dicts, String[] words) {
        List<String> answer = new ArrayList<>();
        Set<Character> letterSet = new HashSet<>(Arrays.asList('a', 'e', 'o', 'u', 'i'));
        Set<String> dictSet = Arrays.stream(dicts).collect(Collectors.toCollection(HashSet::new));

        Map<String, String> dictMap = new HashMap<>();
        for (String dictWord :dicts) {
            if (!dictMap.containsKey(dictWord.toLowerCase())) {
                dictMap.put(dictWord.toLowerCase(), dictWord);
            }
        }

        Map<String, String> thirdMap = new HashMap<>();
        for (String wordVowel: dicts) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < wordVowel.length(); i++) {
                if (letterSet.contains(Character.toLowerCase(wordVowel.charAt(i)))) {
                    stringBuilder.append("#");
                } else {
                    stringBuilder.append(Character.toLowerCase(wordVowel.charAt(i)));
                }
            }
            String result = stringBuilder.toString().trim();
            if (!thirdMap.containsKey(result)) {
                thirdMap.put(result, wordVowel);
            }
        }

        for (String word: words) {
            boolean isFind = false;
            if (dictSet.contains(word)) {
                answer.add(word);
                isFind = true;
            } else if (dictMap.containsKey(word.toLowerCase())) {
                answer.add(dictMap.get(word.toLowerCase()));
                isFind = true;
            }

            if (!isFind) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < word.length(); i++) {
                    if (letterSet.contains(Character.toLowerCase(word.charAt(i)))) {
                        stringBuilder.append("#");
                    } else {
                        stringBuilder.append(Character.toLowerCase(word.charAt(i)));
                    }
                }
                word = stringBuilder.toString();
                if (thirdMap.containsKey(word)) {
                    answer.add(thirdMap.get(word));
                    isFind = true;
                }
            }

            if (!isFind) {
                answer.add("");
            }
        }
        return String.join(" ", answer);
    }
}
