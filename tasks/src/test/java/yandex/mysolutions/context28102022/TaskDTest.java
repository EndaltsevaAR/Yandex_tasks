package yandex.mysolutions.context28102022;

import main.my.contect28102022.TaskD;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TaskDTest {

    @Test
    public void test() {
        System.out.println("Test 1 from task");
        List<Set<String>> linesSet = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        int n = 5;
        lines.add("rom");
        lines.add("bom");
        lines.add("dom");
        lines.add("bot");
        lines.add("rot");
        for (int i = 0; i < n; i++) {
            Set<String> set = new HashSet<>();
            String line = lines.get(i);
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
        int result = TaskD.solution(n, linesSet, lines);
        assertEquals(result, 6);
    }
    /*
     * @Test
     * public void test2() {
     * System.out.println("Test 2 from task");
     * int n = 3;
     * String[] lines = new String[n];
     * lines[0] = "aa";
     * lines[1] = "aa";
     * lines[2] = "aa";
     * int result = TaskD.solution(n, lines);
     * assertEquals(result, 0);
     * }
     * 
     * @Test
     * public void test3() {
     * System.out.println("Test 3 from task");
     * int n = 6;
     * String[] lines = new String[n];
     * lines[0] = "aaa";
     * lines[1] = "aaB";
     * lines[2] = "aBa";
     * lines[3] = "Baa";
     * lines[4] = "BBB";
     * lines[5] = "abb";
     * int result = TaskD.solution(n, lines);
     * assertEquals(result, 3);
     * }
     * 
     * @Test
     * public void test4() {
     * System.out.println("My test");
     * int n = 2;
     * String[] lines = new String[n];
     * lines[0] = "aba";
     * lines[1] = "aaa";
     * int result = TaskD.solution(n, lines);
     * assertEquals(result, 1);
     * }
     * 
     * @Test
     * public void test5() {
     * System.out.println("1 test");
     * int n = 1;
     * String[] lines = new String[n];
     * lines[0] = "aba";
     * int result = TaskD.solution(n, lines);
     * assertEquals(result, 0);
     * }
     * 
     * @Test
     * public void test6() {
     * System.out.println("1 letter");
     * int n = 3;
     * String[] lines = new String[n];
     * lines[0] = "a";
     * lines[1] = "A";
     * lines[2] = "b";
     * int result = TaskD.solution(n, lines);
     * assertEquals(result, 3);
     * }
     * 
     */
}
