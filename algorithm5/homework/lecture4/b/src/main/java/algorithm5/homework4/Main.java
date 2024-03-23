package algorithm5.homework4;

/**
Поле в игре в одномерный морской бой имеет размеры 1×n. Ваша задача — найти такое максимальное k, что на поле 
можно расставить один корабль размера 1×k, два корабля размера 1×(k−1), …, k кораблей размера 1×1, причем корабли, 
как и в обычном морском бое, не должны касаться друг друга и пересекаться.

Формат ввода
В единственной строке входных данных дано число n — количество клеток поля (0≤n≤10^18).

Формат вывода
Выведите единственное число — такое максимальное k, что можно расставить корабли, как описано в условии.

Примечания
Пояснение к примеру: для поля 1×7 ответ равен 2. Расставить один корабль размера 1×2 и два корабля размера 1×1 
 */

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger numberCells = new BigInteger(scanner.nextLine());
        System.out.println(getShipsNumber(numberCells));
        scanner.close();
    }

    public static long getShipsNumber(BigInteger numberCells) {
        BigInteger left = BigInteger.ZERO;
        BigInteger right = numberCells;

        while (left.compareTo(right) < 0) {
            BigInteger med = left.add(right).add(BigInteger.ONE).divide(BigInteger.valueOf(2));
            BigInteger check = isShipsAtField(med);
            if (check.compareTo(numberCells) <= 0) {
                left = med;
            } else {
                right = med.subtract(BigInteger.valueOf(1));
            }
        }
        return right.longValue();
    }

    private static BigInteger isShipsAtField(BigInteger maxShipLevel) {
        return maxShipLevel.multiply(maxShipLevel.add(BigInteger.valueOf(1)))
                .multiply(maxShipLevel.add(BigInteger.valueOf(5))).divide(BigInteger.valueOf(6))
                .subtract(BigInteger.ONE);
    }
}