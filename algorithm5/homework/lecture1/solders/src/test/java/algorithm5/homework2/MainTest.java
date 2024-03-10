package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import algorithm5.homework1.Main;

public class MainTest {
    @Test
    void contextTest1() {
        assertEquals(4, Main.getNumberSteps(10, 11, 15));
    }

}
