package algorithm5.homework2;

/**
Развлекательный телеканал транслирует шоу «Колесо Фортуны». В процессе игры участники шоу крутят большое колесо, разделенное на сектора. 
В каждом секторе этого колеса записано число. После того как колесо останавливается, специальная стрелка указывает на один из секторов. 
Число в этом секторе определяет выигрыш игрока.

Юный участник шоу заметил, что колесо в процессе вращения замедляется из-за того, что стрелка задевает за выступы на колесе, находящиеся 
между секторами. Если колесо вращается с угловой скоростью v градусов в секунду, и стрелка, переходя из сектора X к следующему сектору, 
задевает за очередной выступ, то текущая угловая скорость движения колеса уменьшается на k градусов в секунду. При этом если v ≤ k, 
то колесо не может преодолеть препятствие и останавливается. Стрелка в этом случае будет указывать на сектор X.



Юный участник шоу собирается вращать колесо. Зная порядок секторов на колесе, он хочет заставить колесо вращаться с такой начальной 
скоростью, чтобы после остановки колеса стрелка указала на как можно большее число. Колесо можно вращать в любом направлении и 
придавать ему начальную угловую скорость от a до b градусов в секунду.

Требуется написать программу, которая по заданному расположению чисел в секторах, минимальной и максимальной начальной угловой 
скорости вращения колеса и величине замедления колеса при переходе через границу секторов вычисляет максимальный выигрыш.

Формат ввода
Первая строка входного файла содержит целое число n — количество секторов колеса (3 ≤ n ≤ 100).

Вторая строка входного файла содержит n положительных целых чисел, каждое из которых не превышает 1000 — числа, записанные 
в секторах колеса. Числа приведены в порядке следования секторов по часовой стрелке. Изначально стрелка указывает на первое число.

Третья строка содержит три целых числа: a, b и k (1 ≤ a ≤ b ≤ 10^9, 1 ≤ k ≤ 10^9).

Формат вывода
В выходном файле должно содержаться одно целое число — максимальный выигрыш.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberCells = Integer.parseInt(scanner.nextLine());
        String cells = scanner.nextLine();
        String speeds = scanner.nextLine();
        System.out.println(getMaxProfit(numberCells, cells, speeds));
        scanner.close();
    }

    public static int getMaxProfit(int numberCells, String sCells, String speeds) {
        String[] splitedSCells = sCells.split(" ");
        int[] cells = new int[numberCells];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = Integer.parseInt(splitedSCells[i]);
        }
        long aMinSpeed = Long.parseLong(speeds.split(" ")[0]);
        long bMaxSpeed = Long.parseLong(speeds.split(" ")[1]);
        long kDeltaSpeed = Long.parseLong(speeds.split(" ")[2]);

        if (bMaxSpeed <= kDeltaSpeed) {
            return cells[0];
        }
        long speedDif = bMaxSpeed - aMinSpeed;
        if (speedDif >= kDeltaSpeed * numberCells) {
            return Arrays.stream(cells).max().getAsInt();
        }

        int maxProfit = 0;

        long steps = speedDif / kDeltaSpeed;
        long minMoves = getMovesCount(aMinSpeed, kDeltaSpeed, numberCells);
        if (steps + minMoves >= numberCells) {
            minMoves -= ((steps + minMoves) - numberCells + 1);
            steps = numberCells - 1 - minMoves;
        }

        for (long i = minMoves; i <= minMoves + steps; i++) {
            int clockwise = cells[(int) i];
            if (clockwise > maxProfit) {
                maxProfit = clockwise;
            }

            int counterclockwise;
            if (i == 0) {
                counterclockwise = cells[0];
            } else {
                counterclockwise = cells[numberCells - (int) i];
            }
            if (counterclockwise > maxProfit) {
                maxProfit = counterclockwise;
            }
        }
        return maxProfit;
    }

    private static long getMovesCount(long speed, long kDeltaSpeed, long numberCells) {
        long moves = (speed / kDeltaSpeed) % numberCells;

        if (speed % kDeltaSpeed == 0 && moves != 0) {
            return moves - 1;
        } else {
            return moves;
        }
    }

}