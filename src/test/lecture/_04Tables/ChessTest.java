package test.lecture._04Tables;
import static org.junit.Assert.*;

import main.lecture._04Tables.Chess;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessTest {
    @Test
    public void test1() {
        System.out.println("Test");
        String line = "0,0 0,1 0,2";
        List<String> rocks = Arrays.stream(line.split(" ")).toList();
        List<List<Integer>> rocksCoordinates = new ArrayList<>();
        for (String rock: rocks) {
            List<Integer> coordinates = Arrays.stream(rock.split(",")).map(Integer::parseInt).toList();
            rocksCoordinates.add(coordinates);
        }
        int result = Chess.findRockPairs(rocksCoordinates);
        assertEquals(result, 2);
    }

    @Test
    public void test2() {
        System.out.println("Test2");
        String line = "0,0 0,1 0,2 1,0";
        List<String> rocks = Arrays.stream(line.split(" ")).toList();
        List<List<Integer>> rocksCoordinates = new ArrayList<>();
        for (String rock: rocks) {
            List<Integer> coordinates = Arrays.stream(rock.split(",")).map(Integer::parseInt).toList();
            rocksCoordinates.add(coordinates);
        }
        int result = Chess.findRockPairs(rocksCoordinates);
        assertEquals(result, 3);
    }
}
