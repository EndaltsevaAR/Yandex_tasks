package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void contextTest1() {
        assertTrue("Yes\n1.0000000000".equals(Main.isBoyMeetings(6, 3, 1, 1, 1)));
    }
}
