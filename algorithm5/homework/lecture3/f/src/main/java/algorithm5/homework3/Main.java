package algorithm5.homework3;

/**
С целью экономии чернил в картридже принтера было принято решение укоротить некоторые слова в тексте. 
Для этого был составлен словарь слов, до которых можно сокращать более длинные слова. 
Слово из текста можно сократить, если в словаре найдется слово, являющееся началом слова из текста. 
Например, если в списке есть слово "лом", то слова из текста "ломбард", "ломоносов" и другие слова, начинающиеся на "лом", можно сократить до "лом".

Если слово из текста можно сократить до нескольких слов из словаря, то следует сокращать его до самого короткого слова.

Формат ввода
В первой строке через пробел вводятся слова из словаря, слова состоят из маленьких латинских букв. 
Гарантируется, что словарь не пуст и количество слов в словаре не превышет 1000, а длина слов — 100 символов.

Во второй строке через пробел вводятся слова текста (они также состоят только из маленьких латинских букв). 
Количество слов в тексте не превосходит 10^5, а суммарное количество букв в них — 10^6.

Формат вывода
Выведите текст, в котором осуществлены замены.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        String dict = scanner.nextLine();
        String words = scanner.nextLine();
        System.out.println(getDictWords(dict, words));
        scanner.close();
    }

    public static String getDictWords(String dict, String words) {
        String[] separDict = dict.split(" ");
        Set<StringBuilder> dictSet = new TreeSet<>();
        for (int i = 0; i < separDict.length; i++) {
            dictSet.add(new StringBuilder(separDict[i]));
        }

        String[] separWords = words.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < separWords.length; i++) {
            StringBuilder wordBuilder = getDictWord(dictSet, separWords[i]);
            if (wordBuilder.length() > 1) {
                builder.append(wordBuilder);
            } else {
                builder.append(separWords[i]).append(" ");
            }
        }
        return builder.toString().trim();
    }

    private static StringBuilder getDictWord(Set<StringBuilder> dictSet, String word) {
        StringBuilder miniWord = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        for (int j = 0; j < word.length(); j++) {
            miniWord.append(word.charAt(j));
            if (dictSet.contains(miniWord)) {
                return answer.append(miniWord).append(" ");
            }
        }
        return answer;
    }
}