package yandex.mysolutions.homework1;

import static org.junit.Assert.*;
import main.my.homework1.Path2;
import org.junit.Test;

public class Path2Test {

    @Test
    public void test1() {
        System.out.println("Test from task");
        String result = Path2.getPath("/home/");
        assertEquals(result, "/home");
    }

    @Test
    public void test2() {
        System.out.println("Test from task");
        String result = Path2.getPath("/../");
        assertEquals(result, "/");
    }

    @Test
    public void test3() {
        System.out.println("Test from task");
        String result = Path2.getPath("/home//foo");
        assertEquals(result, "/home/foo");
    }

    @Test
    public void test4() {
        System.out.println("Test from /./");
        String result = Path2.getPath("/./");
        assertEquals(result, "/");
    }

    @Test
    public void test5() {
        System.out.println("Test from /.");
        String result = Path2.getPath("/.");
        assertEquals(result, "/");
    }

    @Test
    public void test6() {
        System.out.println("Test from empty path");
        String result = Path2.getPath("");
        assertEquals(result, "/");
    }

    @Test
    public void test7() {
        System.out.println("Test from normal path");
        String result = Path2.getPath("/abc/def/tyuuu");
        assertEquals(result, "/abc/def/tyuuu");
    }

    @Test
    public void test8() {
        System.out.println("Test for just //");
        String result = Path2.getPath("//");
        assertEquals(result, "/");
    }

    @Test
    public void test9() {
        System.out.println("Test for //");
        String result = Path2.getPath("//abcd");
        assertEquals(result, "/abcd");
    }

    @Test
    public void test10() {
        System.out.println("Test for multi parent path");
        String result = Path2.getPath("/../../../");
        assertEquals(result, "/");
    }
}
