package algorithm5.homework4;

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

        long minHeight = Long.MAX_VALUE;
        for (long i = aPartMax; i <= wLengthBothParts - bPartMax; i++) {
            long aMinHeight = getHeight(i, aPartWords);
            long bMinHeight = getHeight(wLengthBothParts - i, bPartWords);
            long maxStepHeight = Math.max(aMinHeight, bMinHeight);

            if ((aMinHeight == aPartWords.length && bMinHeight >= aMinHeight)
                    || (bMinHeight == bPartWords.length && aMinHeight >= bMinHeight)) {
                return maxStepHeight;
            }
            if (maxStepHeight <= minHeight) {
                minHeight = maxStepHeight;
            } else {
                break;
            }
        }
        return minHeight;
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