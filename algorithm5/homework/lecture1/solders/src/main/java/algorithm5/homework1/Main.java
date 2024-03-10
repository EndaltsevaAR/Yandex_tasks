package algorithm5.homework1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int xOurSoldiers = Integer.parseInt(scanner.nextLine());
        int yBuilding = Integer.parseInt(scanner.nextLine());
        int pEnemySoldiers = Integer.parseInt(scanner.nextLine());
        System.out.println(getNumberSteps(xOurSoldiers, yBuilding, pEnemySoldiers));
        scanner.close();
    }

    public static int getNumberSteps(int xOurSoldiers, int yBuilding, int pEnemySoldiers) {
        int enemyGeneration = pEnemySoldiers;
        if (yBuilding - xOurSoldiers <= 0) { // мы разрушим казарму еще до начала генерации солдат протвника
            return 1;
        }
        // prepare
        int count = 1;
        yBuilding -= xOurSoldiers;

        // в первом ходу мы разрушили часть казармы, и теперь каждый ход вынуждены
        // просто убивать солдат противника, или те убьют/ наших
        if (xOurSoldiers == yBuilding && xOurSoldiers == pEnemySoldiers) {
            return -1;
        }

        // если мы не успеем разрушить казарму за два хода, то противников станет больше
        // наших
        if (yBuilding * 1.5 > xOurSoldiers && pEnemySoldiers > xOurSoldiers) {
            return -1;
        }

        if (xOurSoldiers == yBuilding + pEnemySoldiers) {
            return 2;
        }

        // общая
        double number = (1 + Math.sqrt(5)) / 2;
        double roundedNumber = Math.round(number * 100000.0) / 100000.0;
        // while ((yBuilding + pEnemySoldiers) / (double) xOurSoldiers >= (1 +
        // Math.sqrt(5)) / 2) {
        // while ((yBuilding + pEnemySoldiers) / (double) xOurSoldiers > 1.615) {
        while ((yBuilding + pEnemySoldiers) / (double) xOurSoldiers > number) {
            count++;
            int deltaEnemy = (xOurSoldiers - pEnemySoldiers);
            if (deltaEnemy > 0) {
                yBuilding -= deltaEnemy;
                pEnemySoldiers = 0;
            } else {
                pEnemySoldiers -= xOurSoldiers;
                xOurSoldiers -= Math.abs(deltaEnemy);
            }
            pEnemySoldiers += enemyGeneration;
        }

        // снос казармы
        int delta = yBuilding;
        while (pEnemySoldiers > 0) {
            count++;
            pEnemySoldiers -= (xOurSoldiers - delta);
            if (pEnemySoldiers <= 0) {
                return count;
            }
            xOurSoldiers -= pEnemySoldiers;
            if (xOurSoldiers <= 0) { // на всякий случай можно рассмотреть поражение, если я забыла какое-то краевое
                                     // в начале, здесь должно его вырулить
                return -1;
            }
            delta = 0;
        }
        return count;
    }
}