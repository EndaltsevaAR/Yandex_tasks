package algorithm5.homework1;

import java.util.Scanner;

public class Main {
    public static int xOurSoldiers = 0;
    public static int yBuilding = 0;
    public static int pEnemySoldiers = 0;
    public static int generatedEnemies = 0;
    public static int reservedCount = 0;

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
        int count = prepareCheck();
        if (count != 0) {
            return count;
        }
        count++;
        while (pEnemySoldiers > 0 && xOurSoldiers > 0) {
            count++;
            if (xOurSoldiers >= (pEnemySoldiers + yBuilding)) {
                return count;
            }
            if (yBuilding == 0) {
                killSoldiers();
            } else if (pEnemySoldiers == 0) {
                killBuilding();
            } else {
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
                    int weCanDestroyBuild = weCanDestroyBuildingAndSoldersNextStep(count);
                    if (weCanDestroyBuild > 0) {
                        if (reservedCount > 0) {
                            count = weCanDestroyBuild;
                            return Math.min(reservedCount, count);
                        } else {
                            reservedCount = weCanDestroyBuild;
                        }
                    }
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

        if (xOurSoldiers < 0) {
            count = -1;
        }
        if (reservedCount > 0) {
            if (count < 1) {
                return reservedCount;
            } else {
                return Math.min(reservedCount, count);
            }
        } else {
            return count;
        }

    }

    private static int weCanDestroyBuildingAndSoldersNextStep(int count) {
        int tempYBuild = yBuilding;
        int tempXOurSold = xOurSoldiers;
        int tempPEnemySold = pEnemySoldiers;

        int delta = tempXOurSold - tempYBuild;
        tempPEnemySold -= delta;
        tempXOurSold -= tempPEnemySold;
        while (tempPEnemySold > 0 && tempXOurSold > 0) {
            count++;
            tempPEnemySold -= tempXOurSold;
            if (tempPEnemySold > 0) {
                tempXOurSold -= tempPEnemySold;
            }
        }
        if (tempXOurSold <= 0) {
            return -1;
        } else {
            return count;
        }

    }

    private static int prepareCheck() {
        if (yBuilding - xOurSoldiers <= 0) { // мы разрушим казарму еще до начала генерации солдат протвника
            return 1;
        }

        yBuilding -= xOurSoldiers;

        // в первом ходу мы разрушили часть казармы, и теперь каждый ход вынуждены
        // просто убивать солдат противника, или те убьют/ наших, но есть шанс если
        // казарм осталось слишком мало, и мы сможем побить достаточно противников
        if (xOurSoldiers == pEnemySoldiers) {
            int weCanDestroy = weCanDestroyBuildingAndSoldersNextStep(1);
            if (weCanDestroy != -1) {
                return weCanDestroy + 1;
            } else {
                return -1;
            }
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
}