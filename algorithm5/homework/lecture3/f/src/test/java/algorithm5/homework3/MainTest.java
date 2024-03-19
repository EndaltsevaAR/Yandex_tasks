package algorithm5.homework3;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        assertTrue("a b casds dsasa a".equals(Main.getDictWords("a b", "abdafb basrt casds dsasa a")));
    }

    @Test
    void contextTest2() {
        assertTrue("a aa aa bc abcd".equals(Main.getDictWords("aa bc aaa", "a aa aaa bcd abcd")));
    }
}
