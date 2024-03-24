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

            if ((aMinHeight == aPartWords.length && bMinHeight >= aMinHeight)
                    || (bMinHeight == bPartWords.length && aMinHeight >= bMinHeight)) {
                return Math.max(getHeight(aPartMax, aPartWords), getHeight(bPartMax, bPartWords));
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
