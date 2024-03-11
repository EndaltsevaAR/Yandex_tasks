package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import algorithm5.homework1.Main;

public class MainTest {

    @Test
    void contextTest1() {
        Main.xOurSoldiers = 10;
        Main.yBuilding = 11;
        Main.pEnemySoldiers = 15;
        Main.generatedEnemies = 15;
        Main.reservedCount = 0;
        assertEquals(4, Main.getNumberSteps());
    }

    @Test
    void contextTest2() {
        Main.xOurSoldiers = 1;
        Main.yBuilding = 2;
        Main.pEnemySoldiers = 1;
        Main.generatedEnemies = 1;
        Main.reservedCount = 0;
        assertEquals(-1, Main.getNumberSteps());
    }

    @Test
    void contextTest3() {
        Main.xOurSoldiers = 1;
        Main.yBuilding = 1;
        Main.pEnemySoldiers = 1;
        Main.generatedEnemies = 1;
        Main.reservedCount = 0;
        assertEquals(1, Main.getNumberSteps());
    }

    @Test
    void contextTest4() {
        Main.xOurSoldiers = 25;
        Main.yBuilding = 200;
        Main.pEnemySoldiers = 10;
        Main.generatedEnemies = 10;
        Main.reservedCount = 0;
        assertEquals(13, Main.getNumberSteps());
    }

    @Test
    void contextTest5() {
        Main.xOurSoldiers = 250;
        Main.yBuilding = 500;
        Main.pEnemySoldiers = 187;
        Main.generatedEnemies = 187;
        Main.reservedCount = 0;
        assertEquals(4, Main.getNumberSteps());
    }

    @Test
    void Test6() {
        Main.xOurSoldiers = 300;
        Main.yBuilding = 301;
        Main.pEnemySoldiers = 485;
        Main.generatedEnemies = 485;
        Main.reservedCount = 0;
        assertEquals(-1, Main.getNumberSteps());
    }

    @Test

    void Test7() {
        Main.xOurSoldiers = 300;
        Main.yBuilding = 301;
        Main.pEnemySoldiers = 484;
        Main.generatedEnemies = 484;
        Main.reservedCount = 0;
        assertEquals(6, Main.getNumberSteps());
    }

    @Test
    void Test8() {
        Main.xOurSoldiers = 250;
        Main.yBuilding = 500;
        Main.pEnemySoldiers = 230;
        Main.generatedEnemies = 230;
        Main.reservedCount = 0;
        assertEquals(8, Main.getNumberSteps());
    }

    // @Test
    // void Test9() {
    // Main.xOurSoldiers = 5;
    // Main.yBuilding = 8;
    // Main.pEnemySoldiers = 4;
    // Main.generatedEnemies = 4;
    // Main.reservedCount = 0;
    // assertEquals(4, Main.getNumberSteps());
    // }

    @Test // не работает при сечении, но при цифре да
    void Test10() {
        Main.xOurSoldiers = 250;
        Main.yBuilding = 500;
        Main.pEnemySoldiers = 218;
        Main.generatedEnemies = 218;
        Main.reservedCount = 0;
        assertEquals(6, Main.getNumberSteps());
    }

    @Test
    void Test11() {
        Main.xOurSoldiers = 250;
        Main.yBuilding = 500;
        Main.pEnemySoldiers = 249;
        Main.generatedEnemies = 249;
        Main.reservedCount = 0;
        assertEquals(101, Main.getNumberSteps());
    }

    @Test
    void Test12() {
        Main.xOurSoldiers = 8;
        Main.yBuilding = 12;
        Main.pEnemySoldiers = 7;
        Main.generatedEnemies = 7;
        Main.reservedCount = 0;
        assertEquals(3, Main.getNumberSteps());
    }

    @Test // не работает даже на сечении
    void Test13() {
        Main.xOurSoldiers = 2500;
        Main.yBuilding = 5000;
        Main.pEnemySoldiers = 2499;
        Main.generatedEnemies = 2499;
        Main.reservedCount = 0;
        assertEquals(961, Main.getNumberSteps());
    }

    @Test // работает при сечении
    void Test13a() {
        Main.xOurSoldiers = 78;
        Main.yBuilding = 4934;
        Main.pEnemySoldiers = 77;
        Main.generatedEnemies = 77;
        Main.reservedCount = 0;
        assertEquals(4812, Main.getNumberSteps());
    }

    @Test // работает при сечении
    void Test14() { // работает при сечениии при цифре
        Main.xOurSoldiers = 78;
        Main.yBuilding = 126;
        Main.pEnemySoldiers = 77;
        Main.generatedEnemies = 77;
        Main.reservedCount = 0;
        assertEquals(5, Main.getNumberSteps());
    }

    @Test
    void Test15() { // не работает
        Main.xOurSoldiers = 1661;
        Main.yBuilding = 4327;
        Main.pEnemySoldiers = 1107;
        Main.generatedEnemies = 1107;
        Main.reservedCount = 0;
        assertEquals(6, Main.getNumberSteps());
    }

    @Test
    void Test16() { // не работает
        Main.xOurSoldiers = 1092;
        Main.yBuilding = 2892;
        Main.pEnemySoldiers = 950;
        Main.generatedEnemies = 950;
        Main.reservedCount = 0;
        assertEquals(11, Main.getNumberSteps());
    }

    @Test // не работает
    void Test17() {
        Main.xOurSoldiers = 31;
        Main.yBuilding = 495;
        Main.pEnemySoldiers = 15;
        Main.generatedEnemies = 15;
        Main.reservedCount = 0;
        assertEquals(30, Main.getNumberSteps());
    }

    @Test // работает
    void Test18() {
        Main.xOurSoldiers = 250;
        Main.yBuilding = 500;
        Main.pEnemySoldiers = 209;
        Main.generatedEnemies = 209;
        Main.reservedCount = 0;
        assertEquals(6, Main.getNumberSteps());
    }

    @Test // не работает
    void Test19() {
        Main.xOurSoldiers = 3000;
        Main.yBuilding = 5000;
        Main.pEnemySoldiers = 2998;
        Main.generatedEnemies = 2998;
        Main.reservedCount = 0;
        assertEquals(79, Main.getNumberSteps());
    }

    @Test // не работает
    void Test20() {
        Main.xOurSoldiers = 2500;
        Main.yBuilding = 5000;
        Main.pEnemySoldiers = 2420;
        Main.generatedEnemies = 2420;
        Main.reservedCount = 0;
        assertEquals(16, Main.getNumberSteps());
    }

    @Test // не работает
    void Test46() {
        Main.xOurSoldiers = 1;
        Main.yBuilding = 500;
        Main.pEnemySoldiers = 1;
        Main.generatedEnemies = 1;
        Main.reservedCount = 0;
        assertEquals(-1, Main.getNumberSteps());
    }

}
