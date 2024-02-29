package yandex.lecture._03Set;

import java.util.*;

public class InDict {
    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // String dictLine = scanner.nextLine();
    // List<String> dict = Arrays.stream(dictLine.split(" ")).toList();

    // String textLine = scanner.nextLine();
    // List<String> text = Arrays.stream(textLine.split(" ")).toList();

    // scanner.close();
    // System.out.println(solution(dict, text));
    // }

    public static List<String> solution(List<String> dict, List<String> text) {
        List<String> result = new ArrayList<>();
        Set<String> goodWords = new HashSet<>(dict);
        for (String s : dict) {
            for (int j = 0; j < s.length(); j++) {
                goodWords.add((s.substring(0, j) + s.substring(j + 1)));
            }
        }
        for (String s : text) {
            if (goodWords.contains(s)) {
                result.add(s);
            }
        }
        return result;
    }
}
