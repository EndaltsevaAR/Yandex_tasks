package algorithm5.homework3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        List<String> inputInfo = new ArrayList<>();
        while (scanner.hasNextLine()) {
            inputInfo.add(scanner.nextLine());
        }
        System.out.println(getStatistics(inputInfo));
    }

    public static String getStatistics(List<String> inputInfo) {
        StringBuilder resultingAnswerBuilder = new StringBuilder();
        // статическая информация
        Set<String> requestTypes = getRequestTypes(); // в ключе - запрос, в значении - сокращенное название

        // информация о командах и игроках
        // ключ - игрок, значение - название команды
        Map<String, String> commandStructure = new HashMap<>();

        // ключ - команда, значение - список голов
        Map<String, List<Integer>> commandScopes = new HashMap<>();
        // в ключе футболист, в значениях лист голов (массив из матча и времени)
        Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap = new HashMap<>();

        // список вывода
        List<String> requests = new ArrayList<>();

        int numberGame = 0;
        String firstTeam = "";
        String secondTeam = "";
        int firstCommandScore = 0;
        int secondCommandScore = 0;

        // цикл обработки входных данных
        Pattern pattern = Pattern.compile("\"(.*?)\" - \"(.*?)\" (\\d+):(\\d+)");
        Pattern patternInner = Pattern.compile("(\\D+) (\\d+)'");
        for (int i = 0; i < inputInfo.size();) {
            Matcher matcher = pattern.matcher(inputInfo.get(i));
            while (matcher.find()) {
                firstTeam = matcher.group(1);
                secondTeam = matcher.group(2);
                firstCommandScore = Integer.parseInt(matcher.group(3));
                secondCommandScore = Integer.parseInt(matcher.group(4));
                numberGame++;
                i++;

                List<Integer> firstCommandScopes = null;
                if (commandScopes.containsKey(firstTeam)) {
                    firstCommandScopes = commandScopes.get(firstTeam);
                } else {
                    firstCommandScopes = new ArrayList<>();
                }
                firstCommandScopes.add(firstCommandScore);
                commandScopes.put(firstTeam, firstCommandScopes);

                List<Integer> secondCommandScopes = null;
                if (commandScopes.containsKey(secondTeam)) {
                    secondCommandScopes = commandScopes.get(secondTeam);
                } else {
                    secondCommandScopes = new ArrayList<>();
                }
                secondCommandScopes.add(secondCommandScore);
                commandScopes.put(firstTeam, secondCommandScopes);

                for (int j = i; j < firstCommandScore + secondCommandScore; j++) {
                    Matcher matcherInner = patternInner.matcher(inputInfo.get(j));
                    String playerName = matcherInner.group(1);
                    int minute = Integer.parseInt(matcherInner.group(2));

                    if (j - i <= firstCommandScore) {
                        commandStructure.put(playerName, firstTeam);
                    } else {
                        commandStructure.put(playerName, secondTeam);
                    }

                    List<int[]> goals = null;
                    if (goalsScoredInAMatchByPlayerMap.containsKey(playerName)) {
                        goals = goalsScoredInAMatchByPlayerMap.get(playerName);
                    } else {
                        goals = new ArrayList<>();
                    }
                    int[] goal = new int[2];
                    goal[0] = numberGame;
                    goal[1] = minute;
                    goals.add(goal);
                    goalsScoredInAMatchByPlayerMap.put(playerName, goals);

                }
                i += firstCommandScore + secondCommandScore;

                // формирование списка запросов
                while (isRequest(inputInfo.get(i), requestTypes)) {
                    requests.add(inputInfo.get(i));
                    i++;
                }

                for (int j = 0; j < requests.size(); j++) {
                    resultingAnswerBuilder.append(requestProcessing(requests.get(j), numberGame, commandScopes,
                            commandStructure, goalsScoredInAMatchByPlayerMap)).append("\n");
                }
                requests = new ArrayList<>(); // обнуляем список запросов, потому что предыдущий уже обработан
            }
        }
        return resultingAnswerBuilder.deleteCharAt(resultingAnswerBuilder.length() - 1).toString();
    }

    private static boolean isRequest(String line, Set<String> requestTypes) {
        for (String requestPattern : requestTypes) {
            if (line.startsWith(requestPattern)) {
                return true;
            }
        }
        return false;
    }

    private static Set<String> getRequestTypes() {
        Set<String> requestTypes = new HashSet<>();
        requestTypes.add("Total goals for "); // количество голов, забитое данной командой за все матчи
        requestTypes.add("Mean goals per game for "); // среднее количество голов, забиваемое данной командой за один
                                                      // матч
        requestTypes.add("Total goals by "); // количество голов, забитое данным игроком за все матчи
        requestTypes.add("Mean goals per game by "); // среднее количество голов, забиваемое данным игроком за один матч
                                                     // его команды
        requestTypes.add("Goals on minute "); // количество голов, забитых данным игроком ровно на указанной минуте
        requestTypes.add("Goals on first "); // количество голов, забитых данным игроком на минутах с первой по T-ю
                                             // включительно
        requestTypes.add("Goals on last "); // количество голов, забитых данным игроком на минутах с (91 - T)-й по 90-ю
                                            // включительно.
        requestTypes.add("Score opens by "); // сколько раз данная команда или игрок открывала счет в матче.
        return requestTypes;
    }

    private static StringBuilder requestProcessing(String requestLine, int numberGame,
            Map<String, List<Integer>> commandScopes, Map<String, String> commandStructure,
            Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap) {

        if (requestLine.startsWith("Total goals for ")) {
            return requestTotalGoalCommand(requestLine, commandScopes);
        } else if (requestLine.startsWith("Mean goals per game for ")) {
            return requestAvrGoalCommand(requestLine, commandScopes);
        } else if (requestLine.startsWith("Total goals by ")) {
            return requestTotalGoalPlayer();
        } else if (requestLine.startsWith("Mean goals per game by ")) {
            return requestAvrGoalPlayer();
        } else if (requestLine.startsWith("Goals on minute ")) {
            return requestGoalMinutePlayer();
        } else if (requestLine.startsWith("Goals on first ")) {
            return requestGoalFirstMinutesPlayer();
        } else if (requestLine.startsWith("Goals on last ")) {
            return requestGoalLastMinutesPlayer();
        } else if (requestLine.startsWith("Score opens by ")) {
            return requestScoreOpen();
        } else {
            return new StringBuilder();
        }

    }

    private static StringBuilder requestTotalGoalCommand(String requestLine, Map<String, List<Integer>> commandScopes) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("Total goals for \"(.*?)\"");
        Matcher matcher = pattern.matcher(requestLine);
        String teamName = matcher.group(1);
        List<Integer> scopes = commandScopes.get(teamName);
        int sum = scopes.stream().mapToInt(Integer::intValue).sum();
        return builder.append(sum);
    }

    private static StringBuilder requestAvrGoalCommand(String requestLine, Map<String, List<Integer>> commandScopes) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("Mean goals per game for \"(.*?)\"");
        Matcher matcher = pattern.matcher(requestLine);
        String teamName = matcher.group(1);
        List<Integer> scopes = commandScopes.get(teamName);
        int sum = scopes.stream().mapToInt(Integer::intValue).sum();
        double avr = (double) sum / scopes.size();
        return builder.append(String.format("%.3f", avr));
    }

    private static StringBuilder requestTotalGoalPlayer() {
        StringBuilder builder = new StringBuilder();

        return builder;
    }

    private static StringBuilder requestAvrGoalPlayer() {
        StringBuilder builder = new StringBuilder();

        return builder;
    }

    private static StringBuilder requestGoalMinutePlayer() {
        StringBuilder builder = new StringBuilder();

        return builder;
    }

    private static StringBuilder requestGoalFirstMinutesPlayer() {
        StringBuilder builder = new StringBuilder();

        return builder;
    }

    private static StringBuilder requestGoalLastMinutesPlayer() {
        StringBuilder builder = new StringBuilder();

        return builder;
    }

    private static StringBuilder requestScoreOpen() {
        StringBuilder builder = new StringBuilder();

        return builder;
    }
}