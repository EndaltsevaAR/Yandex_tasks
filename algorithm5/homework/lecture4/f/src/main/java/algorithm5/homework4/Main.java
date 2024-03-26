package algorithm5.homework4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            long widthSquare = Long.parseLong(st.nextToken());
            long heightSquare = Long.parseLong(st.nextToken());
            int numberBadCells = Integer.parseInt(st.nextToken());
            long[][] xyCoordBadCells = new long[numberBadCells][2];
            for (int i = 1; i <= numberBadCells; i++) {
                st = new StringTokenizer(reader.readLine());
                xyCoordBadCells[i][0] = Long.parseLong(st.nextToken());
                xyCoordBadCells[i][1] = Long.parseLong(st.nextToken());
            }
            System.out.println(getMinWidthVelo(widthSquare, heightSquare, xyCoordBadCells));
        }
    }

    public static int getMinWidthVelo(long widthSquare, long heightSquare, long[][] xyCoordBadCells) {

        return 0;
    }
}