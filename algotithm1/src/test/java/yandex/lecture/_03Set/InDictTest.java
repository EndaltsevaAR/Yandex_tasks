package yandex.lecture._03Set;

import static org.junit.Assert.*;

import yandex.lecture._03Set.InDict;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InDictTest {
    @Test
    public void test1() {
        System.out.println("Test from task");
        String dictLine = "abc def";
        List<String> dict = Arrays.stream(dictLine.split(" ")).collect(Collectors.toList());

        String textLine = "abc de";
        List<String> text = Arrays.stream(textLine.split(" ")).collect(Collectors.toList());

        List<String> result = InDict.solution(dict, text);
        List<String> act = new ArrayList<>();
        act.add("abc");
        act.add("de");
        assertEquals(result, act);
    }
}
