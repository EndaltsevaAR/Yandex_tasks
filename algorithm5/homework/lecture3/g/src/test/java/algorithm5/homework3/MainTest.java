package algorithm5.homework3;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        Point point1 = new Point(0, 1);
        Point point2 = new Point(1, 0);
        Set<Point> set = new HashSet<>();
        set.add(point1);
        set.add(point2);
        assertTrue("2\n1 1\n0 0".equals(Main.finishSquare(set)));
    }

    @Test
    void contextTest2() {
        Point point1 = new Point(0, 2);
        Point point2 = new Point(2, 0);
        Point point3 = new Point(2, 2);
        Set<Point> set = new HashSet<>();
        set.add(point1);
        set.add(point2);
        set.add(point3);
        assertTrue("1\n0 0".equals(Main.finishSquare(set)));
    }

    @Test
    void contextTest3() {
        Point point1 = new Point(-1, 1);
        Point point2 = new Point(1, 1);
        Point point3 = new Point(-1, -1);
        Point point4 = new Point(1, -1);
        Set<Point> set = new HashSet<>();
        set.add(point1);
        set.add(point2);
        set.add(point3);
        set.add(point4);
        assertTrue("0".equals(Main.finishSquare(set)));
    }
}
