package algorithm5.homework2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int numberShips = Integer.parseInt(scanner.nextLine());
        String[] ships = new String[numberShips];
        for (int i = 0; i < ships.length; i++) {
            ships[i] = scanner.nextLine();
        }
        System.out.println(getMinMoves(ships));
        scanner.close();
    }

    public static int getMinMoves(String[] ships) {
        int countMoves = 0;

        return countMoves;
    }
}