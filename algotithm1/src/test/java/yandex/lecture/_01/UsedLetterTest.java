package yandex.lecture._01;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

public class UsedLetterTest {
    private static UsedLetter usedLetter;

    @BeforeAll
    public static void init() {
        usedLetter = new UsedLetter();
    }

    @ParameterizedTest
    @ValueSource(strings = { "aasd", "aaassd, aaa123", "123aaa" })
    void DiffirentNumbersOfCountlLetter(String line) {
        assertEquals('a', usedLetter.findMostUsedLetter(line));
    }

    @Test
    void OnelLetter() {
        assertEquals('a', usedLetter.findMostUsedLetter("a"));
    }

    @Test
    void EmptyString() {
        try {
            assertNull(usedLetter.findMostUsedLetter(""));
        } catch (NullPointerException e) {
            System.err.println("Null is");
        }
    }

    @Test
    void NullString() {
        try {
            assertNull(usedLetter.findMostUsedLetter(null));
        } catch (NullPointerException e) {
            System.err.println("Null is");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "asd", "aszxd, a123" })
    void EquualNumbersOfCountlLetter(String line) {
        assertEquals('a', usedLetter.findMostUsedLetter(line));
    }

}
