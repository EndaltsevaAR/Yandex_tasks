package yandex.mysolutions.rome;

import static org.junit.Assert.*;
import main.my.rome.AddSub2;
import main.my.rome.Excel3;
import org.junit.Test;

public class Excel3Test {
    @Test
    public void test() {
        System.out.println("Test 1 from task");
        int number = 3;
        String[] lines = new String[number];
        lines[0] = "2 C2+C2".trim();
        lines[1] = "2 C1+C1".trim();
        lines[2] = "1 3".trim();
        int[] result = Excel3.solution(lines);
        int[] answer = new int[] { -1 };
        assertArrayEquals(result, answer);
    }
}
