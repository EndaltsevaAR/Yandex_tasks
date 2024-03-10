package algorithm5.homework2;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        String nm = scanner.nextLine();
        int nNumberRaces = Integer.parseInt(nm.split(" ")[0]);
        int mNumberClasses = Integer.parseInt(nm.split(" ")[1]);
        String[] sPersons = new String[nNumberRaces];
        for (int i = 0; i < sPersons.length; i++) {
            sPersons[i] = scanner.nextLine();
        }
        System.out.println(getMaxPerson(sPersons, mNumberClasses));
        scanner.close();
    }

    public static String getMaxPerson(String[] sPersons, int mNumberClasses) {
        TreeMap<String, Long> persons = new TreeMap<>();
        for (int i = 0; i < sPersons.length; i++) {
            String[] line = sPersons[i].split(" ");
            for (int j = 0; j < mNumberClasses; j++) {
                persons.put((i + 1) + " " + (j + 1), Long.parseLong(line[j]));
            }
        }

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String key1, String key2) {
                return persons.get(key1).compareTo(persons.get(key2));
            }
        };

        TreeMap<String, Long> sortedPersons = new TreeMap<>(comparator);
        sortedPersons.putAll(persons);

        boolean isFind = false;
        Map.Entry<String, Long> currentEntry = sortedPersons.firstEntry();
        Map.Entry<String, Long> nextEntry = sortedPersons.higherEntry(currentEntry.getKey());
        String answer = currentEntry + "/" + nextEntry;
        while (!isFind) {
            if (isCross(answer)) {
                return answer;
            } else {
                currentEntry = nextEntry;
                nextEntry = sortedPersons.higherEntry(currentEntry.getKey());
                answer += nextEntry.getKey();
            }
        }
        return "";
    }

    private static boolean isCross(String answer) {
        boolean isAnswer = false;
        String[] coordinates = answer.split("/");
        int y[] = new int[coordinates.length];
        int x[] = new int[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            y[i] = Integer.parseInt(coordinates[i].split(" ")[0]);
            x[i] = Integer.parseInt(coordinates[i].split(" ")[1]);
        }
        //
        Set<Integer> ys = new HashSet<>();
        Set<Integer> xs = new HashSet<>();
        for (int i = 1; i < x.length; i++) {
            // ys.
        }
        return false;
    }

    // Map.Entry<String, Long> currentEntry = sortedPersons.firstEntry();
    // Map.Entry<String, Long> nextEntry =
    // sortedPersons.higherEntry(currentEntry.getKey());
    // Map<String, Boolean> maxPositions = new HashMap<>();
    // List<String> positionAtStep = new ArrayList<>();
    // positionAtStep.add(currentEntry.getKey());
    // long currentMax = currentEntry.getValue();
    // long lowerMax = nextEntry.getValue();

    // while (!isFind) {
    // while (currentMax == lowerMax && nextEntry != null) {
    // positionAtStep.add(nextEntry.getKey());
    // currentEntry = nextEntry;
    // nextEntry = sortedPersons.higherEntry(currentEntry.getKey());
    // currentMax = lowerMax;
    // lowerMax = nextEntry.getValue();
    // }
    // processing(maxPositions, positionAtStep);
    // positionAtStep = new ArrayList<>();

    // }

    // private static void processing(Map<String, Boolean> maxPositions,
    // List<String> positionAtStep) {
    // List<String> totalList = new ArrayList<>(maxPositions.keySet());
    // if (totalList.isEmpty()) {
    // for (int i = 0; i < positionAtStep.size(); i++) {
    // maxPositions.put(positionAtStep.get(i), false);
    // }
    // int steps = positionAtStep.size() - 1;
    // while (steps > 0) {
    // for (int i = 0; i < maxPositions.size(); i++) {

    // }
    // }
    // }
    // }

}