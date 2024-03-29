package algorithm5.homework4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("51"))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            Party[] parties = new Party[n];
            long totalVotes = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(reader.readLine());
                long votes = Long.parseLong(st.nextToken());
                long bribe = Long.parseLong(st.nextToken());
                if (bribe == -1) {
                    bribe = 10000001L;
                }
                totalVotes += votes;
                parties[i] = new Party(i, votes, bribe);

            }
            System.out.println(getElection(n, totalVotes, parties));

        }
    }

    public static String getElection(int n, long totalVotes, Party[] parties) {
        Arrays.sort(parties);
        Party winner = parties[0];
        if (parties.length == 1) {
            return winner.bribe + "\n" + (winner.id + 1) + "\n" + winner.votes;
        }

        Party[] partiesWithoutWinners = new Party[n - 1]; // возможно потом вынести вотдельную функцию
        System.arraycopy(parties, 1, partiesWithoutWinners, 0, n - 1);
        Arrays.sort(partiesWithoutWinners, Comparator.comparingLong((Party party) -> party.votes).reversed());
        long[] prefixs = createPrefix(partiesWithoutWinners, n - 1);
        long left = 0;
        long right = totalVotes;
        if (winner.votes <= partiesWithoutWinners[0].votes) {
            while (left < right) {
                long med = (left + right) / 2;
                if (isPartyWin(winner, med, partiesWithoutWinners, prefixs)) {
                    right = med;
                } else {
                    left = med + 1;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(left + winner.bribe).append("\n");
        builder.append(winner.id + 1).append("\n");

        // перераспределение
        winner.votes += left;

        if (partiesWithoutWinners.length == 1) {
            partiesWithoutWinners[0].votes -= left;
        } else if (partiesWithoutWinners.length > 1) {
            // int index = 1;
            // while (left > 0 && index < partiesWithoutWinners.length) { ////// убрать
            // вторую часть условия
            // long nextAfterHighestVotes = partiesWithoutWinners[index].votes;
            // for (int i = 0; partiesWithoutWinners[i].votes > nextAfterHighestVotes &&
            // left > 0; i++) {
            // long delta = partiesWithoutWinners[i].votes - nextAfterHighestVotes;
            // partiesWithoutWinners[i].votes -= Math.min(left, delta);
            // left -= Math.min(left, delta);
            // }
            // index++;
            // }
            for (int i = 1; i < partiesWithoutWinners.length; i++) {
                if (left == 0) {
                    break;
                }
                if (partiesWithoutWinners[i].votes - partiesWithoutWinners[i - 1].votes > 0) {
                    long delta = partiesWithoutWinners[i].votes - partiesWithoutWinners[i - 1].votes;
                    partiesWithoutWinners[i].votes -= Math.min(left, delta);
                    left -= Math.min(left, delta);

                } else {
                    break;
                }
            }
            if (left > 0) {

                while (left > 0) {
                    partiesWithoutWinners[0].votes--;
                    left--;
                    for (int i = 1; i < partiesWithoutWinners.length; i++) {
                        if (left == 0) {
                            break;
                        }
                        if (partiesWithoutWinners[i].votes - partiesWithoutWinners[i - 1].votes > 0) {
                            long delta = partiesWithoutWinners[i].votes - partiesWithoutWinners[i - 1].votes;
                            partiesWithoutWinners[i].votes -= delta;
                            left -= delta;

                        } else {
                            break;
                        }
                    }
                }
            }
        }
        Arrays.sort(partiesWithoutWinners, Comparator.comparingLong((Party party) -> party.id));

        // для дебага
        // int ier = -1;
        // for (int i = 0; i < partiesWithoutWinners.length; i++) {
        // if (partiesWithoutWinners[i].votes >= winner.votes) {
        // ier = i;
        // }
        // }

        for (int i = 0; i < winner.id; i++) {
            builder.append(partiesWithoutWinners[i].votes).append(" ");
        }
        builder.append(winner.votes).append(" ");
        for (int i = winner.id; i < partiesWithoutWinners.length; i++) {
            builder.append(partiesWithoutWinners[i].votes).append(" ");
        }

        String content = builder.toString().trim();
        // для дебага

        // // Имя файла, в который мы хотим записать строку
        // String fileName = "output.txt";

        // try {
        // // Создаем объект File с указанием имени файла
        // File file = new File(fileName);

        // // Создаем объект FileWriter с указанием объекта File
        // FileWriter fw = new FileWriter(file);

        // // Создаем объект BufferedWriter для записи в файл
        // BufferedWriter bw = new BufferedWriter(fw);

        // // Записываем строку в файл
        // bw.write(content);

        // // Закрываем BufferedWriter
        // bw.close();

        // System.out.println("Строка успешно записана в файл " + fileName);
        // } catch (IOException e) {
        // // Обработка исключения, если произошла ошибка ввода-вывода
        // e.printStackTrace();
        // }
        return content;
    }

    private static boolean isPartyWin(Party winner, long med, Party[] parties, long[] prefixs) {
        long line = winner.votes + med - 1;
        int indexLineParty = getParty(parties, line, prefixs);
        return (prefixs[indexLineParty + 1] - (line * (indexLineParty + 1)) <= med);
    }

    private static int getParty(Party[] parties, long line, long[] prefixs) {
        if (line > prefixs[1]) {
            return 0;
        }
        int left = 0;
        int right = parties.length - 1;
        while (left < right) {
            int med = (left + right + 1) / 2;
            if (parties[med].votes > line) {
                left = med;
            } else {
                right = med - 1;
            }
        }
        return left;
    }

    private static long[] createPrefix(Party[] parties, int n) {
        long[] prefixs = new long[n + 1];
        for (int i = 1; i < prefixs.length; i++) {
            prefixs[i] = prefixs[i - 1] + parties[i - 1].votes;
        }
        return prefixs;
    }

}

class Party implements Comparable<Party> {
    int id;
    long votes;
    long bribe;
    long totalCost;

    public Party(int id, long votes, long bribe) {
        this.id = id;
        this.votes = votes;
        this.bribe = bribe;
        totalCost = votes - bribe;
    }

    @Override
    public int compareTo(Party other) {
        int compare = Long.compare(other.totalCost, this.totalCost);
        if (compare == 0) {
            compare = Long.compare(this.votes, other.votes);
        }
        return compare;
    }
}