package algorithm5.homework3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        Set<Lighter> lightersBefore = new HashSet<>(5);
        Set<Lighter> lightersAfter = new HashSet<>(5);
        lightersBefore.add(Main.getLighter("0 0 1 2"));
        lightersBefore.add(Main.getLighter("1 0 0 2"));
        lightersBefore.add(Main.getLighter("2 0 2 2"));
        lightersBefore.add(Main.getLighter("4 0 3 2"));
        lightersBefore.add(Main.getLighter("4 0 5 2"));
        lightersAfter.add(Main.getLighter("9 -1 10 1"));
        lightersAfter.add(Main.getLighter("10 1 9 3"));
        lightersAfter.add(Main.getLighter("8 1 10 1"));
        lightersAfter.add(Main.getLighter("8 1 9 -1"));
        lightersAfter.add(Main.getLighter("8 1 9 3"));
        assertEquals(3, Main.getNumberMoves(lightersBefore, lightersAfter));
    }

    @Test
    void contextTest2() {
        Set<Lighter> lightersBefore = new HashSet<>(1);
        Set<Lighter> lightersAfter = new HashSet<>(1);
        lightersBefore.add(Main.getLighter("3 4 7 9"));
        lightersAfter.add(Main.getLighter("-1 3 3 8"));
        assertEquals(0, Main.getNumberMoves(lightersBefore, lightersAfter));
    }

    @Test
    void contextTest3() {
        Set<Lighter> lightersBefore = new HashSet<>(1);
        Set<Lighter> lightersAfter = new HashSet<>(1);
        lightersBefore.add(Main.getLighter("-4 5 2 -3"));
        lightersAfter.add(Main.getLighter("-12 4 -2 4"));
        assertEquals(1, Main.getNumberMoves(lightersBefore, lightersAfter));
    }

}
