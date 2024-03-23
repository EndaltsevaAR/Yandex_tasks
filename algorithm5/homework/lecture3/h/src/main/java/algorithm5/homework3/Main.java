package algorithm5.homework3;

/**
Вася любит решать головоломки со спичками. Чаще всего они формулируется следующим образом: дано изображение A, составленное из спичек; 
переложите в нем минимальное количество спичек так, чтобы получилось изображение B.

Например, из номера текущего командного чемпионата школьников Санкт-Петербурга по программированию, можно получить ромб с диагональю, 
переложив всего три спички.

Головоломки, которые решает Вася, всегда имеют решение. Это значит, что набор спичек, используемый в изображении A, 
совпадает с набором спичек, используемым в изображении B. Кроме того, в одном изображении никогда не встречаются две спички, 
у которых есть общий участок ненулевой длины (то есть спички могут пересекаться, но не могут накладываться друг на друга).

Вася устал решать головоломки вручную, и теперь он просит вас написать, программу, которая будет решать головоломки за него. 
Программа будет получать описания изображений A и B и должна найти минимальное количество спичек, которые надо переложить 
в изображении A, чтобы полученная картинка получалась из B параллельным переносом.

Формат ввода
В первой строке входного файла содержится целое число n — количество спичек в каждом из изображений (1 ≤ n ≤ 1000).

В следующих n строках записаны координаты концов спичек на изображении A. Спичка номер i описывается целыми числами x1i, y1i, x2i, y2i — 
координатами ее концов. Следующие n строк содержат описание изображения B в таком же формате. Набор длин этих спичек совпадает 
с набором длин спичек с изображения A.

Все координаты по абсолютной величине не превосходят 10^4. Все спички имеют ненулевую длину, то есть x1i ≠ x2i или y1i ≠ y2i.

Формат вывода
Выведите в выходной файл минимальное количество спичек, которые следует переложить, чтобы изображение A совпало с изображением B, 
с точностью до параллельного переноса.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static final double EPSILON = 1e-9;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int nNumberPoints = Integer.parseInt(reader.readLine());
            Set<Lighter> lightersBefore = new HashSet<>(nNumberPoints);
            Set<Lighter> lightersAfter = new HashSet<>(nNumberPoints);
            for (int i = 0; i < nNumberPoints; i++) {
                lightersBefore.add(getLighter(reader.readLine()));
            }
            for (int i = 0; i < nNumberPoints; i++) {
                lightersAfter.add(getLighter(reader.readLine()));
            }
            System.out.println(getNumberMoves(lightersBefore, lightersAfter));
        }
    }

    public static Lighter getLighter(String line) {
        StringTokenizer st = new StringTokenizer(line);
        int xStart = Integer.parseInt(st.nextToken());
        int yStart = Integer.parseInt(st.nextToken());
        int xEnd = Integer.parseInt(st.nextToken());
        int yEnd = Integer.parseInt(st.nextToken());
        return new Lighter(xStart, yStart, xEnd, yEnd);
    }

    public static int getNumberMoves(Set<Lighter> lightersBefore, Set<Lighter> lightersAfter) {
        Map<String, Integer> shiftsCounterMap = new HashMap<>();
        for (Lighter lighterBefore : lightersBefore) {
            for (Lighter lighterAfter : lightersAfter) {
                if (Math.abs(lighterBefore.getLength() - lighterAfter.getLength()) < EPSILON) {
                    int xStartShift = lighterBefore.getXStart() - lighterAfter.getXStart();
                    int yStartShift = lighterBefore.getYStart() - lighterAfter.getYStart();
                    int xEndShift = lighterBefore.getXEnd() - lighterAfter.getXEnd();
                    int yEndShift = lighterBefore.getYEnd() - lighterAfter.getYEnd();
                    if (xStartShift == xEndShift && yStartShift == yEndShift) {
                        String key = xStartShift + " " + yStartShift;
                        shiftsCounterMap.put(key, shiftsCounterMap.getOrDefault(key, 0) + 1);
                    }
                }
            }
        }
        return lightersAfter.size() - getMaxCount(shiftsCounterMap);
    }

    private static int getMaxCount(Map<String, Integer> shiftsCounterMap) {
        int maxCount = 0;
        for (Map.Entry<String, Integer> pair : shiftsCounterMap.entrySet()) {
            if (pair.getValue() > maxCount) {
                maxCount = pair.getValue();
            }
        }
        return maxCount;
    }
}

class Lighter {
    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;
    private double length;

    public Lighter(int xStart, int yStart, int xEnd, int yEnd) {
        if (xStart > xEnd || (xStart == xEnd && yEnd > yStart)) {
            this.xStart = xEnd;
            this.yStart = yEnd;
            this.xEnd = xStart;
            this.yEnd = yStart;
        } else {
            this.xStart = xStart;
            this.yStart = yStart;
            this.xEnd = xEnd;
            this.yEnd = yEnd;
        }
        length = Math.sqrt(Math.pow((xEnd - xStart), 2) + Math.pow((yEnd - yStart), 2));
    }

    public int getXStart() {
        return xStart;
    }

    public int getYStart() {
        return yStart;
    }

    public int getXEnd() {
        return xEnd;
    }

    public int getYEnd() {
        return yEnd;
    }

    public double getLength() {
        return length;
    }

}