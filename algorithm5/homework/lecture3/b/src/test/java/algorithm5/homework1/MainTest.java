package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import algorithm5.homework3.Main;

public class MainTest {
    @Test
    void contextTest1() {
        assertTrue("YES".equals(Main.isAnagram("dusty", "study")));
    }

    @Test
    void contextTest2() {
        assertTrue("NO".equals(Main.isAnagram("rat", "bat")));
    }

    @Test
    void contextTest10() {
        assertTrue("NO".equals(Main.isAnagram(
                "zirkanrlepcmvyjbpgpizexomgzmymouadzywuitkhicnqtrszvrwukcvoknymyiqfdvkdubisfvzidwplywyzjssymazynkubqv",
                "lcegwwmrebgvtbggnztiaayfatbpiphwwwlkhdahnsxnafiglugnukuxhjxguywtizxbsfqjedtzovtyxbhyzzhzmyrzeydpfosv")));
    }
}
