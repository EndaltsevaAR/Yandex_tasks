package algorithm5.homework4;

/**
Как известно, Саруман Радужный очень любит порядок. Поэтому все полки его войска стоят друг за другом, 
причем каждый следующий полк содержит количество орков не меньше, чем предыдущий.

Перед тем как напасть на Хельмову Падь, Саруман решил провести несколько вылазок для разведки. 
Чтобы его отряды никто не заметил, он решил каждый раз отправлять несколько подряд идущих полков так, 
чтобы суммарное количество орков в них было равно определенному числу. Так как это всего лишь разведка, 
каждый полк после вылазки возвращается на свое место. Задачу выбрать нужные полки он поручил Гриме Змеиному Языку. 
А Грима не поскупится на вознаграждение, если вы ему поможете.

Формат ввода
В первой строке входного файла находится два целых числа: n (1 ≤ n ≤ 2⋅10^5) — количество полков и m (1 ≤ m ≤ 2⋅10^5) – 
количество предстоящих вылазок.

В следующей строке записано n чисел ai, где ai — число орков в i-ом полке (1 ≤ ai ≤ 10^9, ai ≤ ai+1).

Далее в m строках записаны запросы вида: количество полков l (1 ≤ l ≤ n), которые должны будут отправиться в эту вылазку, 
и суммарное количество орков в этих полках s (1 ≤ s ≤ 2⋅10^16)

Формат вывода
Для каждого запроса выведите номер полка, с которого начнутся те l, которые необходимо отправить на вылазку. 
Если таких полков несколько, выведите любой. Если же так выбрать полки нельзя, выведите -1
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int nNumberSquads = Integer.parseInt(st.nextToken());
            int mNumberWalks = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(reader.readLine());
            long[] squadsPrefix = new long[nNumberSquads + 1];
            for (int i = 1; i <= nNumberSquads; i++) {
                squadsPrefix[i] = squadsPrefix[i - 1] + (Long.parseLong(st.nextToken()));
            }
            long[][] walks = new long[mNumberWalks][2];
            for (int i = 0; i < mNumberWalks; i++) {
                st = new StringTokenizer(reader.readLine());
                for (int j = 0; j < 2; j++) {
                    walks[i][j] = (Long.parseLong(st.nextToken()));
                }
            }
            System.out.println(getAnswers(squadsPrefix, walks));
        }
    }

    public static String getAnswers(long[] squadsPrefix, long[][] walks) {
        StringBuilder builder = new StringBuilder();
        for (long[] walk : walks) {
            builder.append(getAnswerPerRequest(walk, squadsPrefix)).append("\n");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private static int getAnswerPerRequest(long[] walk, long[] squadsPrefix) {
        int lengthWalk = (int) walk[0];
        long sumSalk = walk[1];

        int left = lengthWalk;
        int right = squadsPrefix.length - 1;
        while (left <= right) {
            int med = (left + right + 1) / 2;
            if (med - lengthWalk < 0 && right >= squadsPrefix.length) {
                return -1;
            }
            long sum = squadsPrefix[med] - squadsPrefix[med - lengthWalk];
            if (sum == sumSalk) {
                return med - lengthWalk + 1;
            } else if (sum < sumSalk) {
                left = med + 1;
            } else {
                right = med - 1;
            }
        }
        return -1;
    }
}