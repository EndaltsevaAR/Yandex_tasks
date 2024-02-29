package yandex.lecture._02LinearSearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PitCraftTest {
    private static PitCraft pitCraft;

    @BeforeAll
    public static void init() {
        pitCraft = new PitCraft();
    }

    @Test
    void normalPit() {
        assertEquals(7, pitCraft.rain("3 1 4 3 5 1 1 3 1"));
    }

}
