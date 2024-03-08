package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void contextTest1() {
        assertTrue("YES\n1.0000000000".equals(Main.isBoyMeetings(6, 3, 1, 1, 1)));
    }

    @Test
    void contextTest2() {
        assertTrue("YES\n0.3000000000".equals(Main.isBoyMeetings(12, 8, 10, 5, 20)));
    }

    @Test
    void contextTest3() {
        assertTrue("YES\n2.0000000000".equals(Main.isBoyMeetings(5, 0, 0, 1, 2)));
    }

    @Test
    void contextTest4() {
        assertTrue("YES\n0.8571428571".equals(Main.isBoyMeetings(10, 7, -3, 1, 4)));
    }

    @Test
    void testSpeedsCoordinatesZiro() {
        assertTrue("YES\n0.0000000000".equals(Main.isBoyMeetings(10, 0, 0, 0, 0)));
    }

    @Test
    void testSpeedsZiroCoordinatesDifNotSimmetric() {
        assertTrue("NO".equals(Main.isBoyMeetings(10, 1, 0, 2, 0)));
    }

    @Test
    void testSpeedsZiroCoordinatesZiroButSimmetricIntoL() {
        assertTrue("YES\n0.0000000000".equals(Main.isBoyMeetings(6, 1, 0, 5, 0)));
    }

    @Test
    void contextTest7() {
        assertTrue(
                "YES\n2.4075923389".equals(Main.isBoyMeetings(615143346, 79387687, -80123649, 306422480, -80123649)));
    }

    @Test
    void contextTestMiddle() {
        assertTrue(
                "YES\n1.0000000000".equals(Main.isBoyMeetings(10, 0, 5, 5, 0)));
    }

    @Test
    void contextTest8() {
        assertTrue(
                "YES\n0.2859497398".equals(Main.isBoyMeetings(948744004, 861724643, 848773505, 584154450, 730556189)));
    }

}
