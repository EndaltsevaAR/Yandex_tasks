package algorithm5.homework2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        String[] persons = new String[2];
        persons[0] = "1 2";
        persons[1] = "3 4";
        assertTrue("2 2".equals(Main.getMaxPerson(persons, 2)));
    }

    @Test
    void contextTest2() {
        String[] persons = new String[3];
        persons[0] = "1 3 5 7";
        persons[1] = "9 11 2 4";
        persons[2] = "6 8 10 12";
        assertTrue("3 2".equals(Main.getMaxPerson(persons, 4)));
    }

    @Test
    void contextTest3() {
        String[] persons = new String[4];
        persons[0] = "6 7 2";
        persons[1] = "2 8 6";
        persons[2] = "9 1 8";
        persons[3] = "5 8 6";
        assertTrue("3 2".equals(Main.getMaxPerson(persons, 3)));
    }

    @Test
    void contextTest8() {
        String[] persons = new String[4];
        persons[0] = "999999997 1 2 3 4";
        persons[1] = "10 2 1000000000 1 7";
        persons[2] = "3 9 999999999 5 10";
        persons[3] = "1 7 3 999999998 6";
        assertTrue("4 3".equals(Main.getMaxPerson(persons, 5)));
    }

    @Test
    void contextTest13() {
        String[] persons = new String[6];
        persons[0] = "5 6 4 9";
        persons[1] = "3 2 7 5";
        persons[2] = "3 7 5 2";
        persons[3] = "8 5 9 5";
        persons[4] = "7 5 3 1";
        persons[5] = "4 3 7 6";
        assertTrue("4 4".equals(Main.getMaxPerson(persons, 4)));
    }

}
