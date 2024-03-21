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

    @Test
    void contextTest6() {
        Point point1 = new Point(8, 6);
        Point point2 = new Point(-9, 6);
        Point point3 = new Point(-4, 1);
        Point point4 = new Point(-5, 3);
        Point point5 = new Point(6, 4);
        Point point6 = new Point(7, -2);
        Point point7 = new Point(9, 2);
        Point point8 = new Point(9, 8);
        Point point9 = new Point(8, 10);
        Point point10 = new Point(-7, -2);
        Point point11 = new Point(-5, -6);
        Point point12 = new Point(1, 7);
        Set<Point> set = new HashSet<>();
        set.add(point1);
        set.add(point2);
        set.add(point3);
        set.add(point4);
        set.add(point5);
        set.add(point6);
        set.add(point7);
        set.add(point8);
        set.add(point9);
        set.add(point10);
        set.add(point11);
        set.add(point12);
        assertTrue("1\n3 10".equals(Main.finishSquare(set)));
        // 12
        // 8 6
        // -9 6
        // -4 1
        // -5 3
        // 6 4
        // 7 -2
        // 9 2
        // 9 8
        // 8 10
        // -7 -2
        // -5 -6
        // 1 7
    }
}
