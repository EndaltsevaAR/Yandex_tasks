package algorithm5.homework4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        assertEquals(2, Main.getShipsNumber(new BigInteger("7")));
    }

    @Test
    void Test1() {
        assertEquals(1, Main.getShipsNumber(new BigInteger("1")));
    }

    @Test
    void Test2() {
        assertEquals(1, Main.getShipsNumber(new BigInteger("2")));
    }

    @Test
    void Test6() {
        assertEquals(1, Main.getShipsNumber(new BigInteger("3")));
    }

    @Test
    void contextTest11() {
        assertEquals(8, Main.getShipsNumber(new BigInteger("200")));
    }

    @Test
    void contextTest5() {
        assertEquals(1, Main.getShipsNumber(new BigInteger("5")));
    }

    // @Test
    // void contextTest25() {
    // assertEquals(1, Main.getShipsNumber(6265505));
    // }
}
