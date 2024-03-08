package algorithm5.homework2;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static class Berry {
        private int id;
        private BigInteger strength; // up - down
        private BigInteger up;
        private BigInteger down;

        public Berry(int id, BigInteger up, BigInteger down) {
            this.id = id;
            this.up = up;
            this.down = down;
            this.strength = up.subtract(down);
        }

        public int getId() {
            return id;
        }

        public BigInteger getStrength() {
            return strength;
        }

        public BigInteger getUp() {
            return up;
        }

        public BigInteger getDown() {
            return down;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int berriesCount = Integer.parseInt(scanner.nextLine());
        String[] berries = new String[berriesCount];
        for (int i = 0; i < berries.length; i++) {
            berries[i] = scanner.nextLine();
        }
        System.out.println(snailGo(berries));
        scanner.close();
    }

    public static String snailGo(String[] sBerries) {
        List<Berry> berriesWithPosStrength = new ArrayList<>();
        List<Berry> berriesWithNegStrength = new ArrayList<>();

        BigInteger delimetrMaxUp = BigInteger.ZERO;
        Berry delimetr = new Berry(0, BigInteger.ZERO, BigInteger.ZERO);
        for (int i = 0; i < sBerries.length; i++) {
            String[] berryData = sBerries[i].split(" ");
            Berry berry = new Berry(i, new BigInteger(berryData[0]), new BigInteger(berryData[1]));
            if (berry.getStrength().compareTo(BigInteger.ZERO) >= 0) {
                berriesWithPosStrength.add(berry);
            } else {
                if (berry.getUp().compareTo(delimetrMaxUp) > 0) {
                    delimetrMaxUp = berry.getUp();
                    delimetr = berry;
                }
                berriesWithNegStrength.add(berry);
            }
        }

        Collections.sort(berriesWithPosStrength, (berry1, berry2) -> {
            int compareUp = berry2.getUp().compareTo(berry1.getUp());
            if (compareUp != 0) {
                return compareUp; // Сортировка по up
            } else {
                return berry1.getDown().compareTo(berry2.getDown()); // Сортировка по down
            }
        });

        StringBuilder answer = new StringBuilder();
        BigInteger maxHeight = BigInteger.ZERO;
        BigInteger currentHeight = BigInteger.ZERO;

        for (Berry berry : berriesWithPosStrength) {
            answer.append(berry.id + 1).append(" ");
            currentHeight = currentHeight.add(berry.getUp());
            if (maxHeight.compareTo(currentHeight) < 0) {
                maxHeight = currentHeight;
            }
            currentHeight = currentHeight.subtract(berry.getDown());
        }

        if (!berriesWithNegStrength.isEmpty()) {
            answer.append(delimetr.getId() + 1).append(" ");
            currentHeight = currentHeight.add(delimetr.getUp());
            if (maxHeight.compareTo(currentHeight) < 0) {
                maxHeight = currentHeight;
            }
            berriesWithNegStrength.remove(delimetr);
        }

        for (Berry berry : berriesWithNegStrength) {
            answer.append(berry.id + 1).append(" ");
        }

        return (maxHeight + "\n" + answer.toString()).trim();
    }
}
