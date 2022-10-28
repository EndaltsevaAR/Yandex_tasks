package main.my.contect28102022;

import java.util.Scanner;

public class TaskB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int countChange = scanner.nextInt();
        int[] nums = new int[number];
        for (int i = 0; i < number; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.println(solution(number, countChange, nums));
    }

    public static int solution(int number, int countChange, int[] nums) {
        int max_answer = 0;
        int[] prefix = findPrefix(number, nums);
        if (prefix[prefix.length - 1] == number) {
            max_answer = number;
        } else {
            boolean isReady = false;
            int start = 0;
            int end = 0;
            for (int i = 0; i < number; i++) {
                if (nums[i] % 2 == 0) {
                    start++;
                } else {
                    if (start > 0) {
                        end = i;
                        isReady = true;
                    }
                }
                if (isReady) {
                    int leftCont = findLeftCont(countChange, prefix[prefix.length - 1] - (end - start), nums, start, end);
                    int rightCont = findRightCont(countChange, prefix[prefix.length - 1] - (end - start), nums, start, end);
                    max_answer = Math.max(max_answer, Math.max(leftCont, rightCont) + end - start);
                    start = 0;
                    end = 0;
                    isReady = false;
                }
            }
        }
        return max_answer;
    }

    private static int findRightCont(int countChange, int numberEven, int[] nums, int start, int end) {
        int len = 0;
        for (int i = end; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                len++;
                numberEven--;
            } else if (countChange >= numberEven && numberEven > 0) {
                countChange--;
                numberEven--;
                len++;
            }
            if (countChange == 0 || numberEven == 0) {
                break;
            }
        }
        if (countChange > 0 && numberEven > 0) {
            for (int i = start - 1; i >= 0; i--) {
                if (nums[i] % 2 == 0) {
                    len++;
                    numberEven--;
                } else if (countChange >= numberEven && numberEven > 0) {
                    countChange--;
                    numberEven--;
                    len++;
                }
                if (countChange == 0 || numberEven == 0) {
                    break;
                }
            }
        }
        return len;
    }

    private static int findLeftCont(int countChange, int numberEven, int[] nums, int start, int end) {
        int len = 0;
        for (int i = start - 1; i >= 0; i--) {
            if (nums[i] % 2 == 0) {
                len++;
                numberEven--;
            } else if (countChange >= numberEven && numberEven > 0) {
                countChange--;
                numberEven--;
                len++;
            }
            if (countChange == 0) {
                break;
            }
        }
        if (countChange > 0 && numberEven > 0) {
            for (int i = end; i < nums.length; i++) {
                if (nums[i] % 2 == 0) {
                    len++;
                    numberEven--;
                } else if (countChange >= numberEven && numberEven > 0) {
                    countChange--;
                    numberEven--;
                    len++;
                }
                if (countChange == 0) {
                    break;
                }
            }
        }
        return len;
    }

    private static int[] findPrefix(int number, int[] nums) {
        int[] prefix = new int[number + 1];
        for (int i = 1; i < number + 1; i++) {
            if (nums[i - 1] % 2 == 0) {
                prefix[i] = prefix[i - 1] + 1;
            } else {
                prefix[i] = prefix[i - 1];
            }
        }
        return prefix;
    }

}

