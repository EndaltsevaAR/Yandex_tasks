package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void contextTest1() {
        int nNumberHolidays = 3;
        int year = 2013;
        String[] holidays = new String[nNumberHolidays];
        holidays[0] = "1 January";
        holidays[1] = "8 January";
        holidays[2] = "15 January";
        String firstDayOfWeek = "Tuesday";
        assertTrue("Monday Tuesday".equals(Main.getRestDayName(year, holidays, firstDayOfWeek)));

    }

}
