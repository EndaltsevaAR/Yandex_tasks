package algorithm5.homework3;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void ContextTest1() {
        List<String> lines = new ArrayList<>();
        lines.add("\"Juventus\" - \"Milan\" 3:1");
        lines.add("Inzaghi 45'");
        lines.add("Del Piero 67'");
        lines.add("Del Piero 90'");
        lines.add("Shevchenko 34'");
        lines.add("Total goals for \"Juventus\"");
        lines.add("Total goals by Pagliuca");
        lines.add("Mean goals per game by Inzaghi");
        lines.add("\"Juventus\" - \"Lazio\" 0:0");
        lines.add("Mean goals per game by Inzaghi");
        lines.add("Mean goals per game by Shevchenko");
        lines.add("Score opens by Inzaghi");
        assertTrue("3\n0\n1.0\n0.5\n1.0\n0".equals(Main.getStatistics(lines)));
    }

    @Test
    void ContextTest2() {
        List<String> lines = new ArrayList<>();
        lines.add("Total goals by Arshavin");
        assertTrue("0".equals(Main.getStatistics(lines)));
    }

    @Test
    void ContextTest3() {
        List<String> lines = new ArrayList<>();
        lines.add("\"First team\" - \"Second team\" 3:4");
        lines.add("Player A 87'");
        lines.add("Player B 88'");
        lines.add("Player A 89'");
        lines.add("Player AA 10'");
        lines.add("Player CC 20'");
        lines.add("Player CC 77'");
        lines.add("Player CC 90'");
        lines.add("Total goals for \"First team\"");
        lines.add("Total goals for \"Second team\"");
        lines.add("Total goals for \"Third team\"");
        lines.add("Total goals for \"Player A\"");
        lines.add("Mean goals per game for \"First team\"");
        lines.add("Mean goals per game for \"Second team\"");
        lines.add("Total goals by First team");
        lines.add("Total goals by Player A");
        lines.add("Total goals by Player B");
        lines.add("Total goals by Player C");
        lines.add("Total goals by Player AA");
        lines.add("Total goals by Player BB");
        lines.add("Total goals by Player CC");
        lines.add("Mean goals per game by Player A");
        lines.add("Mean goals per game by Player B");
        lines.add("Mean goals per game by Player AA");
        lines.add("Mean goals per game by Player CC");
        lines.add("Goals on minute 86 by Player A");
        lines.add("Goals on minute 87 by Player A");
        lines.add("Goals on minute 88 by Player A");
        lines.add("Goals on minute 1 by Player Z");
        lines.add("Goals on first 1 minutes by Player A");
        lines.add("Goals on first 10 minutes by Player A");
        lines.add("Goals on first 86 minutes by Player A");
        lines.add("Goals on first 87 minutes by Player A");
        lines.add("Goals on first 88 minutes by Player A");
        lines.add("Goals on first 89 minutes by Player A");
        lines.add("Goals on first 90 minutes by Player A");
        lines.add("Goals on first 90 minutes by Player Z");
        lines.add("Goals on last 1 minutes by Player A");
        lines.add("Goals on last 2 minutes by Player A");
        lines.add("Goals on last 3 minutes by Player A");
        lines.add("Goals on last 4 minutes by Player A");
        lines.add("Goals on last 5 minutes by Player A");
        lines.add("Goals on last 55 minutes by Player Z");
        lines.add("Score opens by \"First team\"");
        lines.add("Score opens by \"Second team\"");
        lines.add("Score opens by First team");
        lines.add("Score opens by Second team");
        lines.add("Score opens by Player A");
        lines.add("Score opens by Player B");
        lines.add("Score opens by Player C");
        lines.add("Score opens by Player AA");
        lines.add("Score opens by Player BB");
        lines.add("Score opens by Player CC");
        assertTrue(
                "3\n4\n0\n0\n3.0\n4.0\n0\n2\n1\n0\n1\n0\n3\n2.0\n1.0\n1.0\n3.0\n0\n1\n0\n0\n0\n0\n0\n1\n1\n2\n2\n0\n0\n1\n1\n2\n2\n0\n0\n1\n0\n0\n0\n0\n0\n1\n0\n0"
                        .equals(Main.getStatistics(lines)));
    }

    @Test
    void ContextTest5() {
        List<String> lines = new ArrayList<>();
        lines.add("\"q\" - \"w\" 0:0");
        assertTrue("".equals(Main.getStatistics(lines)));
    }
}
