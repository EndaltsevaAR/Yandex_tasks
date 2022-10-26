package test.lecture._06BinarySearch;
import main.lecture._06BinarySearch.School;
import org.junit.Test;
import static org.junit.Assert.*;

public class InterviewTest {
    @Test
    public void test() {
        int number = 9;
        int tasks = 1;
        int result = School.leftSearch(number, tasks);
        int answer = 3;
        assertEquals(result, answer);
    }
}
