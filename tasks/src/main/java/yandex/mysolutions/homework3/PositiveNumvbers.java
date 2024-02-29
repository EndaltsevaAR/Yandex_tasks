package main.my.homework3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PositiveNumvbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = Integer.parseInt(scanner.nextLine());
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        int numberQueries = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> queries = new ArrayList<>();
        for (int i = 0; i < numberQueries; i++) {
//            List<Integer> query = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            List<Integer> query = new ArrayList<>();
            query.add(scanner.nextInt());
            query.add(scanner.nextInt());
            queries.add(query);
        }
        List<Integer> answer = findPositiveCounters(numbers, queries);
        for (Integer integer :answer) {
            System.out.println(integer);
        }

    }

    public static List<Integer> findPositiveCounters(List<Integer> numbers, List<List<Integer>> queries) {
        List<Integer> prefix = new ArrayList<>();
        prefix.add(0);
        for (int i = 1; i < numbers.size() + 1; i++) {
            if (numbers.get(i - 1) > 0) {
                prefix.add(prefix.get(i - 1) + 1);
            } else {
                prefix.add(prefix.get(i - 1));
            }
        }
        List<Integer> result = new ArrayList<>();
        for (List<Integer> query : queries) {
            result.add(prefix.get(query.get(1)) - prefix.get(query.get(0) - 1));
        }
        return result;
    }
}
