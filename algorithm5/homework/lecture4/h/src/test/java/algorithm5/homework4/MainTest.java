package algorithm5.homework4;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void contextTest1() {
        Party party1 = new Party(1, 7, -1);
        Party party2 = new Party(2, 2, 8);
        Party party3 = new Party(3, 1, 2);
        Set<Party> allParties = new TreeSet<>(Comparator.comparingLong((Party p) -> p.votes));
        TreeSet<Party> corruptedParties = new TreeSet<>(
                Comparator.comparingLong((Party p) -> p.totalCost).reversed());
        allParties.add(party1);
        allParties.add(party2);
        allParties.add(party3);
        corruptedParties.add(party2);
        corruptedParties.add(party3);
        // String answer = Main.getElection(3, 10, allParties, corruptedParties);
        // assertTrue("6\n3\n3 2 5".equals(answer));
    }
}
