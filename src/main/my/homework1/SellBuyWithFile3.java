package main.my.homework1;

import java.util.Scanner;

public class SellBuyWithFile3 {
    public static final double MONEY = 1000;
    int[] min;
    int[] max;
    int minSize, maxSize;
    double trend = 0;
    double[] prices;

    public static void main(String[] args) throws Exception {
        SellBuyWithFile3 main = new SellBuyWithFile3();
        main.run();
    }

    public void run() {
        readInput();
        findMinMax();
        analyze();
    }

    private void analyze() {
        int currentMin;
        int currentMax=-1;
        if (minSize == 0){
            System.out.println("0 0");
            return;
        }
        Delta maxDelta = new Delta(0,0);
        for (int i = 0; i < minSize; i++) {
            currentMin = min[i];
            if (currentMin > currentMax) {
                currentMax = -1;
                for (int j = 0; j < maxSize; j++) {
                    if (max[j] >= currentMin) {
                        if (currentMax == -1) {
                            currentMax = max[j];
                        } else {
                            if (prices[max[j]] > prices[currentMax]) {
                                currentMax = max[j];
                            }
                        }
                    }
                }
            }
            Delta tmp = new Delta(currentMin, currentMax);
            if (maxDelta.value() < tmp.value()){
                maxDelta = tmp;
            }
        }
        if (maxDelta.min == 0 && maxDelta.max == 0){
            System.out.println("0 0");
        }else {
            System.out.println((maxDelta.min + 1) + " " + (maxDelta.max + 1));
        }
    }

    private void findMinMax() {
        int minIndex = 0;
        int maxIndex = 0;
        if (prices[0] <= MONEY) {
            min[minIndex++] = 0;
        }
        for (int i = 1; i < prices.length; i++) {
            double direction = Math.signum(prices[i] - prices[i - 1]);
            if ((direction != trend) || (trend == 0)) {
                if (direction > 0) {
                    if (prices[i-1] <= MONEY) {
                        min[minIndex++] = i - 1;
                    }
                    trend = direction;
                }
                if (direction < 0) {
                    max[maxIndex++] = i - 1;
                    trend = direction;
                }
            }
        }
        max[maxIndex++] = prices.length - 1;
        minSize = minIndex;
        maxSize = maxIndex;
    }

    private void readInput() {
        Scanner scan = new Scanner(System.in);
        int length = scan.nextInt();
        prices = new double[length];
        min = new int[length];
        max = new int[length];
        for (int i = 0; i < length; i++) {
            prices[i] = scan.nextInt();
        }
    }

    class Delta {
        int min, max;
        public Delta(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public double value(){

            return SellBuyWithFile3.MONEY / prices[min] * (prices[max] - prices[min]);
        }
    }
}
