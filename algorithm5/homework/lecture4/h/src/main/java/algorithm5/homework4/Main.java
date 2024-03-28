package algorithm5.homework4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] partiesId = new int[n];
            Set<Party> allParties = new TreeSet<>(Comparator.comparingLong((Party p) -> p.votes));
            TreeSet<Party> corruptedParties = new TreeSet<>(
                    Comparator.comparingLong((Party p) -> p.totalCost).reversed());

            long totalVotes = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(reader.readLine());
                long votes = Long.parseLong(st.nextToken());
                long bribe = Long.parseLong(st.nextToken());
                Party party = new Party(i, votes, bribe);
                partiesId[i] = i;
                allParties.add(party);
                if (bribe != -1) {
                    corruptedParties.add(party);
                }
                totalVotes += votes;
            }
            System.out.println(getElection(totalVotes, partiesId, allParties, corruptedParties));

        }
    }

    private static String getElection(long totalVotes, int[] partiesId, Set<Party> allParties,
            TreeSet<Party> corruptedParties) {
        Party winner = corruptedParties.first();
        System.out.println("Party number is " + corruptedParties.first().id);
        long totalStartVotes = totalVotes / partiesId.length;
        long totalExtraMaxVotes = 0;
        int numberStrongPartes = 0;
        for (Party party : allParties) {
            if (!party.equals((winner)) && party.votes < totalStartVotes) {
                totalExtraMaxVotes += totalStartVotes - party.votes;
            } else {
                numberStrongPartes -= partiesId.length - 1 - (party.id); // минус самого победителя
                break;
            }
        }

        long extraVotes = 0;
        long firstParties = 0;
        if (numberStrongPartes != 0) {
            extraVotes = totalExtraMaxVotes / numberStrongPartes; // подумать про деление на ноль
            firstParties = totalExtraMaxVotes % numberStrongPartes;
        }

        int counterParties = 1;
        winner.votes = totalStartVotes + 1 + totalExtraMaxVotes;
        for (Party party : allParties) {
            if (!party.equals((winner)) && (party.votes >= totalStartVotes)) {
                party.votes = totalStartVotes - extraVotes;
                if (counterParties <= firstParties) {
                    party.votes--;
                }
            }
            counterParties++;
        }

        Set<Party> sortedPartiesById = new TreeSet<>(Comparator.comparingInt(party -> party.id));
        sortedPartiesById.addAll(allParties);

        StringBuilder builder = new StringBuilder();
        builder.append(totalStartVotes + 1 + totalExtraMaxVotes + winner.bribe).append("\n");
        builder.append(winner.id + 1).append("\n"); // номер партии
        for (Party party : sortedPartiesById) {
            builder.append(party.votes).append(" ");
        }

        return builder.toString().trim();
    }

}

class Party {
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

}