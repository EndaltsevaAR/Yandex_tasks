package algorithm5.homework4;

/**
Верс нужно подготовить рапорт о последнем боевом вылете. Она уже сочинила в голове текст, осталось лишь его записать. 
Рапорт будет состоять из двух частей: первая будет содержать n слов, i-е из которых состоит из ai букв, вторая — m слов, 
j-е из которых состоит из bj букв. Язык Крии не содержит никаких знаков препинания. Верс должна записать рапорт на клетчатом 
рулоне бумаги, шириной w клеток. 

Так как рапорт состоит из двух частей, она разделит вертикальной чертой рулон на две части целой ширины, после чего в левой части 
напишет первую часть, а в правой — вторую.
Обе части рапорта записываются аналогично, каждая на своей части рулона. Одна буква слова занимает ровно одну клетку. 
Первое слово записывается в первой строке рулона, начиная с самой левой клетки этой части рулона. Каждое следующее слово, 
если это возможно, должно быть записано в той же строке, что и предыдущее, и быть отделено от него ровно одной пустой клеткой. 
Иначе, оно пишется в следующей строке, начиная с самой левой клетки. Если ширина части рулона меньше, чем длина какого-то слова, 
которое должно быть написано в этой части, написать эту часть рапорта на части рулона такой ширины невозможно.
Гарантируется, что можно провести вертикальную черту так, что обе части рапорта возможно написать. Верс хочет провести вертикальную 
черту так, чтобы длина рулона, которой хватит, чтобы написать рапорт, была минимальна. Помогите ей найти эту минимальную длину.

Формат ввода
В первой строке даны три целых числа w, n и m — ширина рулона, количество слов в первой и второй части рапорта (1≤w≤10^9; 1≤n,m≤100000).
В следующей строке дано n целых чисел ai — длина i-го слова первой части рапорта 1≤ai≤10^9.
В следующей строке дано m целых чисел bj — длина j-го слова второй части рапорта 1≤bj≤10^9.
Гарантируется, что возможно провести черту так, что обе части рапорта возможно написать.

Формат вывода
В единственной строке выведите одно целое число — минимальную длину рулона, которой достаточно, чтобы написать рапорт.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            long wLengthBothParts = Long.parseLong(st.nextToken());
            int nLengthAPart = Integer.parseInt(st.nextToken());
            int mLengthBPart = Integer.parseInt(st.nextToken());

            long aPartMax = Long.MIN_VALUE;
            long bPartMax = Long.MIN_VALUE;
            long[] aPartWords = new long[nLengthAPart];
            long[] bPartWords = new long[mLengthBPart];

            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < nLengthAPart; i++) {
                aPartWords[i] = (Long.parseLong(st.nextToken()));
                if (aPartWords[i] > aPartMax) {
                    aPartMax = aPartWords[i];
                }
            }
            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < mLengthBPart; i++) {
                bPartWords[i] = (Long.parseLong(st.nextToken()));
                if (bPartWords[i] > bPartMax) {
                    bPartMax = bPartWords[i];
                }
            }
            System.out.println(getMinHeight(wLengthBothParts, aPartMax, bPartMax, aPartWords, bPartWords));
        }
    }

    public static long getMinHeight(long wLengthBothParts, long aPartMax, long bPartMax, long[] aPartWords,
            long[] bPartWords) {
        if (aPartWords.length == 1) {
            return getHeight(wLengthBothParts - aPartMax, bPartWords);
        }
        if (bPartWords.length == 1) {
            return getHeight(wLengthBothParts - bPartMax, aPartWords);
        }
        long heightAtAMaxs = Math.max(getHeight(aPartMax, aPartWords),
                getHeight(wLengthBothParts - aPartMax, bPartWords));
        long heightAtBMaxs = Math.max(getHeight(wLengthBothParts - bPartMax, aPartWords),
                getHeight(bPartMax, bPartWords));
        long minHeightAtMaxs = Math.min(heightAtAMaxs, heightAtBMaxs);

        long left = aPartMax;
        long right = wLengthBothParts - bPartMax;
        while (left < right) {
            long med = (left + right) / 2;
            long aMinHeight = getHeight(med, aPartWords);
            long bMinHeight = getHeight(wLengthBothParts - med, bPartWords);

            if (aMinHeight == aPartWords.length && bMinHeight >= aMinHeight) {
                return Math.max(getHeight(aPartMax, aPartWords), getHeight(wLengthBothParts - aPartMax, bPartWords));
            }
            if (bMinHeight == bPartWords.length && aMinHeight >= bMinHeight) {
                return Math.max(getHeight(wLengthBothParts - bPartMax, aPartWords), getHeight(bPartMax, bPartWords));
            }

            if (aMinHeight == bMinHeight) {
                return aMinHeight;
            }

            if (aMinHeight < bMinHeight) {
                right = med;
            } else {
                left = med + 1;
            }
        }
        return Math.min(minHeightAtMaxs,
                Math.max(getHeight(left, aPartWords), getHeight(wLengthBothParts - left, bPartWords)));
    }

    private static long getHeight(long lineLength, long[] aPartWords) {
        long counter = 0;

        long currentLength = 0;
        for (int j = 0; j < aPartWords.length; j++) {
            if (currentLength == 0) {
                currentLength += aPartWords[j];
            } else {
                if (lineLength - currentLength >= aPartWords[j] + 1) {
                    currentLength += aPartWords[j] + 1;
                } else {
                    counter++;
                    currentLength = aPartWords[j];
                }
            }
        }
        return ++counter;
    }
}
