package test.my.homework1;
import static org.junit.Assert.*;

import main.my.homework1.Path2;
import main.my.homework1.SellBuy3;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SellBuy3Test {
    @Test
    public void test1() {
        System.out.println("Test from task");
        int number = 6;
        List<Integer> ints = Arrays.asList(10, 3, 5, 3 ,11, 9);
        String result = SellBuy3.getDaysForBuyAndSell(number, ints);
        assertEquals(result, "2 5");
    }
}
