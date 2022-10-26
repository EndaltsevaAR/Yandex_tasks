package test.my.homework3;
import main.my.homework3.Anagram;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnagramTest {
    @Test
    public void test() {
        System.out.println("Test 1 from task");
        String line1 = "dusty";
        String line2 = "study";
        String result = Anagram.solution(line1, line2);
        String answer = "YES";
        assertEquals(result, answer);
    }

    @Test
    public void test2() {
        System.out.println("Test 2 from task");
        String line1 = "rat";
        String line2 = "bat";
        String result = Anagram.solution(line1, line2);
        String answer = "NO";
        assertEquals(result, answer);
    }
}
