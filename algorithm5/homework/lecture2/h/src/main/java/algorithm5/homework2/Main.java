package algorithm5.homework2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
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
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < sPersons.length; i++) {
            String[] line = sPersons[i].split(" ");
            for (int j = 0; j < mNumberClasses; j++) {
                Person person = new Person(i + 1, j + 1, Long.parseLong(line[j]));
                persons.add(person);
            }
        }

        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Long.compare(p2.getPower(), p1.getPower());
            }
        });

        return findCrossMaxPowers(persons);
    }

    private static String findCrossMaxPowers(List<Person> persons) {
        boolean isHorizontFind = false;
        boolean isVerticalFind = false;

        int personCounter = 1;
        Person nextPerson = persons.get(personCounter);

        List<int[]> coordinates = new ArrayList<>();
        coordinates.add(getCoordinates(persons.get(0)));

        int[] nextCoordinates;

        int line = 0;
        int column = 0;

        while (!isHorizontFind || !isVerticalFind) {
            nextCoordinates = getCoordinates(nextPerson);

            // check lines
            if (!isHorizontFind && !isVerticalFind) {
                coordinates.add(nextCoordinates);

                if (coordinates.size() > 2) {
                    Set<Integer> ys = new HashSet<>();
                    ys.add(coordinates.get(0)[0]);
                    // попытка нахождения строки
                    for (int i = 1; i < coordinates.size() && !isHorizontFind; i++) {
                        if (ys.contains(coordinates.get(i)[0])) {
                            line = coordinates.get(i)[0];
                            isHorizontFind = true;
                        } else {
                            ys.add(coordinates.get(i)[0]);
                        }
                    }

                    // попытка нахождения столбца
                    Set<Integer> xs = new HashSet<>();
                    for (int i = 0; i < coordinates.size() && !isVerticalFind; i++) {

                        if (isHorizontFind) {
                            if (coordinates.get(i)[0] != line) {
                                column = coordinates.get(i)[1];
                                isVerticalFind = true;
                            }
                        } else {
                            if (xs.contains(coordinates.get(i)[1])) {
                                column = coordinates.get(i)[1];
                                isVerticalFind = true;
                                for (int j = 0; j < coordinates.size() && !isHorizontFind; j++) {
                                    if (coordinates.get(j)[1] != column) {
                                        line = coordinates.get(j)[0];
                                        isHorizontFind = true;
                                    }
                                }
                            } else {
                                xs.add(coordinates.get(i)[1]);
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
            nextPerson = persons.get(++personCounter);
        }
        return line + " " + column;
    }

    private static int[] getCoordinates(Person person) {
        int[] coordinates = new int[2];
        coordinates[0] = person.getRaceY();
        coordinates[1] = person.getClassX();
        return coordinates;
    }

}

class Person {
    private int raceY;
    private int classX;
    private long power;

    public Person(int raceY, int classX, long power) {
        this.raceY = raceY;
        this.classX = classX;
        this.power = power;
    }

    public int getRaceY() {
        return raceY;
    }

    public int getClassX() {
        return classX;
    }

    public long getPower() {
        return power;
    }
}