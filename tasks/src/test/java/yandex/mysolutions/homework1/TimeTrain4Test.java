package yandex.mysolutions.homework1;

import static org.junit.Assert.*;

import main.my.homework1.TimeTrain4;
import org.junit.Test;

public class TimeTrain4Test {
    @Test
    public void test1() {
        System.out.println("Test from task");
        // int number = 2;
        String line = "23:59 00:00";
        int result = TimeTrain4.getMinDiffTime(line);
        assertEquals(result, 1);
    }

    @Test
    public void test2() {
        System.out.println("Test from task");
        // int number = 3;
        String line = "00:00 23:59 00:00";
        int result = TimeTrain4.getMinDiffTime(line);
        assertEquals(result, 0);
    }

    @Test
    public void test3() {
        System.out.println("Extra test");
        // int number = 3;
        String line = "00:02 23:58 00:07";
        int result = TimeTrain4.getMinDiffTime(line);
        assertEquals(result, 4);
    }
}
