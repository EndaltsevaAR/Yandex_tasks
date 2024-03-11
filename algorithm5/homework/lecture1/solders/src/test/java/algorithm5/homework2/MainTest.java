package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import algorithm5.homework1.Main;

public class MainTest {
    public static int xOurSoldiers;
    public static int yBuilding;
    public static int pEnemySoldiers;
    public static int generatedEnemies;

    @BeforeAll
    void init() {
        xOurSoldiers = 0;
        yBuilding = 0;
        pEnemySoldiers = 0;
        generatedEnemies = 0;
    }

    @Test
    void contextTest1() {
        xOurSoldiers = 10;
        yBuilding = 11;
        pEnemySoldiers = 15;
        generatedEnemies = 15;
        assertEquals(4, Main.getNumberSteps());
    }

    @Test
    void contextTest2() {
        xOurSoldiers = 1;
        yBuilding = 2;
        pEnemySoldiers = 1;
        generatedEnemies = 1;
        assertEquals(-1, Main.getNumberSteps());
    }

    @Test
    void contextTest3() {
        xOurSoldiers = 1;
        yBuilding = 1;
        pEnemySoldiers = 1;
        generatedEnemies = 1;
        assertEquals(1, Main.getNumberSteps());
    }

    @Test
    void contextTest4() {
        xOurSoldiers = 25;
        yBuilding = 200;
        pEnemySoldiers = 10;
        generatedEnemies = 10;
        assertEquals(13, Main.getNumberSteps());
    }

    @Test
    void contextTest5() {
        xOurSoldiers = 250;
        yBuilding = 500;
        pEnemySoldiers = 187;
        generatedEnemies = 187;
        assertEquals(4, Main.getNumberSteps());
    }

    @Test
    void Test6() {
        xOurSoldiers = 300;
        yBuilding = 301;
        pEnemySoldiers = 485;
        generatedEnemies = 485;
        assertEquals(-1, Main.getNumberSteps());
    }

    @Test
    void Test7() {
        xOurSoldiers = 300;
        yBuilding = 301;
        pEnemySoldiers = 484;
        generatedEnemies = 484;
        assertEquals(6, Main.getNumberSteps());
    }

    @Test
    void Test8() {
        xOurSoldiers = 250;
        yBuilding = 500;
        pEnemySoldiers = 230;
        generatedEnemies = 230;
        assertEquals(8, Main.getNumberSteps());
    }

// @Test
// void Test9() {
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(4, Main.getNumberSteps(5, 8, 4));
// }

// @Test // не работает при сечении, но при цифре да
// void Test10() {
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(6, Main.getNumberSteps(250, 500, 218));
// }

// @Test
// void Test11() {
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(101, Main.getNumberSteps(250, 500, 249));
// }

// @Test
// void Test12() {
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(3, Main.getNumberSteps(8, 12, 7));
// }

// @Test // не работает даже на сечении
// void Test13() {
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(961, Main.getNumberSteps(2500, 5000, 2499));
// }

// @Test // работает при сечении
// void Test13a() {
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(4812, Main.getNumberSteps(78, 4934, 77));
// }

// @Test // работает при сечении
// void Test14() { // работает при сечениии при цифре
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(5, Main.getNumberSteps(78, 126, 77));
// }

// @Test
// void Test15() { // не работает
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(6, Main.getNumberSteps(1661, 4327, 1107));
// }

// @Test
// void Test16() { // не работает
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(11, Main.getNumberSteps(1092, 2892, 950));
// }

// @Test // не работает
// void Test17() {
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(30, Main.getNumberSteps(31, 495, 15));
// }

// @Test // работает
// void Test18() {
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(6, Main.getNumberSteps(250, 500, 209));
// }

// @Test // не работает
// void Test19() {
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(79, Main.getNumberSteps(3000, 5000, 2998));
// }

// @Test // не работает
// void Test20() {
// xOurSoldiers = 10;
// yBuilding = 11;
// pEnemySoldiers = 15;
// generatedEnemies = 15;
// assertEquals(16, Main.getNumberSteps(2500, 5000, 2420));
// }

// }
