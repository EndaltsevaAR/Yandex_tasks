package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void contextBothOperation() {
        assertTrue("x+".equals(Main.getOperators("5 7 2")));
    }

    @Test
    void contextOneOperation() {
        assertTrue("+".equals(Main.getOperators("4 -5")));
    }

    void twoOddNumbers() {
        assertTrue("x".equals(Main.getOperators("1 3")));
    }

    @Test
    void firstOddSecondEvenNumbers() {
        assertTrue("+".equals(Main.getOperators("3 4")));
    }

    @Test
    void firstEvenSecondOddNumbers() {
        assertTrue("+".equals(Main.getOperators("4 3")));
    }

    @Test
    void testFuction1() {
        assertTrue(Main.getOperatorsForTest("2 1 2 3"));
    }

    @Test
    void testFuction2() {
        assertTrue(Main.getOperatorsForTest("2 2 2 3"));
    }

    @Test
    void testFuction3() {
        assertTrue(Main.getOperatorsForTest("2 2 2 3 1"));
    }

    @Test
    void testFuction4() {
        assertTrue(Main.getOperatorsForTest("2 2 2 3 1 0"));
    }

    @Test
    void testFuction5() {
        assertTrue(Main.getOperatorsForTest("2 0 2 2 3 1 0"));
    }

    @Test
    void testFuctionContext() {
        assertTrue(Main.getOperatorsForTest("390029247 153996608 -918017777"));
    }

    @Test
    void testFuctionContext0() {
        assertTrue(Main.getOperatorsForTest("0 0 0 0 0 1 1 1 1 1"));
    }

    @Test
    void testFuctionContext01() {
        assertTrue(Main.getOperatorsForTest("0 0 0 0 0 1 1 1 1 1 1"));
    }

    @Test
    void testFuctionContext03() {
        assertTrue(Main.getOperatorsForTest("0 0 0 0 0 1 1 1 0 1 1 1"));
    }

}
