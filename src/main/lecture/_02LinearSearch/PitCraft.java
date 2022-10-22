package main.lecture._02LinearSearch;

/*
Description:
Игра PitCraft происходит в двумерном мире, который состоит из блоков
размером 1 на 1 метр. Остров игрока представляет собой набор различных
столбцов, состоящих из блока камня и окруженный морем. Над островом
прошел сильный дождь, который заполнил водой все низины, а не
поместившаяся в них вода стекла в море, не увеличив его уровень.
По ландшафту острова определить, сколько воды осталось после дождя в
низинах на острове
 */

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PitCraft {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<Integer> heights = Arrays.stream(line.split(" ")).map(s -> Integer.parseInt(s.trim())).toList();
        scanner.close();
        System.out.println(rain(heights));
    }

    private static int rain(List<Integer> heights) {
        int maxHeightPosition = 0;
        for (int i = 0; i < heights.size(); i++) {
            if (heights.get(i) > heights.get(maxHeightPosition)) {
                maxHeightPosition = i;        //find max height of all columns
            }
        }
        int rainAnswer = 0;
        int currentMax = 0;
        for (int i = 0; i < maxHeightPosition; i++) {
            if (heights.get(i) > currentMax) {
                currentMax = heights.get(i);
            }
            rainAnswer += currentMax - heights.get(i);
        }
        currentMax = 0;
        for (int i = heights.size() - 1; i > maxHeightPosition; i--) {
            if (heights.get(i) > currentMax) {
                currentMax = heights.get(i);
            }
            rainAnswer += currentMax - heights.get(i);
        }
        return rainAnswer;
    }
}
