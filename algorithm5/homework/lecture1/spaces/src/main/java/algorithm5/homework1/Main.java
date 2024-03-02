package algorithm5.homework1;

/**
 Петя - начинающий программист. Сегодня он написал код из n строк. К сожалению оказалось, что этот код трудно читать. 
 Петя решил исправить это, добавив в различные места пробелы. А точнее, для i-й строки ему нужно добавить ровно ai пробелов.
Для добавления пробелов Петя выделяет строку и нажимает на одну из трёх клавиш: Space, Tab, и Backspace. 
При нажатии на Space в строку добавляется один пробел. При нажатии на Tab в строку добавляются четыре пробела. 
При нажатии на Backspace в строке удаляется один пробел.
Ему хочется узнать, какое наименьшее количество клавиш придётся нажать, чтобы добавить необходимое количество пробелов в каждую строку. Помогите ему!

Формат ввода
Первая строка входных данных содержит одно целое положительное число 
n(1≤n≤10^5) – количество строк в файле.
Каждая из следующих n строк содержит одно целое неотрицательное число ai(0≤ai≤10^9) – количество пробелов, которые нужно добавить в i-ю строку файла.

Формат вывода
Выведите одно число – минимальное количество нажатий, чтобы добавить в каждой строке необходимое количество пробелов.
Пример
Ввод	
5
1
4
12
9
0

Вывод
8

Примечания
В примере можно:
1 раз нажать на Space в первой строке.
1 раз нажать на Tab на второй строке.
3 раза нажать на Tab в третьей строке.
2 раза нажать на Tab и один раз нажать на Space в четвёртой строке.
Ничего не нажимать в пятой строке.
В итоге получается 1+1+3+3=8 нажатий. 
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberLines = Integer.parseInt(scanner.nextLine());
        int[] linesLenght = new int[numberLines];
        for (int i = 0; i < linesLenght.length; i++) {
            linesLenght[i] = Integer.parseInt(scanner.nextLine());
        }
        Long count = getTouchCount(linesLenght);
        System.out.println(count);
        scanner.close();
    }

    public static Long getTouchCount(int[] linesLenght) {
        Long count = 0L;
        for (int i = 0; i < linesLenght.length; i++) {
            int tabs = linesLenght[i] / 4;

            linesLenght[i] -= tabs * 4;
            switch (linesLenght[i]) {
                case 1:
                    count += 1;
                    break;
                case 2:
                case 3:
                    count += 2;
                    break;
            }
            count += tabs;
        }
        return count;
    }
}