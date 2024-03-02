package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void drawAtHome() {
        assertEquals(1, Main.ballToWin("0:0", "0:0", "1"));
    }

    @Test
    void drawNotAtHome() {
        assertEquals(1, Main.ballToWin("0:0", "0:0", "2"));
    }

    @Test
    void loseAtHome() {
        assertEquals(5, Main.ballToWin("0:2", "0:3", "1"));
    }

    @Test
    void loseNotAtHome() {
        assertEquals(6, Main.ballToWin("0:2", "0:3", "2"));
    }

    @Test
    void winAtHome() {
        assertEquals(0, Main.ballToWin("2:0", "3:0", "1"));
    }

    @Test
    void winNotAtHome() {
        assertEquals(0, Main.ballToWin("2:0", "3:0", "2"));
    }

    @Test
    void maxAtHome() {
        assertEquals(1, Main.ballToWin("5:5", "5:5", "1"));
    }

    @Test
    void maxNotAtHome() {
        assertEquals(1, Main.ballToWin("5:5", "5:5", "2"));
    }

    @Test
    void WinAndLoseNotAtHome() {
        assertEquals(1, Main.ballToWin("5:2", "2:5", "2"));
    }

    @Test
    void anotherWinAndLoseNotAtHome() {
        assertEquals(1, Main.ballToWin("2:3", "5:4", "2"));
    }

    @Test
    void moreWinAndLoseNotAtHome() {
        assertEquals(3, Main.ballToWin("5:2", "0:5", "1"));
    }

}
