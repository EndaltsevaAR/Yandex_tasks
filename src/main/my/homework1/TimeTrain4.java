package main.my.homework1;
/*
Description:
Разница во времени
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Каждые сутки на вокзал прибывает n электричек. По заданному расписанию прибытия электричек определите минимальное
время между прибытием двух разных электричек.

Формат ввода
В первой строке задано число n (1 ≤ n ≤ 2 × 104) — количество электричек.

Во второй строке задано n моментов времени в формате HH:MM (0 ≤ HH ≤ 23, 0 ≤ MM ≤ 59) через пробел.

Формат вывода
Выведите одно число — минимальное время в минутах между прибытием двух электричек.
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TimeTrain4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int numberTrain = Integer.parseInt(line);
        String lineTrains = scanner.nextLine();
        System.out.println(getMinDiffTime(lineTrains));
        scanner.close();
    }

    public static int getMinDiffTime(String lineTrains) {
        String[] stringTimeTrains = lineTrains.split(" ");
        List<Integer> timeTrains = new ArrayList<>();
        for (String time:stringTimeTrains) {
            String[] temp = time.split(":");
            timeTrains.add(Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]));
        }

        if (timeTrains.size() == 1) {
            return 0;
        }

        timeTrains.sort(Comparator.naturalOrder());
        int min = Math.min((timeTrains.get(1) - timeTrains.get(0)), Math.abs(1440 - (timeTrains.get(1) - timeTrains.get(0))));
        for (int i = 1; i < timeTrains.size(); i++) {
            int tempDiff = timeTrains.get(i) - timeTrains.get(i - 1);
            if (tempDiff < min) {
                min = tempDiff;
            } else if (Math.abs(1440 - tempDiff) < min) {
                 min = Math.abs(1440 - tempDiff);
            }
        }
        // check first and last
        int tempDiff = Math.min((timeTrains.get(timeTrains.size() - 1) - timeTrains.get(0)), Math.abs(1440 - (timeTrains.get(timeTrains.size() - 1) - timeTrains.get(0))));
        if (tempDiff < min) {
            min = tempDiff;
        }
        return min;
    }
}
