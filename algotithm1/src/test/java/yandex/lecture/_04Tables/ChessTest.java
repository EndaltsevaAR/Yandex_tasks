package yandex.lecture._04Tables;

import static org.junit.Assert.*;

import org.junit.Test;

import yandex.lecture._04Tables.Chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChessTest {
    @Test
    public void test1() {
        System.out.println("Test");
        String line = "0,0 0,1 0,2";
        List<String> rocks = Arrays.stream(line.split(" ")).collect(Collectors.toList());
        List<List<Integer>> rocksCoordinates = new ArrayList<>();
        for (String rock : rocks) {
            List<Integer> coordinates = Arrays.stream(rock.split(",")).map(Integer::parseInt)
                    .collect(Collectors.toList());
            rocksCoordinates.add(coordinates);
        }
        int result = Chess.findRockPairs(rocksCoordinates);
        assertEquals(result, 2);
    }

    @Test
    public void test2() {
        System.out.println("Test2");
        String line = "0,0 0,1 0,2 1,0";
        List<String> rocks = Arrays.stream(line.split(" ")).collect(Collectors.toList());
        List<List<Integer>> rocksCoordinates = new ArrayList<>();
        for (String rock : rocks) {
            List<Integer> coordinates = Arrays.stream(rock.split(",")).map(Integer::parseInt)
                    .collect(Collectors.toList());
            rocksCoordinates.add(coordinates);
        }
        int result = Chess.findRockPairs(rocksCoordinates);
        assertEquals(result, 3);
    }
}
