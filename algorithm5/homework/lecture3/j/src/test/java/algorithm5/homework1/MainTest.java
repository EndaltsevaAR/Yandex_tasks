package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        assertTrue("3 3".equals(Main.getNumberSteps(3, 2)));
    }
}
