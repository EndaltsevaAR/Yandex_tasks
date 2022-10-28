package main.my.rome;
//Не до решен
/*
Description:
Электронные таблицы
Ограничение времени 1 секунда
Ограничение памяти 256Mb
Ввод стандартный ввод или input.txt
Вывод стандартный вывод или output.txt
Даны значения и формулы в некоторых ячейках электронной таблицы.
Необходимо найти все результаты формул или сообщить о циклических зависимостях. В формулах могут присутствовать только
операции ‘+’, ‘-’ и ‘*’ (без скобок), а операндами являются исключительно значения других ячеек. Формула может состоять
из одного операнда, т.е. являться копированием значения.
Гарантируется, что результаты вычислений помещаются в целочисленный 32-битный знаковый тип (промежуточные результаты
вычислений также помещаются в целочисленный 32-битный знаковый тип).

Формат ввода
Первая строка содержит одно число n (2≤n≤500) – количество ячеек в таблице.
Далее идет n строк. i + 1-я строка является описанием i-й ячейки в таблице. Первое число в строке typei – тип ячейки,
может принимать следующие значения: type i=1 – ячейка содержит в себе целочисленно значение x, не превосходящее 100
 по модулю. type i= 2 – ячейка содержит в себе формулу. Далее идет формула, в состав которой входят ячейки от 1 до
n (номера ячеек, из которых нужно брать значения), а также математические операции +, - и *. Каждая ячейка записана в виде
C index. Кроме того, каждая формула содержит не более 10 операндов. Также гарантируется, что в ячейке второго типа в
формуле нет операнда с таким же номером, как и номер ячейки. Гарантируется отсутствие унарных операторов.
В формулах операторы разделяются знаками операций без пробелов.

Формат вывода
Если система выражений имеет циклические зависимости, то нужно вывести −1.
В противном случае, необходимо вывести  n чисел – значения во всех ячейках таблицы, если вычислить значения формул.
 */

import java.util.*;

public class Excel3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        String[] lines = new String[number];
        for (int i = 0; i < number; i++) {
            lines[i] = scanner.nextLine();
        }
        int[] result = solution(lines);
        scanner.close();
    }

    public static int[] solution(String[] lines) {
        int[] answers = new int[lines.length];
        HashMap<Integer, List<Integer>> links = new HashMap<>();
        for (int i = 0; i < lines.length; i++) {
        //    links.put(i, 0);
        }
        for (int i = 0; i < lines.length; i++) {
            answers[i] = findCellAnswerAndLinks(i, lines, links);
        }
        return answers;
    }

    private static int findCellAnswerAndLinks(int i, String[] lines, HashMap<Integer, List<Integer>> links) {
        int answer = 0;
        String[] lineInside = lines[i].split(" ");
        switch (lineInside[0]) {
            case "1":
                answer = Integer.parseInt(lineInside[1]);
                break;
            case "2":
                StringTokenizer stringTokenizer = new StringTokenizer(lineInside[1], "[+-*]", true);
                while (stringTokenizer.hasMoreElements()) {
                    if (stringTokenizer.countTokens() == 1) {
                        String token = (String) stringTokenizer.nextElement();
                        int link = Integer.parseInt(token.substring(1)) - 1;
                        links.get(i).add(link);
                        answer = findCellAnswerAndLinks(link, lines, links);
                    } else {
                        List<String> row = new ArrayList<>();
                        while (stringTokenizer.hasMoreElements()) {
                            row.add(stringTokenizer.nextToken());
                        }

                    }
                }
                break;
        }
        return answer;
    }

}
