package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void contextTest1() {
        int nNumberHolidays = 2;
        int year = 2015;
        String[] holidays = new String[nNumberHolidays];
        holidays[0] = "1 January";
        holidays[1] = "8 January";
        String firstDayOfWeek = "Thursday";
        assertTrue("Monday Thursday".equals(Main.getRestDayName(year, holidays, firstDayOfWeek)));

    }

    @Test
    void contextTest2() {
        int nNumberHolidays = 3;
        int year = 2013;
        String[] holidays = new String[nNumberHolidays];
        holidays[0] = "1 January";
        holidays[1] = "8 January";
        holidays[2] = "15 January";
        String firstDayOfWeek = "Tuesday";
        assertTrue("Monday Tuesday".equals(Main.getRestDayName(year, holidays, firstDayOfWeek)));

    }

    @Test
    void contextTest3() {
        int nNumberHolidays = 3;
        int year = 2013;
        String[] holidays = new String[nNumberHolidays];
        holidays[0] = "6 February";
        holidays[1] = "13 February";
        holidays[2] = "20 February";
        String firstDayOfWeek = "Tuesday";
        assertTrue("Tuesday Wednesday".equals(Main.getRestDayName(year, holidays, firstDayOfWeek)));

    }

}
