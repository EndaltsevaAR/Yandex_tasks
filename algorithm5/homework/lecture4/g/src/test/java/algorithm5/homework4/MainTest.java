package algorithm5.homework4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        String input = "...##.###...\n...##.###...\n.########...\n.###########\n...#########\n...#########\n......###...\n......###...\n......###...";
        String[] lines = input.split("\n");
        int rows = lines.length;
        int cols = lines[0].length();
        char[][] charArray = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            charArray[i] = lines[i].toCharArray();
        }
        assertEquals(3, Main.getScale(9, 12, charArray));
    }

    @Test
    void contextTest2() {
        String input = ".##...\n.##...\n######\n######\n.##...\n.##...";
        String[] lines = input.split("\n");
        int rows = lines.length;
        int cols = lines[0].length();
        char[][] charArray = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            charArray[i] = lines[i].toCharArray();
        }
        assertEquals(1, Main.getScale(9, 12, charArray));
    }
}
