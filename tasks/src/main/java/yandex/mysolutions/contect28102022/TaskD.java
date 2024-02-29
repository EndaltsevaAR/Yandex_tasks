package main.my.contect28102022;
import java.util.*;

public class TaskD {

    public static void main(String[] args) {
        List<Set<String>> linesSet = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Set<String> set = new HashSet<>();
            String line = scanner.nextLine();
            lines.add(line);
            for (int j = 0; j < line.length(); j++) {
                for (char k = 'a'; k < 'z'; k++) {
                    if (k != line.charAt(j)) {
                        set.add(line.substring(0, j) + k + line.substring(j + 1));
                    }
                }
                for (char k = 'A'; k < 'Z'; k++) {
                    if (k != line.charAt(j)) {
                        set.add(line.substring(0, j) + k + line.substring(j + 1));
                    }
                }
            }
            linesSet.add(set);
        }
        System.out.println(solution(n, linesSet, lines));
        scanner.close();
    }

    public static int solution(int n, List<Set<String>> linesSet, List<String> lines) {
        int answer = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (linesSet.get(i).contains(lines.get(j))) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
