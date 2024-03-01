package yandex.lecture._03Set;

/**
 Дан словарь из N слов, длина каждого не превосходит K

 В записи каждого из M слов текста (каждое длиной до K) может быть пропущена
 одна буква. Для каждого слова сказать, входит ли оно (возможно с одной
 пропущенной буквой) в словарь
 */
import java.util.*;
import java.util.stream.Collectors;

public class InDict {

    public List<String> enterWordsAtDict(String dictLine, String textLine) {

        List<String> dict = Arrays.stream(dictLine.split(" ")).collect(Collectors.toList());
        List<String> text = Arrays.stream(textLine.split(" ")).collect(Collectors.toList());
        List<String> result = new ArrayList<>();
        Set<String> goodWords = new HashSet<>(dict);
        for (String dictWord : dict) {
            for (int j = 0; j < dictWord.length(); j++) {
                goodWords.add((dictWord.substring(0, j) + dictWord.substring(j + 1)));
            }
        }
        for (String textWord : text) {
            if (goodWords.contains(textWord)) {
                result.add(textWord);
            }
        }
        return result;
    }
}
