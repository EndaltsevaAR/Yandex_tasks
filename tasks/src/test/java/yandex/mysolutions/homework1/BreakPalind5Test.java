package yandex.mysolutions.homework1;

import static org.junit.Assert.*;
import main.my.homework1.BreakPalind5;
import org.junit.Test;

public class BreakPalind5Test {
    @Test
    public void test1() {
        System.out.println("Test from task");
        String line = "abba";
        String result = BreakPalind5.getAnotherWord(line);
        assertEquals(result, "aaba");
    }

    @Test
    public void test2() {
        System.out.println("Test from task");
        String line = "a";
        String result = BreakPalind5.getAnotherWord(line);
        assertEquals(result, "");
    }

    @Test
    public void test3() {
        System.out.println("Test for first symbol");
        String line = "baab";
        String result = BreakPalind5.getAnotherWord(line);
        assertEquals(result, "aaab");
    }

    @Test
    public void test4() {
        System.out.println("Test for a symbol");
        String line = "aaaa";
        String result = BreakPalind5.getAnotherWord(line);
        assertEquals(result, "aaab");
    }

    @Test
    public void test5() {
        System.out.println("Test for one symbol");
        String line = "b";
        String result = BreakPalind5.getAnotherWord(line);
        assertEquals(result, "");
    }

    @Test
    public void test6() {
        System.out.println("Test for empty string");
        String line = "";
        String result = BreakPalind5.getAnotherWord(line);
        assertEquals(result, "");
    }

    @Test
    public void test7() {
        System.out.println("Test for big string");
        String line = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String result = BreakPalind5.getAnotherWord(line);
        assertEquals(result, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test
    public void test8() {
        System.out.println("Test for z");
        String line = "zzzzzzzzzzaazzzzzzzzzz";
        String result = BreakPalind5.getAnotherWord(line);
        assertEquals(result, "azzzzzzzzzaazzzzzzzzzz");
    }

    @Test
    public void test9() {
        System.out.println("Test for odd lenght");
        String line = "aabaa";
        String result = BreakPalind5.getAnotherWord(line);
        assertEquals(result, "aabab");
    }
}
