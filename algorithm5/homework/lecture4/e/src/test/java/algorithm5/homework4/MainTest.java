package algorithm5.homework4;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void contextTest2() {
        assertTrue("3/1".equals(Main.getNumeratorAndDenominator(new BigInteger("6"))));
    }

    @Test
    void contextTest3() {
        assertTrue("2/1".equals(Main.getNumeratorAndDenominator(new BigInteger("2"))));
    }

    @Test
    void myTest3() {
        assertTrue("1/2".equals(Main.getNumeratorAndDenominator(new BigInteger("3"))));
    }

    @Test
    void myTest4() {
        assertTrue("1/3".equals(Main.getNumeratorAndDenominator(new BigInteger("4"))));
    }

    @Test
    void myTest5() {
        assertTrue("2/2".equals(Main.getNumeratorAndDenominator(new BigInteger("5"))));
    }
}
