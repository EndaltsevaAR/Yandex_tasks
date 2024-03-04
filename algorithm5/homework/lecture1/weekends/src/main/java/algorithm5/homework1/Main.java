package algorithm5.homework1;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.time.DayOfWeek;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int nNumberHolidays = Integer.parseInt(scanner.nextLine());
        int year = Integer.parseInt(scanner.nextLine());
        String[] holidays = new String[nNumberHolidays];
        for (int i = 0; i < holidays.length; i++) {
            holidays[i] = scanner.nextLine();
        }
        String firstDayOfWeek = scanner.nextLine();
        System.out.println(getRestDayName(year, holidays, firstDayOfWeek));
        scanner.close();
    }

    public static String getRestDayName(int year, String[] holidays, String firstDayOfWeek) {
        Map<Integer, Integer> daysOfWeek = fullDayNameMap(year, firstDayOfWeek);
        int[] holidaysDayOfWeek = getDayOfWeekHolidays(year, holidays);

        for (Map.Entry<Integer, Integer> pair : daysOfWeek.entrySet()) {
            daysOfWeek.put(pair.getKey(),
                    pair.getValue() + getVacationsAtAnotherDays(pair.getKey(), holidaysDayOfWeek));
        }

        int maxDays = daysOfWeek.get(0);
        int maxDayOfWeekNumber = 1;
        int minDays = daysOfWeek.get(0);
        int minDayOfWeekNumber = 1;

        for (Map.Entry<Integer, Integer> pair : daysOfWeek.entrySet()) {
            if (pair.getValue() >= maxDays) {
                maxDays = pair.getValue();
                maxDayOfWeekNumber = pair.getKey();
            }

            if (pair.getValue() <= minDays) {
                minDays = pair.getValue();
                minDayOfWeekNumber = pair.getKey();
            }
        }

        String maxAnswer = DayOfWeek.of(maxDayOfWeekNumber).toString().toLowerCase();
        String minAnswer = DayOfWeek.of(minDayOfWeekNumber).toString().toLowerCase();

        return (Character.toUpperCase(maxAnswer.charAt(0)) + maxAnswer.substring(1) + " "
                + Character.toUpperCase(minAnswer.charAt(0)) + minAnswer.substring(1)).trim();
    }

    private static Integer getVacationsAtAnotherDays(Integer key, int[] holidaysDayOfWeek) {
        int vacations = 0;
        for (int i = 0; i < holidaysDayOfWeek.length; i++) {
            if (holidaysDayOfWeek[i] != key) {
                vacations++;
            }
        }
        return vacations;
    }

    private static int[] getDayOfWeekHolidays(int year, String[] holidays) {
        int[] days = new int[holidays.length];
        for (int i = 0; i < days.length; i++) {
            int day = Integer.parseInt(holidays[i].split(" ")[0]);
            int month = getMonthNumber(holidays[i].split(" ")[1]);
            LocalDate date = LocalDate.of(year, month, day);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            int dayOfWeekNumber = dayOfWeek.getValue();
            days[i] = dayOfWeekNumber;
        }
        return days;
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private static Map<Integer, Integer> fullDayNameMap(int year, String firstDayOfWeek) {
        Map<Integer, Integer> daysOfWeek = new TreeMap<>();

        int numberDayAtYear = 365;
        if (isLeapYear(year)) {
            numberDayAtYear = 366;
        }

        DayOfWeek dayOfWeek = DayOfWeek.valueOf(firstDayOfWeek.toUpperCase());
        int dayNumber = dayOfWeek.getValue() - 1;

        int numberDay53 = numberDayAtYear % 7;
        for (int i = 0; i < 7; i++) {
            if (i >= dayNumber && i < dayNumber + numberDay53) {
                daysOfWeek.put(i, 53);
            } else {
                daysOfWeek.put(i, 52);
            }
        }
        return daysOfWeek;
    }

    private static int getMonthNumber(String monthName) {
        Month month = Month.valueOf(monthName.toUpperCase());
        return month.getValue();
    }
}