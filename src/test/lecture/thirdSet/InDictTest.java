package test.lecture.thirdSet;

import static org.junit.Assert.*;

import main.lecture.thirdSet.InDict;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InDictTest {
    @Test
    public void test1() {
        System.out.println("Test from task");
        String dictLine = "abc def";
        List<String> dict = Arrays.stream(dictLine.split(" ")).toList();

        String textLine = "abc de";
        List<String> text = Arrays.stream(textLine.split(" ")).toList();

        List<String> result = InDict.solution(dict, text);
        List<String> act = new ArrayList<>();
        act.add("abc");
        act.add("de");
        assertEquals(result, act);
    }
}
