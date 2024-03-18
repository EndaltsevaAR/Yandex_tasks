package algorithm5.homework3;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        String[] line1 = "3 1".split(" ");
        String[] line2 = "1 3".split(" ");
        String[] line3 = "1 2".split(" ");
        List<String[]> lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        assertTrue("1 3".equals(Main.getTotalList(lines)));
    }

    @Test
    void contextTest2() {
        String[] line1 = "1 2 2".split(" ");
        String[] line2 = "3 4 3".split(" ");
        String[] line3 = "5".split(" ");
        List<String[]> lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        assertTrue("".equals(Main.getTotalList(lines)));
    }
}
