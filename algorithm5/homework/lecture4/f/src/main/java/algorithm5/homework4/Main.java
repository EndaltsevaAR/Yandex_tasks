package algorithm5.homework4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            long widthSquare = Long.parseLong(st.nextToken());
            long heightSquare = Long.parseLong(st.nextToken());
            int numberBadCells = Integer.parseInt(st.nextToken());
            Cell[] xyCoordBadCells = new Cell[numberBadCells];
            for (int i = 1; i <= numberBadCells; i++) {
                st = new StringTokenizer(reader.readLine());
                xyCoordBadCells[i] = new Cell(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            }
            System.out.println(getMinWidthVelo(widthSquare, heightSquare, xyCoordBadCells));
        }
    }

    public static int getMinWidthVelo(long widthSquare, long heightSquare, Cell[] xyCoordBadCells) {
        // сортировка сначала по х по возрастанию, потом по у по возрастанию
        Arrays.sort(xyCoordBadCells, Comparator.comparingLong(Cell::getX).thenComparingLong(Cell::getY));
        return 0;
    }
}

class Cell {
    private long x;
    private long y;

    public Cell(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (x ^ (x >>> 32));
        result = prime * result + (int) (y ^ (y >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        return x == other.x && y == other.y;
    }

}