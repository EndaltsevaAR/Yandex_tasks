package main.my.probniy;

import java.util.Scanner;
/*
Description:
A+B 1
Ограничение времени	2 секунды
Ограничение памяти	64Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Даны два числа A и B. Вам нужно вычислить их сумму A + B. В этой задаче для работы с входными и выходными данными
вы можете использовать и файлы и потоки на ваше усмотрение.

Формат ввода
Первая строка входа содержит числа A и B (−210^9≤A,B≤2⋅10^9) разделенные пробелом
Формат вывода
В единственной строке выхода выведите сумму чисел A + B
*/

public class probniy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        long answer = x + y;
        System.out.println(answer);
        scanner.close();
    }
}
