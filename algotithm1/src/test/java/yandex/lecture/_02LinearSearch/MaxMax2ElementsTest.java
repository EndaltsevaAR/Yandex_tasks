package yandex.lecture._02LinearSearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MaxMax2ElementsTest {
    private static MaxMax2Elements max2Elements;

    @BeforeAll
    public void init() {
        max2Elements = new MaxMax2Elements();
    }

    @Test
    void normalLine() {
        List<Integer> maxs = max2Elements.maxMax("1 2 3 4");
        assertEquals(4, maxs.get(0));
        assertEquals(3, maxs.get(1));
    }

    @Test
    void eqLine() {
        List<Integer> maxs = max2Elements.maxMax("1 2 3 3");
        assertEquals(3, maxs.get(0));
        assertEquals(3, maxs.get(1));
    }

}
