package test.lecture._06BinarySearch;
import main.lecture._06BinarySearch.School;
import org.junit.Test;
import static org.junit.Assert.*;

public class SchoolTest {
    @Test
    public void test() {
        int number = 9;
        int parents = 1;
        int result = School.leftSearch(number, parents);
        int answer = 3;
        assertEquals(result, answer);
    }
}
