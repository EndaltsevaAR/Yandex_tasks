package main.my.probniy;
/*
Description:
A+B 2
Ограничение времени	2 секунды
Ограничение памяти	64Mb
Ввод	input.txt
Вывод	output.txt
Даны два числа A и B. Вам нужно вычислить их сумму A+B. В этой задаче вам нужно читать из файла и выводить ответ в файл

Формат ввода
Первая строка входного файла содержит числа A и B (-2 ⋅ 109 ≤ A, B ≤ 2 ⋅ 109) разделенные пробелом

Формат вывода
В единственной строке выходного файла выведите сумму чисел A+B
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class files {

    public static void main(String[] args) throws IOException {
        PrintWriter writer = new PrintWriter("output.txt");
        Scanner scanner = new Scanner(new File("input.txt"));
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        writer.print(a+b);
        writer.close();
    }
}