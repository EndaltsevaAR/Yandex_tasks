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
        if (yBuilding - xOurSoldiers <= 0) {
            return 1;
        }
        // prepare;
        int count = 1;
        yBuilding -= xOurSoldiers;

        if (yBuilding * 1.5 > xOurSoldiers && pEnemySoldiers > xOurSoldiers) {
            return -1;
        }

        // common
        while (((yBuilding + pEnemySoldiers) / (double) xOurSoldiers) > (1 + Math.sqrt(5)) / 2) { // можно ли удалить
                                                                                                  // лишние скобки
            int delta = xOurSoldiers - pEnemySoldiers;
            yBuilding -= delta;
            count++;
        }

        // kill building
        count++;
        pEnemySoldiers -= (xOurSoldiers - yBuilding);
        if (pEnemySoldiers <= 0) {
            return count;
        }
        xOurSoldiers -= pEnemySoldiers;
        while (pEnemySoldiers > 0) {
            count++;
            pEnemySoldiers -= xOurSoldiers;
            if (pEnemySoldiers <= 0) {
                return count;
            }
            xOurSoldiers -= pEnemySoldiers;
        }

        return count;
    }
}