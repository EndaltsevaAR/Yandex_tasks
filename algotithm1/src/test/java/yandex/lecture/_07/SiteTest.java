package yandex.lecture._07;

import yandex.lecture._07.Site;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SiteTest {
    @Test
    void test() {
        int n = 4;
        int[] enters = new int[] { 1, 3, 2, 5 };
        int[] outs = new int[] { 9, 6, 3, 7 };
        int result = Site.solution(n, enters, outs);
        assertEquals(result, 3);
    }
}
