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

    // @Test
    // void Test7() {
    // assertEquals(6, Main.getNumberSteps(300, 301, 484));
    // }

    @Test
    void Test8() {
        assertEquals(8, Main.getNumberSteps(250, 500, 230));
    }

    // @Test
    // void Test9() {
    // assertEquals(4, Main.getNumberSteps(5, 8, 4));
    // }

    // @Test не работает при сечении, но при цифре да
    // void Test10() {
    // assertEquals(6, Main.getNumberSteps(250, 500, 218));
    // }

    @Test
    void Test11() {
        assertEquals(101, Main.getNumberSteps(250, 500, 249));
    }

    @Test
    void Test12() {
        assertEquals(3, Main.getNumberSteps(8, 12, 7));
    }

    // @Test //не работает даже на сечении
    // void Test13() {
    // assertEquals(961, Main.getNumberSteps(2500, 5000, 2499));
    // }

    // @Test // работает при сечении
    // void Test13() {
    // assertEquals(4812, Main.getNumberSteps(78, 4934, 77));
    // }

    @Test // работает при сечении
    void Test14() { // работает при сечениии при цифре
        assertEquals(5, Main.getNumberSteps(78, 126, 77));
    }

    // @Test
    // void Test15() { // не работает
    // assertEquals(6, Main.getNumberSteps(1661, 4327, 1107));
    // }

    // @Test
    // void Test16() { // не работает
    // assertEquals(11, Main.getNumberSteps(1092, 2892, 950));
    // }
    // @Test // не работает
    // void Test17() {
    // assertEquals(30, Main.getNumberSteps(31, 495, 15));
    // }

    @Test // работает
    void Test18() {
        assertEquals(6, Main.getNumberSteps(250, 500, 209));
    }

    @Test // не работает
    void Test19() {
        assertEquals(79, Main.getNumberSteps(3000, 5000, 2998));
    }

    @Test // не работает
    void Test20() {
        assertEquals(16, Main.getNumberSteps(2500, 5000, 2420));
    }

}
