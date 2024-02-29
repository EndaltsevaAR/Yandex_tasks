package yandex.lecture._07;

import yandex.lecture._07.SiteEnterTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SiteEnterTimeTest {

    @Test
    public void test() {
        int n = 4;
        int[] enters = new int[] { 1, 3, 2, 5 };
        int[] outs = new int[] { 9, 6, 3, 7 };
        int result = SiteEnterTime.solution(n, enters, outs);
        assertEquals(result, 8);
    }
}
