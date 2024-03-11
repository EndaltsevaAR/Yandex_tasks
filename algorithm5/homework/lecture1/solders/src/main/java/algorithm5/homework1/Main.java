package algorithm5.homework1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static int xOurSoldiers = 0;
    public static int yBuilding = 0;
    public static int pEnemySoldiers = 0;
    public static int generatedEnemies = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        xOurSoldiers = Integer.parseInt(scanner.nextLine());
        yBuilding = Integer.parseInt(scanner.nextLine());
        pEnemySoldiers = Integer.parseInt(scanner.nextLine());
        generatedEnemies = pEnemySoldiers;
        System.out.println(getNumberSteps());
        scanner.close();
    }

    public static int getNumberSteps() {
        int count = prepareCheck(xOurSoldiers, yBuilding, pEnemySoldiers);
        if (count != 0) {
            return count;
        }

        while (pEnemySoldiers > 0 || xOurSoldiers > 0) {
            count++;
            if (xOurSoldiers >= (pEnemySoldiers + yBuilding)) {
                return count;
            }
            if (yBuilding == 0) {
                killSoldiers();
            } else if (pEnemySoldiers == 0) {
                killBuilding();
            } else {
                if (pEnemySoldiers - xOurSoldiers > 0 && generatedEnemies - xOurSoldiers > 0
                        && yBuilding > xOurSoldiers) {
                    return -1;
                }

                if (yBuilding > xOurSoldiers) {
                    int delta = xOurSoldiers - pEnemySoldiers;
                    if (delta >= 0) {
                        pEnemySoldiers = 0;
                        yBuilding -= delta;
                    } else {
                        pEnemySoldiers -= xOurSoldiers;
                        xOurSoldiers -= pEnemySoldiers;
                    }
                    pEnemySoldiers += generatedEnemies;
                } else {
                    if (weCanDestroyBuildingAndSoldersNextStep()) {
                        int delta = xOurSoldiers - yBuilding;
                        yBuilding = 0;
                        pEnemySoldiers -= (xOurSoldiers - delta);
                        xOurSoldiers -= pEnemySoldiers;
                    } else {
                        if (pEnemySoldiers - xOurSoldiers <= 0 && generatedEnemies - xOurSoldiers > 0) {
                            int delta = xOurSoldiers - pEnemySoldiers;
                            if (delta >= 0) {
                                pEnemySoldiers = 0;
                                yBuilding -= delta;
                            } else {
                                pEnemySoldiers -= xOurSoldiers;
                                xOurSoldiers -= pEnemySoldiers;
                            }
                            pEnemySoldiers += generatedEnemies;
                        }
                    }
                }
            }
        }

        if (xOurSoldiers < 0) {
            return -1;
        }
        return count;
    }

    private static boolean weCanDestroyBuildingAndSoldersNextStep() {
        int tempYBuild = yBuilding;
        int tempXOurSold = xOurSoldiers;
        int tempPEnemySold = pEnemySoldiers;

        int delta = xOurSoldiers - yBuilding;
        pEnemySoldiers -= (xOurSoldiers - delta);
        xOurSoldiers -= pEnemySoldiers;
        if (xOurSoldiers * 2 > pEnemySoldiers) {
            return true;
        } else {
            return false;
        }

    }

    private static int prepareCheck(int xOurSoldiers, int yBuilding, int pEnemySoldiers) {
        if (yBuilding - xOurSoldiers <= 0) { // мы разрушим казарму еще до начала генерации солдат протвника
            return 1;
        }

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
        return 0;

    }

    private static void killSoldiers() {
        pEnemySoldiers -= xOurSoldiers;
        if (pEnemySoldiers > 0) {
            xOurSoldiers -= pEnemySoldiers;
        }
    }

    private static void killBuilding() {
        yBuilding -= xOurSoldiers;
        if (yBuilding > 0) {
            pEnemySoldiers += generatedEnemies;
        }
    }

    // public static int getNumberSteps(int xOurSoldiers, int yBuilding, int
    // pEnemySoldiers) {
    // TreeSet<Integer> possibleSteps = new TreeSet<>();
    // boolean isSomeOptions = false;
    // int enemyGeneration = pEnemySoldiers;
    // if (yBuilding - xOurSoldiers <= 0) { // мы разрушим казарму еще до начала
    // генерации солдат протвника
    // return 1;
    // }
    // // prepare
    // int count = 1;
    // yBuilding -= xOurSoldiers;

    // // в первом ходу мы разрушили часть казармы, и теперь каждый ход вынуждены
    // // просто убивать солдат противника, или те убьют/ наших
    // if (xOurSoldiers == yBuilding && xOurSoldiers == pEnemySoldiers) {
    // return -1;
    // }

    // // если мы не успеем разрушить казарму за два хода, то противников станет
    // больше
    // // наших
    // if (yBuilding * 1.5 > xOurSoldiers && pEnemySoldiers > xOurSoldiers) {
    // return -1;
    // }

    // if (xOurSoldiers == yBuilding + pEnemySoldiers) {
    // return 2;
    // }

    // // общая

    // while ((yBuilding + pEnemySoldiers) / (double) xOurSoldiers > 1.7) {
    // count++;
    // int deltaEnemy = (xOurSoldiers - pEnemySoldiers);
    // if (deltaEnemy > 0) {
    // yBuilding -= deltaEnemy;
    // pEnemySoldiers = 0;
    // } else {
    // pEnemySoldiers -= xOurSoldiers;
    // xOurSoldiers -= Math.abs(deltaEnemy);
    // }
    // pEnemySoldiers += enemyGeneration;
    // }

    // if ((yBuilding + pEnemySoldiers) / (double) xOurSoldiers >= 1.6
    // && (yBuilding + pEnemySoldiers) / (double) xOurSoldiers < 1.7) {
    // isSomeOptions = true;
    // }
    // // снос казармы
    // if (!isSomeOptions) { // не пограничный случай для сечения
    // int delta = yBuilding;
    // while (pEnemySoldiers > 0) {
    // count++;
    // pEnemySoldiers -= (xOurSoldiers - delta);
    // if (pEnemySoldiers <= 0) {
    // return count;
    // }
    // xOurSoldiers -= pEnemySoldiers;
    // if (xOurSoldiers <= 0) { // на всякий случай можно рассмотреть поражение,
    // если я забыла какое-то краевое
    // // в начале, здесь должно его вырулить
    // return -1;
    // }
    // delta = 0;
    // }
    // return count;
    // } else { // пограничный случай для сечения, стоит проверить несколько
    // вариантов
    // int stepsInCircle = 0;
    // int xOurSoldiersBoolean = xOurSoldiers;
    // while (xOurSoldiersBoolean > 0 || possibleSteps.isEmpty()) {
    // int countBoolean = count;
    // xOurSoldiersBoolean = xOurSoldiers;
    // int yBuildingBoolean = yBuilding;
    // int pEnemySoldiersBoolean = pEnemySoldiers;
    // for (int i = 0; i < stepsInCircle && pEnemySoldiersBoolean > 0 &&
    // xOurSoldiersBoolean > 0; i++) {
    // countBoolean++;
    // int deltaEnemy = (xOurSoldiersBoolean - pEnemySoldiersBoolean);
    // if (deltaEnemy > 0) {
    // yBuildingBoolean -= deltaEnemy;
    // pEnemySoldiersBoolean = 0;
    // } else {
    // pEnemySoldiersBoolean -= xOurSoldiersBoolean;
    // xOurSoldiersBoolean -= Math.abs(deltaEnemy);
    // }
    // pEnemySoldiersBoolean += enemyGeneration;
    // }

    // int delta = yBuildingBoolean;
    // while (pEnemySoldiersBoolean > 0 && xOurSoldiersBoolean > 0) {
    // countBoolean++;
    // pEnemySoldiersBoolean -= (xOurSoldiersBoolean - delta);
    // if (pEnemySoldiersBoolean <= 0) {
    // continue;
    // } else {
    // xOurSoldiersBoolean -= pEnemySoldiersBoolean;
    // if (xOurSoldiersBoolean <= 0) { // на всякий случай можно рассмотреть
    // поражение, если я забыла
    // // какое-то краевое
    // // в начале, здесь должно его вырулить
    // break;
    // }
    // delta = 0;
    // }

    // }
    // if (xOurSoldiersBoolean > 0) {
    // possibleSteps.add(countBoolean);
    // }

    // stepsInCircle++;
    // if (!possibleSteps.isEmpty()) {

    // }
    // }
    // return possibleSteps.first();
    // }
    // }
}