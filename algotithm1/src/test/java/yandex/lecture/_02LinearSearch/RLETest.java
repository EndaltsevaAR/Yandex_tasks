package yandex.lecture._02LinearSearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RLETest {
    private static RLE rle;

    @BeforeAll
    public void init() {
        rle = new RLE();
    }

    @Test
    void normalLine() {
        assertTrue("a2b3c4".equals(rle.rle("aabbbcccc")));
    }

}
