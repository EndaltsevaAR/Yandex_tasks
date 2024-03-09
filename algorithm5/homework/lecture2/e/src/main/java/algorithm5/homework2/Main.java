package algorithm5.homework2;

/**
Домашний питомец мальчика Васи — улитка Петя. Петя обитает на бесконечном в обе стороны вертикальном столбе, 
который для удобства можно представить как числовую прямую. Изначально Петя находится в точке 0.
Вася кормит Петю ягодами. У него есть n ягод, каждая в единственном экземпляре. Вася знает, что если утром он даст Пете ягоду с номером i, 
то поев и набравшись сил, за остаток дня Петя поднимется на ai единиц вверх по столбу, но при этом за ночь, потяжелев, съедет на bi 
единиц вниз. Параметры различных ягод могут совпадать.

Пете стало интересно, а как оно там, наверху, и Вася взялся ему в этом помочь. Ближайшие n дней он будет кормить Петю ягодами 
из своего запаса таким образом, чтобы максимальная высота, на которой побывал Петя за эти n дней была максимальной. 
К сожалению, Вася не умеет программировать, поэтому он попросил вас о помощи. Найдите, максимальную высоту, на которой Петя сможет 
побывать за эти n дней и в каком порядке Вася должен давать Пете ягоды, чтобы Петя смог её достичь!

Формат ввода
В первой строке входных данных дано число n (1≤n≤5⋅10^5) — количество ягод у Васи. В последующих n строках описываются параметры каждой ягоды. 
В i+1 строке дано два числа ai и bi (0≤ai,bi≤10^9) — то, насколько поднимется улитка за день после того, как съест i ягоду и 
насколько опуститься за ночь.

Формат вывода
В первой строке выходных данных выведите единственное число — максимальную высоту, которую сможет достичь Петя, 
если Вася будет его кормить оптимальным образом. В следующей строке выведите n различных целых чисел от 1 до n — порядок, 
в котором Вася должен кормить Петю (i число в строке соответствует номеру ягоды, которую Вася должен дать Пете в i день 
чтобы Петя смог достичь максимальной высоты).

Пример 2
Ввод	
2
7 6
7 4

Вывод
10
2 1 

Примечания
Во втором примере изначально улитка находится на высоте 0. Пусть сначала Петя накормит её второй ягодой, а затем первой. 
После того как она съест вторую ягоду, за день она поднимется на 7 (и окажется на высоте 7), а за ночь опустится на 4 (и окажется 
на высоте 3). После того как она съест первую ягоду, за день она поднимется на 7 (и окажется на высоте 10), а за ночь опустится на 6 
(и окажется на высоте 4).

Таким образом, максимальная высота, на которой побывает улитка при данном порядке кормления, равна 10. 
Нетрудно видеть, что если Петя накормит улитку сначала первой ягодой, а затем второй, то максимальная высота, 
на которой побывает улитка, будет меньше.
 */
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static class Berry {
        private int id;
        private BigInteger strength; // up - down
        private BigInteger up;
        private BigInteger down;

        public Berry(int id, BigInteger up, BigInteger down) {
            this.id = id;
            this.up = up;
            this.down = down;
            this.strength = up.subtract(down);
        }

        public int getId() {
            return id;
        }

        public BigInteger getStrength() {
            return strength;
        }

        public BigInteger getUp() {
            return up;
        }

        public BigInteger getDown() {
            return down;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input2.txt");
        Scanner scanner = new Scanner(file);
        int berriesCount = Integer.parseInt(scanner.nextLine());
        String[] berries = new String[berriesCount];
        for (int i = 0; i < berries.length; i++) {
            berries[i] = scanner.nextLine();
        }
        System.out.println(snailGo(berries));
        scanner.close();
    }

    public static String snailGo(String[] sBerries) {
        List<Berry> berriesWithPosStrength = new ArrayList<>();
        List<Berry> berriesWithNegStrength = new ArrayList<>();

        BigInteger maxDownFromPos = BigInteger.ZERO;
        Berry delimetrFromPos = new Berry(0, BigInteger.ZERO, BigInteger.ZERO);

        BigInteger maxUpFromNeg = BigInteger.ZERO;
        Berry delimetrFromNeg = new Berry(0, BigInteger.ZERO, BigInteger.ZERO);

        for (int i = 0; i < sBerries.length; i++) {
            String[] berryData = sBerries[i].split(" ");
            Berry berry = new Berry(i, new BigInteger(berryData[0]), new BigInteger(berryData[1]));
            if (berry.getStrength().compareTo(BigInteger.ZERO) >= 0) {
                if (berry.getDown().compareTo(maxDownFromPos) > 0) {
                    maxDownFromPos = berry.getDown();
                    delimetrFromPos = berry;
                }
                berriesWithPosStrength.add(berry);
            } else {
                if (berry.getUp().compareTo(maxUpFromNeg) > 0) {
                    maxUpFromNeg = berry.getUp();
                    delimetrFromNeg = berry;
                }
                berriesWithNegStrength.add(berry);
            }
        }

        StringBuilder answer = new StringBuilder();
        BigInteger maxHeight = BigInteger.ZERO;
        BigInteger currentHeight = BigInteger.ZERO;

        if (!berriesWithPosStrength.isEmpty()) {
            berriesWithPosStrength.remove(delimetrFromPos);

            for (Berry berry : berriesWithPosStrength) {
                answer.append(berry.id + 1).append(" ");
                currentHeight = currentHeight.add(berry.getUp());
                currentHeight = currentHeight.subtract(berry.getDown());
            }

            answer.append(delimetrFromPos.getId() + 1).append(" ");
            currentHeight = currentHeight.add(delimetrFromPos.getUp());
            if (maxHeight.compareTo(currentHeight) < 0) {
                maxHeight = currentHeight;
            }
            currentHeight = currentHeight.subtract(delimetrFromPos.getDown());
        }

        if (!berriesWithNegStrength.isEmpty()) {
            answer.append(delimetrFromNeg.getId() + 1).append(" ");
            currentHeight = currentHeight.add(delimetrFromNeg.getUp());
            if (maxHeight.compareTo(currentHeight) < 0) {
                maxHeight = currentHeight;
            }
            berriesWithNegStrength.remove(delimetrFromNeg);
        }

        for (Berry berry : berriesWithNegStrength) {
            answer.append(berry.getId() + 1).append(" ");
        }

        return (maxHeight + "\n" + answer.toString()).trim();
    }
}
