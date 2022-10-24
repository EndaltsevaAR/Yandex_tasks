package main.my.homework2;

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
