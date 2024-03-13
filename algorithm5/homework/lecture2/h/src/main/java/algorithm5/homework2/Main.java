package algorithm5.homework2;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
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

        TreeMap<String, Long> sortedPersons = new TreeMap<>(comparator.reversed());
        sortedPersons.putAll(persons);

        return findCrossMaxPowers(sortedPersons);

    }

    private static String findCrossMaxPowers(TreeMap<String, Long> sortedPersons) {
        boolean isHorizontFind = false;
        boolean isVerticalFind = false;
        Map.Entry<String, Long> currentEntry = sortedPersons.firstEntry();
        Map.Entry<String, Long> nextEntry = sortedPersons.higherEntry(currentEntry.getKey());

        List<int[]> coordinates = new ArrayList<>();
        coordinates.add(getCoordinates(currentEntry.getKey()));
        int[] nextCoordinates;

        int line = 0;
        int column = 0;

        while (!isHorizontFind || !isVerticalFind) {
            nextCoordinates = getCoordinates(nextEntry.getKey());

            if (!isHorizontFind && !isVerticalFind) {
                coordinates.add(nextCoordinates); // мы кладем в список только если не высчитали одну
                                                  // из координат
                if (coordinates.size() > 2) {
                    // попытка нахождения строки
                    for (int i = 1; i < coordinates.size() && !isHorizontFind; i++) {
                        if (coordinates.get(i)[0] == coordinates.get(0)[0]) {
                            line = coordinates.get(i)[0];
                            isHorizontFind = true;
                        }
                    }

                    // попытка нахождения столбца
                    for (int i = 1; i < coordinates.size() && !isVerticalFind; i++) {
                        if (isHorizontFind) {
                            if (coordinates.get(i)[0] != line) {
                                column = coordinates.get(i)[1];
                                isVerticalFind = true;
                            }
                        } else {
                            if (coordinates.get(i)[1] == coordinates.get(0)[1]) {
                                column = coordinates.get(i)[1];
                                isVerticalFind = true;
                                for (int j = 0; j < coordinates.size() && !isHorizontFind; j++) {
                                    if (coordinates.get(j)[1] != column) {
                                        line = coordinates.get(j)[0];
                                        isHorizontFind = true;
                                    }
                                }
                            }
                        }
                    }

                }
            } else if (isHorizontFind) {
                if (nextCoordinates[1] == line) {
                    coordinates.add(nextCoordinates);
                } else {
                    column = nextCoordinates[1];
                    isVerticalFind = true;
                }
            } else {
                if (nextCoordinates[0] == column) {
                    coordinates.add(nextCoordinates);
                } else {
                    line = nextCoordinates[0];
                    isHorizontFind = true;
                }
            }
            currentEntry = nextEntry;
            nextEntry = sortedPersons.higherEntry(currentEntry.getKey());
        }
        return line + " " + column;
    }

    private static int[] getCoordinates(String key) {
        int[] coordinates = new int[2];
        String[] keys = key.split(" ");
        for (int i = 0; i < keys.length; i++) {
            coordinates[i] = Integer.parseInt(keys[i]);
        }
        return coordinates;
    }
}