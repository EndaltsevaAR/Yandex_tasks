package yandex.lecture._03Set;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class InDictTest {
    private static InDict inDict;

    @BeforeAll
    public static void init() {
        inDict = new InDict();
    }

    @Test
    void normalTest() {
        List<String> result = inDict.enterWordsAtDict("abc def", "abc de");
        List<String> act = new ArrayList<>();
        act.add("abc");
        act.add("de");
        assertEquals(act, result);
    }

    @Test
    void WordNotInDict() {
        List<String> result = inDict.enterWordsAtDict("abc def", "abc ert");
        List<String> act = new ArrayList<>();
        act.add("abc");
        assertEquals(act, result);
    }

    @Test
    void NotOneWordInDict() {
        List<String> result = inDict.enterWordsAtDict("abc def", "ert efdf");
        List<String> act = new ArrayList<>();
        assertEquals(act, result);
    }
}
