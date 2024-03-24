package algorithm5.homework4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        long[] a = Arrays.stream("2 2 2 3 2 2".split(" ")).mapToLong(Long::parseLong).toArray();
        long[] b = Arrays.stream("3 3 5 2 4 3".split(" ")).mapToLong(Long::parseLong).toArray();
        assertEquals(3, Main.getMinHeight(15, 3, 5, a, b));
    }

    @Test
    void contextTest2() {
        long[] a = Arrays.stream("1 1 2 1 2".split(" ")).mapToLong(Long::parseLong).toArray();
        long[] b = Arrays.stream("7 7 1 5 5".split(" ")).mapToLong(Long::parseLong).toArray();
        assertEquals(4, Main.getMinHeight(10, 2, 7, a, b));
    }

}
