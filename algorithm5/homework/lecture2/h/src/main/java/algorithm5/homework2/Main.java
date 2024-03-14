package algorithm5.homework2;

/**
Константин и Михаил играют в настольную игру «Ярость Эльфов». В игре есть n рас и m классов персонажей. 
Каждый персонаж характеризуется своими расой и классом. Для каждой расы и каждого класса существует ровно 
один персонаж такой расы и такого класса. Сила персонажа i-й расы и j-го класса равна ai j, и обоим игрокам это прекрасно известно.

Сейчас Константин будет выбирать себе персонажа. Перед этим Михаил может запретить одну расу и один класс, чтобы Константин не мог
 выбирать персонажей, у которых такая раса или такой класс. Конечно же, Михаил старается, чтобы Константину достался как можно более слабый персонаж, 
 а Константин, напротив, выбирает персонажа посильнее. Какие расу и класс следует запретить Михаилу?

Формат ввода
Первая строка содержит два целых числа n и m (2 ≤ n,m ≤ 1000) через пробел — количество рас и классов в игре «Ярость Эльфов», соответственно.

В следующих n строках содержится по m целых чисел через пробел. j-е число i-й из этих строк — это ai j (1 ≤ ai j ≤ 10^9).

Формат вывода
В единственной строке выведите два целых числа через пробел — номер расы и номер класса, которые следует запретить Михаилу. 
Расы и классы нумеруются с единицы. Если есть несколько возможных ответов, выведите любой из них.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
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
        int line = 0;
        int column = 0;

        List<Integer> ys = new ArrayList<>();
        List<Integer> xs = new ArrayList<>();
        ys.add(persons.get(0).getRaceY());
        xs.add(persons.get(0).getClassX());
        int secondY, secondX;

        for (int i = 1; i < persons.size() && (!isHorizontFind || !isVerticalFind); i++) {
            secondY = persons.get(i).getRaceY();
            secondX = persons.get(i).getClassX();
            if (!isHorizontFind && !isVerticalFind) {
                if (ys.contains(secondY)) {
                    column = secondY;
                    isVerticalFind = true;
                    int maybeLine = checkHorizontal(column, ys, xs);
                    if (maybeLine != -1) {
                        line = maybeLine;
                        isHorizontFind = true;
                    }

                } else if (xs.contains(secondX)) {
                    line = secondX;
                    isHorizontFind = true;
                    int maybeColumn = checkVertical(line, ys, xs);
                    if (maybeColumn != -1) {
                        column = maybeColumn;
                        isVerticalFind = true;
                    }
                }
                if (i == 1) {
                    ys.add(secondY);
                    xs.add(secondX);
                }
            } else if (isHorizontFind) {
                if (secondX != line) {
                    column = secondY;
                    isVerticalFind = true;
                }
            } else if (isVerticalFind) {
                if (secondY != column) {
                    line = secondX;
                    isHorizontFind = true;
                }
            }
        }
        return column + " " + line;
    }

    private static int checkVertical(int line, List<Integer> ys, List<Integer> xs) {
        int result = -1;
        for (int i = 0; i < xs.size(); i++) {
            if (xs.get(i) != line) {
                return ys.get(i);
            }
        }
        return result;
    }

    private static int checkHorizontal(int column, List<Integer> ys, List<Integer> xs) {
        int result = -1;
        for (int i = 0; i < ys.size(); i++) {
            if (ys.get(i) != column) {
                return xs.get(i);
            }
        }
        return result;
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