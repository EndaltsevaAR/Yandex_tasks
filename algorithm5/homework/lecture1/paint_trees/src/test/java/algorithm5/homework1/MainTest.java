package algorithm5.homework1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void testFromContest() {
        assertEquals(25, Main.getTreesNumber("0 7", "12 5"));
    }

    @Test
    void sameDataForVasyaAndMasha() {
        assertEquals(15, Main.getTreesNumber("0 7", "0 7"));
    }

    @Test
    void sameStartForVasyaAndMashaLenghtVasyaBigger() {
        assertEquals(15, Main.getTreesNumber("0 7", "0 5"));
    }

    @Test
    void sameStartForVasyaAndMashaLenghtMashaBigger() {
        assertEquals(15, Main.getTreesNumber("0 5", "0 7"));
    }

    @Test
    void vasyaAndMashaNotLeght() {
        assertEquals(1, Main.getTreesNumber("0 0", "0 0"));
    }

    @Test
    void intersectionTrees() {
        assertEquals(7, Main.getTreesNumber("0 3", "2 0"));
    }

}
