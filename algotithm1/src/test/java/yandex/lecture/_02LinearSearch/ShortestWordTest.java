package yandex.lecture._02LinearSearch;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class ShortestWordTest {
    private static ShortestWord word;

    @BeforeAll
    public void init() {
        word = new ShortestWord();
    }

    @Test
    void normalLine() {
        assertTrue("a a".equals(word.shortWord("a a bb ccc")));
    }

}
