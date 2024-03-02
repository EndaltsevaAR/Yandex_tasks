package algorithm5.homework1;

/**
 * На шахматной доске стоят слоны и ладьи, необходимо посчитать, сколько клеток не бьется ни одной из фигур.
 * 
 * Шахматная доска имеет размеры 8 на 8. Ладья бьет все клетки горизонтали и вертикали, проходящих через клетку, 
 * где она стоит, до первой встретившейся фигуры. Слон бьет все клетки обеих диагоналей, проходящих через клетку, 
 * где он стоит, до первой встретившейся фигуры.
 * 
 * Формат ввода
 * В первых восьми строках ввода описывается шахматная доска. 
 * Первые восемь символов каждой из этих строк описывают состояние соответствующей горизонтали: 
 * символ B (заглавная латинская буква) означает, что в клетке стоит слон, символ R — ладья, символ * — что клетка пуста. 
 * После описания горизонтали в строке могут идти пробелы, однако длина каждой строки не превышает 250 символов. 
 * После описания доски в файле могут быть пустые строки.
 * 
 * Формат вывода
 * Выведите количество пустых клеток, которые не бьются ни одной из фигур.
 */

import java.util.Scanner;

public class Main {
    public static final int FIELD_LEN = 8;

    public static void main(String[] args) {
        char[][] field = new char[FIELD_LEN][FIELD_LEN];

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < FIELD_LEN; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < FIELD_LEN; j++) {
                field[i][j] = line.charAt(j);
            }
        }

        System.out.println(getUnusedCells(field));
        scanner.close();
    }

    public static int getUnusedCells(char[][] field) {
        int count = 0;

        for (int i = 0; i < FIELD_LEN; i++) {
            for (int j = 0; j < FIELD_LEN; j++) {
                if (!(field[i][j] == 'B' || field[i][j] == 'R' || rockWay(field, i, j) || bishopWay(field, i, j))) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean rockWay(char[][] field, int i, int j) {
        boolean isWay = false;

        // horizontal
        int leftCheck = j - 1;
        while (leftCheck >= 0) {
            if (field[i][leftCheck] == 'B') {
                break;
            } else if (field[i][leftCheck] == 'R') {
                return true;
            }
            leftCheck--;
        }

        int rightCheck = j + 1;
        while (rightCheck < FIELD_LEN) {
            if (field[i][rightCheck] == 'B') {
                break;
            } else if (field[i][rightCheck] == 'R') {
                return true;
            }
            rightCheck++;
        }

        // vertical
        int upCheck = i - 1;
        while (upCheck >= 0) {
            if (field[upCheck][j] == 'B') {
                break;
            } else if (field[upCheck][j] == 'R') {
                return true;
            }
            upCheck--;
        }

        int downCheck = i + 1;
        while (downCheck < FIELD_LEN) {
            if (field[downCheck][j] == 'B') {
                break;
            } else if (field[downCheck][j] == 'R') {
                return true;
            }
            downCheck++;
        }
        return isWay;
    }

    private static boolean bishopWay(char[][] field, int i, int j) {
        boolean isWay = false;

        // up left
        int iChechUpLeft = i - 1;
        int jCheckUpLeft = j - 1;
        while (iChechUpLeft >= 0 && jCheckUpLeft >= 0) {
            if (field[iChechUpLeft][jCheckUpLeft] == 'R') {
                break;
            } else if (field[iChechUpLeft][jCheckUpLeft] == 'B') {
                return true;
            }
            iChechUpLeft--;
            jCheckUpLeft--;
        }

        // up right
        int iChechUpRight = i - 1;
        int jCheckUpLRight = j + 1;
        while (iChechUpRight >= 0 && jCheckUpLRight < FIELD_LEN) {
            if (field[iChechUpRight][jCheckUpLRight] == 'R') {
                break;
            } else if (field[iChechUpRight][jCheckUpLRight] == 'B') {
                return true;
            }
            iChechUpRight--;
            jCheckUpLRight++;
        }

        // down left
        int iChechDownLeft = i + 1;
        int jCheckDownLeft = j - 1;
        while (iChechDownLeft < FIELD_LEN && jCheckDownLeft >= 0) {
            if (field[iChechDownLeft][jCheckDownLeft] == 'R') {
                break;
            } else if (field[iChechDownLeft][jCheckDownLeft] == 'B') {
                return true;
            }
            iChechDownLeft++;
            jCheckDownLeft--;
        }

        // down right
        int iChechDownRight = i + 1;
        int jCheckDownRight = j + 1;
        while (iChechDownRight < FIELD_LEN && jCheckDownRight < FIELD_LEN) {
            if (field[iChechDownRight][jCheckDownRight] == 'R') {
                break;
            } else if (field[iChechDownRight][jCheckDownRight] == 'B') {
                return true;
            }
            iChechDownRight++;
            jCheckDownRight++;
        }
        return isWay;
    }
}