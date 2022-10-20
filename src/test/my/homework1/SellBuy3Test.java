package test.my.homework1;
import static org.junit.Assert.*;

import main.my.homework1.SellBuy3;
import org.junit.Test;

public class SellBuy3Test {
    @Test
    public void test1() {
        System.out.println("Test from task");
        // int number = 6;
        int[] ints = {10, 3, 5, 3 ,11, 9};
        String result = SellBuy3.getDaysForBuyAndSell(ints);
        assertEquals(result, "2 5");
    }

    @Test
    public void test2() {
        System.out.println("Test from task no profit");
        // int number = 4;
        int[] ints = {5, 5, 5, 5};
        String result = SellBuy3.getDaysForBuyAndSell(ints);
        assertEquals(result, "0 0");
    }
}
