package test.my;
import main.my.SightTask1;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class SightTask1Test {

    @Test
    public void Test1() {
        String testString = "2 1 2/n2 2 3/n2 3 1";
        ByteArrayInputStream in = new ByteArrayInputStream(testString.getBytes());
        InputStream inputStream = System.in;
        System.setIn(in);
        SightTask1 sightTask1 = new SightTask1();

        System.setIn(inputStream);
    }

}