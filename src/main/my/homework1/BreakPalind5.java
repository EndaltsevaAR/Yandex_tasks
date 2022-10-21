package main.my.homework1;


/*
Description:
Сломай палиндром
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Палиндромом называется строка, которая читается одинаково слева-направо и справа-налево.

В заданной строке-палиндроме необходимо заменить один символ, чтобы она перестала быть палиндромом.
При этом полученная строка должна быть лексикографически минимальной.

Строка A лексикографически меньше строки B (той же длины), если на первой различающейся позиции в строке A стоит
меньший символ, чем в B. Например, строка adbc меньше строки adca, т.к. первые два символа в строках совпадают, а на третьем месте в строке adbc стоит символ b, который меньше символа c, стоящего на третьей позиции в строке adca.

Формат ввода
Вводится строка-палиндром, состоящая из маленьких букв латинского алфавита. Длина строки не превосходит 1000.

Формат вывода
Выведите лексикографически минимальную строку, не являющуюяся палиндромом, полученную из исходной строки заменой
одного символа.

Если получить такую строку невозможно - выведите пустую строку.
 */

import java.util.Scanner;

public class BreakPalind5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(getAnotherWord(line));
        scanner.close();
    }

    public static String getAnotherWord(String line) {
        String answer = "";
        boolean isFind = false;
        if (line.length() < 2) {
            return "";
        }
        for (int i = 0; i < line.length()/ 2 && !isFind; i++) {
            if (line.charAt(i) != 'a') {
                answer = line.substring(0, i) + 'a' + line.substring(i+1);
                isFind = true;
            }
        }
        if (!isFind) {
            answer = line.substring(0, line.length() -1) + "b";
        }
        return answer;
    }
}
