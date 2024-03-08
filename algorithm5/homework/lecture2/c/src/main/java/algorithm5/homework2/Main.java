package algorithm5.homework2;

/**
На столе лежали две одинаковые верёвочки целой положительной длины.

Петя разрезал одну из верёвочек на N частей, каждая из которых имеет целую положительную длину, так что на столе стало N+1 верёвочек. 
Затем в комнату зашла Маша и взяла одну из лежащих на столе верёвочек. По длинам оставшихся на столе N верёвочек определите, 
какую наименьшую длину может иметь верёвочка, взятая Машей.

Формат ввода
Первая строка входных данных содержит одно целое число N — количество верёвочек, оставшихся на столе (2 ≤ N ≤ 1000). 
Во второй строке содержится N целых чисел li — длины верёвочек (1 ≤ li ≤ 1000).

Формат вывода
Выведите одно целое число — наименьшую длину, которую может иметь верёвочка, взятая Машей.


 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberRopes = Integer.parseInt(scanner.nextLine());
        String ropesLength = scanner.nextLine();
        System.out.println(getMinRope(numberRopes, ropesLength));
        scanner.close();
    }

    public static int getMinRope(int numberRopes, String ropesLength) {
        String[] sRopes = ropesLength.split(" ");
        int[] ropes = new int[numberRopes];

        int sumRopesLength = 0;
        int maxLength = 0;
        for (int i = 0; i < sRopes.length; i++) {
            ropes[i] = Integer.parseInt(sRopes[i]);
            sumRopesLength += ropes[i];
            if (ropes[i] > maxLength) {
                maxLength = ropes[i];
            }
        }

        if (sumRopesLength < maxLength * 2) {
            return maxLength * 2 - sumRopesLength;
        } else {
            return sumRopesLength;
        }
    }
}