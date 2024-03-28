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
            int n = Integer.parseInt(st.nextToken());
            Party[] parties = new Party[n];
            long totalVotes = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(reader.readLine());
                long votes = Long.parseLong(st.nextToken());
                long bribe = Long.parseLong(st.nextToken());
                totalVotes += votes;
                parties[i] = new Party(i, votes, bribe);

            }
            System.out.println(getElection(n, totalVotes, parties));

        }
    }

    public static String getElection(int n, long totalVotes, Party[] parties) {
        // if (parties.length == 1) {
        // return isTotalitarizm(parties);
        // }

        Arrays.sort(parties);
        Party winner = parties[0];
        Party[] partiesWithoutWinners = new Party[n - 1];
        System.arraycopy(parties, 0, partiesWithoutWinners, 0, winner.id);
        for (int i = parties.length; i > winner.id; i--) {
            partiesWithoutWinners[i - 1] = parties[i];
        }

        Arrays.sort(partiesWithoutWinners, Comparator.comparingLong(party -> party.votes));
        long[] prefixs = createPrefix(partiesWithoutWinners, n);
        long left = 0;
        long right = totalVotes;

        while (left < right) {
            long med = (left + right) / 2;
            if (!isPartyLose(winner, med, partiesWithoutWinners, prefixs)) {
                right = med;
            } else {
                left = right - 1;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(left + winner.bribe).append("\n");
        builder.append(winner.id).append("\n");

        // перераспределение
        winner.votes += left;

        if (partiesWithoutWinners.length == 1) {
            partiesWithoutWinners[0].votes -= left;
        } else if (partiesWithoutWinners.length > 1) {
            int index = 1;
            while (left > 0) {
                long nextAfterHighestVotes = partiesWithoutWinners[index].votes;
                for (int i = 0; partiesWithoutWinners[i].votes > nextAfterHighestVotes && left > 0; i++) {
                    long delta = partiesWithoutWinners[i].votes - nextAfterHighestVotes;
                    partiesWithoutWinners[i].votes -= Math.min(left, delta);
                    left -= Math.min(left, delta);
                }
                index++;
            }
        }

        for (int i = 0; i < partiesWithoutWinners.length; i++) {
            builder.append(partiesWithoutWinners[i].votes).append(" ");
        }
        builder.append(winner.votes);
        for (int i = winner.id + 1; i < partiesWithoutWinners.length; i++) {
            builder.append(partiesWithoutWinners[i].votes).append(" ");
        }

        return builder.toString().trim();
    }

    private static boolean isPartyLose(Party winner, long med, Party[] parties, long[] prefixs) {
        long line = winner.votes + med - 1;
        int indexLineParty = getParty(parties, line, prefixs);
        return (prefixs[indexLineParty] - (line * (indexLineParty + 1)) >= med);
    }

    private static int getParty(Party[] parties, long line, long[] prefixs) {
        int left = 0;
        int right = parties.length - 1;
        while (left < right) {
            int med = (left + right) / 2;
            if (parties[med].votes >= line) {
                right = med;
            } else {
                left = med + 1;
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
        return Long.compare(other.totalCost, this.totalCost);
    }
}