package test.my.homework2;

import main.my.homework2.Rejection5;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Rejection5Test {
    @Test
    public void test() {
        System.out.println("Test 1 from task");
        int len1 = 4;
        String line = "LiTe lite bare Bare";
        String[] dict = line.split(" ");
        System.out.println("dict is " + Arrays.toString(dict));
        int len2 = 10;
        String text = "leti leet leto Bare BARE Bear bear lite Lite LiTe";
        String[] words = text.split(" ");
        System.out.println("text is " + Arrays.toString(words));
        String answer = Rejection5.solution(dict, words);
        System.out.println("answer is " + answer);
        String result = "LiTe  LiTe Bare bare   lite LiTe LiTe";
        assertEquals(result, answer);
    }

    @Test
    public void test2() {
        System.out.println("Test 2 from task");

        int len1 = 1;
        String line = "DeLay";
        String[] dict = line.split(" ");
        System.out.println("dict is " + Arrays.toString(dict));
        int len2 = 1;
        String text = "delOy";
        String[] words = text.split(" ");
        System.out.println("text is " + Arrays.toString(words));
        String answer = Rejection5.solution(dict, words);
        String result = "DeLay";
        assertEquals(result, answer);
    }

    @Test
    public void test3() {
        System.out.println("Extra test");

        int len1 = 2;
        String line = "delay DeLay";
        String[] dict = line.split(" ");
        System.out.println("dict is " + Arrays.toString(dict));
        int len2 = 1;
        String text = "DeLaY";
        String[] words = text.split(" ");
        System.out.println("text is " + Arrays.toString(words));
        String answer = Rejection5.solution(dict, words);
        System.out.println("answer is " + answer);
        String result = "delay";
        assertEquals(result, answer);
    }

    @Test
    public void test4() {
        System.out.println("Empty test");

        int len1 = 2;
        String line = "delay DeLay";
        String[] dict = line.split(" ");
        System.out.println("dict is " + Arrays.toString(dict));
        int len2 = 1;
        String text = "bam";
        String[] words = text.split(" ");
        System.out.println("text is " + Arrays.toString(words));
        String answer = Rejection5.solution(dict, words);
        System.out.println("answer is " + answer);
        String result = "";
        assertEquals(result, answer);
    }
}
