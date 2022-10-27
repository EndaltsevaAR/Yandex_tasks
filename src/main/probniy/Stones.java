package main.probniy;

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
