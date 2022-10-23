package test.lecture._04Tables;
import static org.junit.Assert.*;

import main.lecture._04Tables.CompareNumbers;
import org.junit.Test;

public class CompareNumbersTest {
    @Test
    public void test1() {
        System.out.println("Good story");
        int x = 123;
        int y = 321;
        boolean result = CompareNumbers.solution(x, y);
        assertTrue(result);
    }

    @Test
    public void test2() {
        System.out.println("Bad story");
        int x = 1234;
        int y = 1235;
        boolean result = CompareNumbers.solution(x, y);
        assertFalse(result);
    }
}
