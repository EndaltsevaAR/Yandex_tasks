package main.lecture.secondLinearSearch;
/*
Description:
Дана строка (возможно, пустая), состоящая из букв A-Z:
AAAABBBCCXYZDDD...
Нужно написать функцию RLE, которая на выходе даст строку вида:
A4B3C2XYZD3 и сгенерирует ошибку, если на вход пришла невалидная строка.
Если символ встречается 1 раз, цифра не ставится после нее, если символ
встречается более 1 раза подряд, после буквы ставится количество повторений
 */

import java.util.Scanner;

public class RLE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        System.out.println(rle(line));
    }

    private static String rle(String line) {
        if (line == null || line.length() == 0) {
            System.out.println("Error");
            System.exit(0);
        }
        char lastLetter = line.charAt(0);
        int last_position = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isLetter(line.charAt(i))) {
                System.out.println("Part of word is not letter");
                System.exit(0);
            }
            if (line.charAt(i) != lastLetter) {
                stringBuilder.append(lastLetter);
                if (i - last_position > 1) {
                    stringBuilder.append(i - last_position);
                }
                last_position = i;
                lastLetter = line.charAt(i);
            }
        }
        stringBuilder.append(line.charAt(last_position));
        if(line.length() - last_position > 1) {
            stringBuilder.append(line.length() - last_position);
        }
        return stringBuilder.toString();
    }
}
