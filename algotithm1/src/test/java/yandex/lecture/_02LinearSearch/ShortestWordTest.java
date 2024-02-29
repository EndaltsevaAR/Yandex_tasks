package yandex.lecture._02LinearSearch;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class ShortestWordTest {
    private static ShortestWord word;

    @BeforeAll
    public static void init() {
        word = new ShortestWord();
    }

    @Test
    void normalLine() {
        assertTrue("a a".equals(word.shortWord("a a bb ccc")));
    }

}
