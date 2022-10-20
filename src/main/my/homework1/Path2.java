package main.my.homework1;

/*
Description:
Канонический путь
Ограничение времени	1 секунда
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt

По заданной строке, являющейся абсолютным адресом в Unix-системе, вам необходимо получить канонический адрес.
В Unix-системе "." соответсвутет текущей директории, ".." — родительской директории, при этом будем считать, что любое
количество точек подряд, большее двух, соответствует директории с таким названием (состоящем из точек). "/" является
разделителем вложенных директорий, причем несколько "/" подряд должны интерпретироваться как один "/".

Канонический путь должен обладать следующими свойствами:
1) всегда начинаться с одного "/"
2) любые две вложенные директории разделяются ровно одним знаком "/"
3) путь не заканчивается "/" (за исключением корневой директории, состоящего только из символа "/")
4) в каноническом пути есть только директории, т.е. нет ни одного вхождения "." или ".." как соответствия текущей или
родительской директории

Формат ввода
Вводится строка с абсолютным адресом, её длина не превосходит 100.

Формат вывода
Выведите канонический путь.
 */

import java.util.Scanner;

public class Path2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(getPath(line));
        scanner.close();
    }

    private static String getPath(String line) {
    boolean flag = true;
    line = line +  "/";
    if (line.charAt(0) != '/') {
        line = "/" + line;
    }

    while (flag) {
        if (line.contains("/./")) {
            line = line.replaceAll("/\\./", "/");
        } else if (line.contains("//")) {
            line = line.replaceAll("//", "/");
        } else if (line.contains("/../")) {
            int position = line.indexOf("/../");
            if (position == 0) {
                line = line.substring(3);
            } else {
                int prev = line.substring(0, position).lastIndexOf("/");
                line = line.substring(0, prev) + line.substring(position + 3);
            }
        } else {
            flag = false;
        }
    }

    if (line.charAt(line.length() - 1) == '/' && !line.equals("/")) {
        line = line.substring(0, line.length() - 1);
    }
    return line;
    }
}
