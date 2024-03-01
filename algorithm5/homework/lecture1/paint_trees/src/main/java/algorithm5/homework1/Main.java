package algorithm5.homework1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Вася и Маша участвуют в субботнике и красят стволы деревьев в белый цвет.
 * Деревья растут вдоль улицы через равные промежутки в 1 метр. Одно из деревьев
 * обозначено числом ноль, деревья по одну сторону занумерованы положительными
 * числами 1,2 и т.д., а в другую — отрицательными −1, −2 и т.д.
 * 
 * Ведро с краской для Васи установили возле дерева P, а для Маши — возле дерева
 * Q.
 * Ведра с краской очень тяжелые и Вася с Машей не могут их переставить,
 * поэтому они окунают кисть в ведро и уже с этой кистью идут красить дерево.
 * Краска на кисти из ведра Васи засыхает, когда он удаляется от ведра более чем
 * на V
 * метров, а из ведра Маши — на M метров. Определите, сколько деревьев может
 * быть покрашено.
 * 
 * Формат ввода
 * В первой строке содержится два целых числа P и V — номер дерева, у которого
 * стоит ведро
 * Васи и на сколько деревьев он может от него удаляться.
 * В второй строке содержится два целых числа Q и M — аналогичные данные для
 * Маши.
 * Все числа целые и по модулю не превосходят 1 *10^8.
 * 
 * Формат вывода
 * Выведите одно число — количество деревьев, которые могут быть покрашены.
 * Пример
 * Ввод
 * 0 7
 * 12 5
 * 
 * Вывод
 * 25
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String vasyaInfo = scanner.nextLine();
        String mashaInfo = scanner.nextLine();
        int result = getTreesNumber(vasyaInfo, mashaInfo);
        System.out.println(result);
        scanner.close();
    }

    private static int getTreesNumber(String vasyaInfo, String mashaInfo) {
        int backetVasyaP = Integer.parseInt(vasyaInfo.split(" ")[0]);
        int lenghtVasyaV = Integer.parseInt(vasyaInfo.split(" ")[1]);
        int backetMashaQ = Integer.parseInt(mashaInfo.split(" ")[0]);
        int lenghtMashaM = Integer.parseInt(mashaInfo.split(" ")[1]);

        Set<Integer> treesSet = new HashSet<>();
        for (int i = 0; i <= lenghtVasyaV; i++) {
            treesSet.add(backetVasyaP + i);
            treesSet.add(backetVasyaP - i);
        }
        for (int i = 0; i <= lenghtMashaM; i++) {
            treesSet.add(backetMashaQ + i);
            treesSet.add(backetMashaQ - i);
        }

        return treesSet.size();
    }
}