package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import algorithm5.homework1.Main;

public class MainTest {
    @Test
    void contextTest1() {
        assertEquals(4, Main.getNumberSteps(10, 11, 15));
    }

    @Test
    void contextTest2() {
        assertEquals(-1, Main.getNumberSteps(1, 2, 1));
    }

    @Test
    void contextTest3() {
        assertEquals(1, Main.getNumberSteps(1, 1, 1));
    }

    @Test
    void contextTest4() {
        assertEquals(13, Main.getNumberSteps(25, 200, 10));
    }

    @Test
    void contextTest5() {
        assertEquals(4, Main.getNumberSteps(250, 500, 187));
    }

    @Test
    void Test6() {
        assertEquals(-1, Main.getNumberSteps(300, 301, 485));
    }

    @Test
    void Test7() {
        assertEquals(6, Main.getNumberSteps(300, 301, 484));
    }

    @Test
    void Test8() {
        assertEquals(8, Main.getNumberSteps(250, 500, 230));
    }

    @Test
    void Test9() {
        assertEquals(4, Main.getNumberSteps(5, 8, 4));
    }

}
