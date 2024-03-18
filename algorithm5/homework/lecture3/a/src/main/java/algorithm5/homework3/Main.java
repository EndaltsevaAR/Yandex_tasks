package algorithm5.homework3;

/**
Костя успешно прошел собеседование и попал на стажировку в отдел разработки сервиса «Музыка».
Конкретно ему поручили такое задание — научиться подбирать плейлист для группы друзей, родственников или коллег. 
При этом нужно подобрать такой плейлист, в который входят исключительно нравящиеся всем членам группы песни.
Костя очень хотел выполнить это задание быстро и качественно, но у него не получается. 
 ему написать программу, которая составляет плейлист для группы людей.

Формат ввода
В первой строке расположено одно натуральное число n(1≤n≤2⋅10^5), где n – количество человек в группе.
В следующих 2⋅n строках идет описание любимых плейлистов членов группы. По 2 строки на каждого участника.
В первой из этих 2-х строк расположено число ki — количество любимых треков i-го члена группы. В следующей строке 
расположено ki строк через пробел — названия любимых треков i-го участника группы.
Каждый трек в плейлисте задан в виде строки, все строки уникальны, сумма длин строк не превосходит 2⋅10^6. 
Строки содержат большие и маленькие латинские буквы и цифры.

Формат вывода
Выведите количество, а затем сам список песен через пробел — список треков, которые нравятся каждому участнику группы. 
Ответ необходимо отсортировать в лексикографическом порядке!
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int numberPeople = Integer.parseInt(scanner.nextLine());
        String[][] names = new String[numberPeople][];
        for (int i = 0; i < names.length; i++) {
            String numberTrack = scanner.nextLine();
            names[i] = scanner.nextLine().split(" ");
        }
        System.out.println(printPlayList(names));
        scanner.close();
    }

    public static String printPlayList(String[][] names) {
        Map<String, Integer> namesMap = new TreeMap<>();
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < names[i].length; j++) {
                if (!namesMap.containsKey(names[i][j])) {
                    namesMap.put(names[i][j], 0);
                }
                namesMap.put(names[i][j], namesMap.get(names[i][j]) + 1);
            }
        }

        StringBuilder builder = new StringBuilder();
        int counter = 0;
        for (Map.Entry<String, Integer> pair : namesMap.entrySet()) {
            if (pair.getValue() == names.length) {
                builder.append(pair.getKey()).append(" ");
                counter++;
            }
        }
        return counter + "\n" + builder.toString().trim();
    }
}