package yandex.mysolutions.homework1;

import static org.junit.Assert.*;

import main.my.homework1.Stage1;
import org.junit.Test;

public class Stage1Test {

    @Test
    public void test1() {
        System.out.println("Test from task");
        long result = Stage1.getHeight(5);
        assertEquals(result, 2);
    }

    @Test
    public void test2() {
        System.out.println("Test from task");
        long result = Stage1.getHeight(8);
        assertEquals(result, 3);
    }

    @Test
    public void test3() {
        System.out.println("Test from total steps without extra cubs");
        long result = Stage1.getHeight(6);
        assertEquals(result, 3);
    }

    @Test
    public void test4() {
        System.out.println("Test for 1");
        long result = Stage1.getHeight(1);
        assertEquals(result, 1);
    }

    @Test
    public void test5() {
        System.out.println("Test for max");
        long result = Stage1.getHeight(2147483647);
        assertEquals(result, 65535);
    }
}
