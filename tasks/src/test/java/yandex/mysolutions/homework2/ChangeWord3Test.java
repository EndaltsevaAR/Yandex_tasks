package yandex.mysolutions.homework2;

import main.my.homework2.ChangeWord3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangeWord3Test {
    @Test
    public void test() {
        System.out.println("Test 1 from task");
        String line = "a b";
        String[] dict = line.split(" ");
        String text = "abdafb basrt casds dsasa a";
        String[] words = text.split(" ");
        String answer = ChangeWord3.solution(dict, words);
        String result = "a b casds dsasa a";
        assertEquals(result, answer);
    }

    @Test
    public void test2() {
        System.out.println("Test 2 from task");
        String line = "aa bc aaa";
        String[] dict = line.split(" ");
        String text = "a aa aaa bcd abcd";
        String[] words = text.split(" ");
        String answer = ChangeWord3.solution(dict, words);
        String result = "a aa aa bc abcd";
        assertEquals(result, answer);
    }

}
