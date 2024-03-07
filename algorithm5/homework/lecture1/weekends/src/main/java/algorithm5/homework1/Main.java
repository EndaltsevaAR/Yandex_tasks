package algorithm5.homework1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
        int[] holidaysDayOfWeek = getDayOfWeekHolidays(year, holidays, firstDayOfWeek);
        sumHolidays(daysOfWeek, holidaysDayOfWeek);
        return getMaxAndMin(daysOfWeek);
    }

    private static Map<Integer, Integer> fullDayNameMap(int year, String firstDayOfWeek) {
        Map<Integer, Integer> daysOfWeek = new TreeMap<>();

        int numberOfDayOfWeek = getNumberOfDayOfWeek(firstDayOfWeek);

        for (int i = 0; i < 7; i++) {
            if (i == numberOfDayOfWeek || (i - 1 == numberOfDayOfWeek && isLeapYear(year))) { // 1 день - 53 дня, 2 дня
                                                                                              // - если високосный
                daysOfWeek.put(i, 53);
            } else {
                daysOfWeek.put(i, 52);
            }
        }
        return daysOfWeek;
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private static int getNumberOfDayOfWeek(String firstDayOfWeek) {
        switch (firstDayOfWeek) {
            case "Monday":
                return 0;
            case "Tuesday":
                return 1;
            case "Wednesday":
                return 2;
            case "Thursday":
                return 3;
            case "Friday":
                return 4;
            case "Saturday":
                return 5;
            default:
                return 6;
        }
    }

    private static int[] getDayOfWeekHolidays(int year, String[] holidays, String firstDayOfWeek) {
        int[] days = new int[holidays.length];
        for (int i = 0; i < days.length; i++) {
            int holidayDay = Integer.parseInt(holidays[i].split(" ")[0]);
            String holidayMonth = holidays[i].split(" ")[1];
            days[getHolidayDayOfWeek(holidayDay, holidayMonth, year, firstDayOfWeek)] += 1;

        }
        return days;

    }

    private static int getHolidayDayOfWeek(int holidayDay, String holidayMonth, int year, String firstDayOfWeek) {
        int dayOfYear = getNumberOfDayOfWeek(firstDayOfWeek);

        int monthNumber = getNumberOfMonth(holidayMonth);
        for (int i = 0; i < monthNumber; i++) {
            dayOfYear += geNumberOfDaysInAMonth(monthNumber, year);
        }
        dayOfYear += holidayDay;
        return (dayOfYear % 7) - 1; // ПРОВЕРИТЬ ПРАВИЛЬНО ЛИ ТУТ ПОЛУЧАЕТСЯ
    }

    private static int getNumberOfMonth(String holidayMonth) {
        switch (holidayMonth) {
            case "January":
                return 0;
            case "February":
                return 1;
            case "March":
                return 2;
            case "April":
                return 3;
            case "May":
                return 4;
            case "June":
                return 5;
            case "July":
                return 6;
            case "August":
                return 7;
            case "September":
                return 8;
            case "October":
                return 9;
            case "November":
                return 10;
            default:
                return 11;
        }
    }

    private static int geNumberOfDaysInAMonth(int monthName, int year) {
        switch (monthName) {
            case 0:
                return 31;
            case 1:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            case 2:
                return 31;
            case 3:
                return 30;
            case 4:
                return 31;
            case 5:
                return 30;
            case 6:
                return 31;
            case 7:
                return 31;
            case 8:
                return 30;
            case 9:
                return 31;
            case 10:
                return 30;
            default:
                return 31;
        }
    }

    private static void sumHolidays(Map<Integer, Integer> daysOfWeek, int[] holidaysDayOfWeek) {
        int sumHolidays = Arrays.stream(holidaysDayOfWeek).sum();
        for (int i = 0; i < holidaysDayOfWeek.length; i++) {
            daysOfWeek.put(i, daysOfWeek.get(i) + sumHolidays - holidaysDayOfWeek[i]);
        }
    }

    private static String getMaxAndMin(Map<Integer, Integer> daysOfWeek) {
        int maxKey = 0;
        int maxValue = daysOfWeek.get(0);
        int minKey = 0;
        int minValue = daysOfWeek.get(0);

        for (Map.Entry<Integer, Integer> pair : daysOfWeek.entrySet()) {
            if (pair.getValue() > maxValue) {
                maxValue = pair.getValue();
                maxKey = pair.getKey();
            }

            if (pair.getValue() < minValue) {
                minValue = pair.getValue();
                minKey = pair.getKey();
            }
        }

        return getDayOfWeekByNumber(maxKey) + " " + getDayOfWeekByNumber(minKey);

    }

    private static String getDayOfWeekByNumber(int maxKey) { // если надо будет ужать можно тут превратить в стринг
                                                             // билдер

        switch (maxKey) {
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Saturday";
            default:
                return "Sunday";
        }
    }
}
