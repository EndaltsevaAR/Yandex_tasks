package algorithm5.homework1;

/**
 * Раунд плей-офф между двумя командами состоит из двух матчей. Каждая команда проводит по одному матчу «дома» и «в гостях». 
 * Выигрывает команда, забившая большее число мячей. Если же число забитых мячей совпадает, выигрывает команд, забившая больше мячей «в гостях». 
 * Если и это число мячей совпадает, матч переходит в дополнительный тайм или серию пенальти.
 * 
 * Вам дан счёт первого матча, а также счёт текущей игры (которая ещё не завершилась). 
 * Помогите комментатору сообщить, сколько голов необходимо забить первой команде, чтобы победить, не переводя игру в дополнительное время.
 * 
 * Формат ввода
 * В первой строке записан счёт первого мачта в формате G1:G2, где G1 "— число мячей, забитых первой командой, 
 * а G2 "— число мячей, забитых второй командой. Во второй строке записан счёт второго (текущего) матча в аналогичном формате. 
 * Все числа в записи счёта не превышают 5. В третьей строке записано число 1, если первую игру первая команда провела «дома», или 2, если «в гостях».

Формат вывода
Выведите единственное целое число "— необходимое количество мячей.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstPlay = scanner.nextLine();
        String secondPlay = scanner.nextLine();
        String firstPlayOwner = scanner.nextLine();
        int result = ballToWin(firstPlay, secondPlay, firstPlayOwner);
        System.out.println(result);
        scanner.close();
    }

    public static int ballToWin(String firstPlay, String secondPlay, String firstPlayOwner) {
        int balls = 0;
        int firstCommandHomePlay, secondCommandHomePlay, firstCommandOutPlay, secondCommandOutPlay;

        int a = Integer.parseInt(firstPlay.split(":")[0]);
        int b = Integer.parseInt(firstPlay.split(":")[1]);
        int c = Integer.parseInt(secondPlay.split(":")[0]);
        int d = Integer.parseInt(secondPlay.split(":")[1]);

        if (firstPlayOwner.equals("1")) {
            firstCommandHomePlay = a;
            secondCommandHomePlay = b;
            firstCommandOutPlay = c;
            secondCommandOutPlay = d;
        } else {
            firstCommandHomePlay = c;
            secondCommandHomePlay = d;
            firstCommandOutPlay = a;
            secondCommandOutPlay = b;
        }

        int firstCommandBalls = firstCommandHomePlay + firstCommandOutPlay;
        int secondCommandBalls = secondCommandHomePlay + secondCommandOutPlay;

        if (firstCommandBalls > secondCommandBalls) {
            return balls;
        } else {
            balls = secondCommandBalls - firstCommandBalls;
            if (firstPlayOwner.equals("1")) {
                firstCommandOutPlay += balls;
            }
            if (firstCommandOutPlay <= secondCommandHomePlay) {
                balls++;
            }
        }
        return balls;
    }
}