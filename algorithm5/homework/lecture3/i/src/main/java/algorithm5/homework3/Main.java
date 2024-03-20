package algorithm5.homework3;

/**
Ася Вуткина — известный футбольный комментатор. Будучи профессионалом своего дела, Ася тщательно следит за всеми матчами 
всех европейских чемпионатов.

Благодаря накопленной информации, Ася может во время трансляции матча сообщить какую-нибудь интересную статистику, 
например: «Индзаги третий матч подряд забивает гол на 9-й минуте» или «Матерацци никогда не открывает счет в матче».

Но мозг Аси не безграничен, а помнить всю историю футбола просто невозможно. Поэтому Ася попросила вас написать программу, 
которая собирает статистику матчей и умеет отвечать на некоторые запросы, касающиеся истории футбола.

Информация о матче сообщается программе в следующей форме:

"<Название 1-й команды>" - "<Название 2-й команды>" <Счет 1-й команды>:<Счет 2-й команды>

<Автор 1-го забитого мяча 1-й команды> <Минута, на которой был забит мяч>'

<Автор 2-го забитого мяча 1-й команды> <Минута, на которой был забит мяч>'

...

<Автор последнего забитого мяча 1-й команды> <Минута, на которой был забит мяч>'

<Автор 1-го забитого мяча 2-й команды> <Минута, на которой был забит мяч>'

...

<Автор последнего забитого мяча 2-й команды> <Минута, на которой был забит мяч>'

Запросы к программе бывают следующих видов:

Total goals for <Название команды>

— количество голов, забитое данной командой за все матчи.

Mean goals per game for <Название команды>

— среднее количество голов, забиваемое данной командой за один матч. Гарантирутся, что к моменту подачи такого 
запроса команда уже сыграла хотя бы один матч.

Total goals by <Имя игрока>

— количество голов, забитое данным игроком за все матчи.

Mean goals per game by <Имя игрока>

— среднее количество голов, забиваемое данным игроком за один матч его команды.

Гарантирутся, что к моменту подачи такого запроса игрок уже забил хотя бы один гол.

Goals on minute <Минута> by <Имя игрока>

— количество голов, забитых данным игроком ровно на указанной минуте матча.

Goals on first <T> minutes by <Имя игрока>

— количество голов, забитых данным игроком на минутах с первой по T-ю включительно.

Goals on last <T> minutes by <Имя игрока>

— количество голов, забитых данным игроком на минутах с (91 - T)-й по 90-ю включительно.

Score opens by <Название команды>

— сколько раз данная команда открывала счет в матче.

Score opens by <Имя игрока>

— сколько раз данный игрок открывал счет в матче.

Формат ввода
Входной файл содержит информацию о матчах и запросы в том порядке, в котором они поступают в программу Аси Вуткиной.

Во входном файле содержится информация не более чем о 100 матчах, в каждом из которых забито не более 10 голов. 
Всего в чемпионате участвует не более 20 команд, в каждой команде не более 10 игроков забивают голы.

Все названия команд и имена игроков состоят только из прописных и строчных латинских букв и пробелов, а их длина не превышает 30. 
Прописные и строчные буквы считаются различными. Имена и названия не начинаются и не оканчиваются пробелами и не содержат двух 
пробелов подряд. Каждое имя и название содержит хотя бы одну букву.

Минута, на которой забит гол — целое число от 1 до 90 (про голы, забитые в дополнительное время, принято говорить, что они 
забиты на 90-й минуте).

Для простоты будем считать, что голов в собственные ворота в европейских чемпионатах не забивают, и на одной минуте матча может 
быть забито не более одного гола (в том числе на 90-й). Во время чемпионата игроки не переходят из одного клуба в другой.

Количество запросов во входном файле не превышает 500.

Формат вывода
Для каждого запроса во входном файле выведите ответ на этот запрос в отдельной строке. Ответы на запросы, подразумевающие 
нецелочисленный ответ, должны быть верны с точностью до трех знаков после запятой.
 */

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
        scanner.close();
    }

    public static String getStatistics(List<String> inputInfo) {
        StringBuilder resultingAnswerBuilder = new StringBuilder();
        // статическая информация
        Set<String> requestTypes = getRequestTypes(); // в ключе - запрос, в значении - сокращенное название

        // информация о командах и игроках
        // ключ - игрок, значение - название команды
        Map<String, String> commandStructure = new HashMap<>();

        // ключ - команда, значение - список голов (массив из матча и количества голов)
        Map<String, List<int[]>> commandScopes = new HashMap<>();
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
        for (int i = 0; i < inputInfo.size();) {
            Matcher matcher = pattern.matcher(inputInfo.get(i));
            if (matcher.find()) {
                firstTeam = matcher.group(1);
                secondTeam = matcher.group(2);
                firstCommandScore = Integer.parseInt(matcher.group(3));
                secondCommandScore = Integer.parseInt(matcher.group(4));
                numberGame++;
                i++;

                List<int[]> firstCommandScopes = null;
                if (commandScopes.containsKey(firstTeam)) {
                    firstCommandScopes = commandScopes.get(firstTeam);
                } else {
                    firstCommandScopes = new ArrayList<>();
                }
                int[] firstArr = new int[2];
                firstArr[0] = numberGame;
                firstArr[1] = firstCommandScore;
                firstCommandScopes.add(firstArr);
                commandScopes.put(firstTeam, firstCommandScopes);

                List<int[]> secondCommandScopes = null;
                if (commandScopes.containsKey(secondTeam)) {
                    secondCommandScopes = commandScopes.get(secondTeam);
                } else {
                    secondCommandScopes = new ArrayList<>();
                }
                int[] secondArr = new int[2];
                secondArr[0] = numberGame;
                secondArr[1] = secondCommandScore;
                secondCommandScopes.add(secondArr);
                commandScopes.put(secondTeam, secondCommandScopes);

                for (int j = i; j < i + firstCommandScore + secondCommandScore; j++) {
                    int lastIndex = inputInfo.get(j).lastIndexOf(' ');
                    String playerName = inputInfo.get(j).substring(0, lastIndex);
                    int minute = Integer
                            .parseInt(inputInfo.get(j).substring(lastIndex + 1, inputInfo.get(j).length() - 1));

                    if (j - i < firstCommandScore) {
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
                while (i < inputInfo.size() && isRequest(inputInfo.get(i), requestTypes)) {
                    requests.add(inputInfo.get(i));
                    i++;
                }

                for (int j = 0; j < requests.size(); j++) {
                    resultingAnswerBuilder.append(requestProcessing(requests.get(j), numberGame, commandScopes,
                            commandStructure, goalsScoredInAMatchByPlayerMap)).append("\n");
                }
                requests = new ArrayList<>(); // обнуляем список запросов, потому что предыдущий уже обработан
            } else {
                // формирование списка запросов
                while (i < inputInfo.size() && isRequest(inputInfo.get(i), requestTypes)) {
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
        if (resultingAnswerBuilder.length() == 0) {
            return "";
        } else {
            return resultingAnswerBuilder.deleteCharAt(resultingAnswerBuilder.length() - 1).toString();
        }
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
            Map<String, List<int[]>> commandScopes, Map<String, String> commandStructure,
            Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap) {

        if (requestLine.startsWith("Total goals for ")) {
            return requestTotalGoalCommand(requestLine, commandScopes);
        } else if (requestLine.startsWith("Mean goals per game for ")) {
            return requestAvrGoalCommand(requestLine, commandScopes);
        } else if (requestLine.startsWith("Total goals by ")) {
            return requestTotalGoalPlayer(requestLine, goalsScoredInAMatchByPlayerMap);
        } else if (requestLine.startsWith("Mean goals per game by ")) {
            return requestAvrGoalPlayer(requestLine, goalsScoredInAMatchByPlayerMap, commandScopes, commandStructure);
        } else if (requestLine.startsWith("Goals on minute ")) {
            return requestGoalMinutePlayer(requestLine, goalsScoredInAMatchByPlayerMap);
        } else if (requestLine.startsWith("Goals on first ")) {
            return requestGoalFirstMinutesPlayer(requestLine, goalsScoredInAMatchByPlayerMap);
        } else if (requestLine.startsWith("Goals on last ")) {
            return requestGoalLastMinutesPlayer(requestLine, goalsScoredInAMatchByPlayerMap);
        } else if (requestLine.startsWith("Score opens by ")) {
            return requestScoreOpen(requestLine, goalsScoredInAMatchByPlayerMap, commandScopes, commandStructure);
        } else {
            return new StringBuilder();
        }

    }

    private static StringBuilder requestTotalGoalCommand(String requestLine, Map<String, List<int[]>> commandScopes) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("Total goals for \"(.*?)\"");
        Matcher matcher = pattern.matcher(requestLine);
        if (matcher.find()) {
            String teamName = matcher.group(1);
            List<int[]> scopes = commandScopes.get(teamName);
            int sum = 0;
            if (scopes == null) {
                return builder.append(sum);
            }

            for (int[] scope : scopes) {
                sum += scope[1];
            }
            return builder.append(sum);
        }
        return builder;

    }

    private static StringBuilder requestAvrGoalCommand(String requestLine, Map<String, List<int[]>> commandScopes) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("Mean goals per game for \"(.*?)\"");
        Matcher matcher = pattern.matcher(requestLine);
        if (matcher.find()) {
            String teamName = matcher.group(1);
            List<int[]> scopes = commandScopes.get(teamName);
            double sum = 0;
            for (int[] scope : scopes) {
                sum += scope[1];
            }
            double avr = sum / scopes.size(); // можно не проверять, так как в задаче гарантируется, что хотя бы одну
                                              // игру команда сыграла
            return builder.append(avr);
        }
        return builder;
    }

    private static StringBuilder requestTotalGoalPlayer(String requestLine,
            Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap) {

        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("Total goals by (.*)");
        Matcher matcher = pattern.matcher(requestLine);
        if (matcher.find()) {
            String playerName = matcher.group(1).trim();
            List<int[]> playerScores = goalsScoredInAMatchByPlayerMap.get(playerName);
            if (playerScores == null) {
                return builder.append(0);
            } else {
                return builder.append(playerScores.size()); // каждый раз забивает по голу
            }

        }
        return builder;
    }

    private static StringBuilder requestAvrGoalPlayer(String requestLine,
            Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap, Map<String, List<int[]>> commandScopes,
            Map<String, String> commandStructure) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("Mean goals per game by (.*)");
        Matcher matcher = pattern.matcher(requestLine);
        if (matcher.find()) {
            String playerName = matcher.group(1).trim();
            String teamName = commandStructure.get(playerName);
            List<int[]> commandGames = commandScopes.get(teamName);
            List<int[]> playerGames = goalsScoredInAMatchByPlayerMap.get(playerName);
            if (playerGames == null) {
                return builder.append(0);
            }
            double avr = (double) playerGames.size() / commandGames.size(); // можно не проверять, так как в задаче
                                                                            // гарантируется, что хотя бы одну игру
                                                                            // команда сыграла

            return builder.append(avr);
        }
        return builder;
    }

    private static StringBuilder requestGoalMinutePlayer(String requestLine,
            Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("Goals on minute (\\d+) by (.*)");
        Matcher matcher = pattern.matcher(requestLine);
        if (matcher.find()) {
            int minute = Integer.parseInt(matcher.group(1));
            String playerName = matcher.group(2);
            List<int[]> playerGames = goalsScoredInAMatchByPlayerMap.get(playerName);
            int countGames = 0;
            if (playerGames == null) {
                return builder.append(countGames);
            }

            for (int[] game : playerGames) {
                if (game[1] == minute) {
                    countGames++;
                }
            }
            return builder.append(countGames);
        }
        return builder;
    }

    private static StringBuilder requestGoalFirstMinutesPlayer(String requestLine,
            Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("Goals on first (\\d+) minutes by (.*)");
        Matcher matcher = pattern.matcher(requestLine);
        if (matcher.find()) {
            int minute = Integer.parseInt(matcher.group(1));
            String playerName = matcher.group(2);
            List<int[]> playerGames = goalsScoredInAMatchByPlayerMap.get(playerName);
            int countGames = 0;
            if (playerGames == null) {
                return builder.append(countGames);
            }

            for (int[] game : playerGames) {
                if (game[1] <= minute) {
                    countGames++;
                }
            }
            return builder.append(countGames);
        }
        return builder;
    }

    private static StringBuilder requestGoalLastMinutesPlayer(String requestLine,
            Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("Goals on last (\\d+) minutes by (.*)");
        Matcher matcher = pattern.matcher(requestLine);
        if (matcher.find()) {
            int minute = Integer.parseInt(matcher.group(1));
            String playerName = matcher.group(2);
            List<int[]> playerGames = goalsScoredInAMatchByPlayerMap.get(playerName);
            int countGames = 0;
            if (playerGames == null) {
                return builder.append(countGames);
            }

            for (int[] game : playerGames) {
                if (game[1] >= 91 - minute) {
                    countGames++;
                }
            }
            return builder.append(countGames);
        }
        return builder;
    }

    private static StringBuilder requestScoreOpen(String requestLine,
            Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap, Map<String, List<int[]>> commandScopes,
            Map<String, String> commandStructure) {
        if (requestLine.contains("\"")) {
            return requestScoreOpenCommand(requestLine, goalsScoredInAMatchByPlayerMap, commandScopes,
                    commandStructure);
        } else {
            return requestScoreOpenPlayer(requestLine, goalsScoredInAMatchByPlayerMap);
        }
    }

    private static StringBuilder requestScoreOpenCommand(String requestLine,
            Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap, Map<String, List<int[]>> commandScopes,
            Map<String, String> commandStructure) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("Score opens by \"(.*?)\"");
        Matcher matcher = pattern.matcher(requestLine);
        if (matcher.find()) {
            String teamName = matcher.group(1);
            List<int[]> teamGames = commandScopes.get(teamName);
            if (teamGames == null) {
                return builder.append(0);
            }
            int[] gameNumbers = new int[teamGames.size()];
            for (int i = 0; i < teamGames.size(); i++) {
                gameNumbers[i] = teamGames.get(i)[0];
            }
            int count = getCountCommand(gameNumbers, goalsScoredInAMatchByPlayerMap, commandStructure, teamName);
            return builder.append(count);
        }
        return builder;
    }

    private static int getCountCommand(int[] gameNumbers, Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap,
            Map<String, String> commandStructure, String teamName) {
        int countTeamOpenScore = 0;

        for (int i = 0; i < gameNumbers.length; i++) {
            int minuteMin = 92;
            String teamMinuteMin = "";
            for (Map.Entry<String, List<int[]>> playerGames : goalsScoredInAMatchByPlayerMap.entrySet()) {
                List<int[]> games = playerGames.getValue();
                if (games == null) {
                    return 0;
                }
                for (int[] game : games) {
                    if (game[0] == gameNumbers[i] && game[1] < minuteMin) {
                        minuteMin = game[1];
                        teamMinuteMin = commandStructure.get(playerGames.getKey());
                    }
                }
            }
            if (teamMinuteMin.equals(teamName)) {
                countTeamOpenScore++;
            }
        }

        return countTeamOpenScore;
    }

    private static StringBuilder requestScoreOpenPlayer(String requestLine,
            Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("Score opens by (.*)");
        Matcher matcher = pattern.matcher(requestLine);
        if (matcher.find()) {
            String playerName = matcher.group(1).trim();
            List<int[]> playerGames = goalsScoredInAMatchByPlayerMap.get(playerName);
            if (playerGames == null) {
                return builder.append(0);
            }

            Set<Integer> gameNumbers = new HashSet<>();
            for (int i = 0; i < playerGames.size(); i++) {
                gameNumbers.add(playerGames.get(i)[0]);
            }
            int count = getCountPlayer(gameNumbers, goalsScoredInAMatchByPlayerMap, playerName);
            return builder.append(count);
        }
        return builder;
    }

    private static int getCountPlayer(Set<Integer> gameNumbers, Map<String, List<int[]>> goalsScoredInAMatchByPlayerMap,
            String playerName) {
        int countPlayerOpenScore = 0;

        for (Integer gameNumber : gameNumbers) {
            int minuteMin = 92;
            String playerMinuteMin = "";
            for (Map.Entry<String, List<int[]>> playerGames : goalsScoredInAMatchByPlayerMap.entrySet()) {
                List<int[]> games = playerGames.getValue();
                if (games == null) {
                    return 0;
                }
                for (int[] game : games) {
                    if (game[0] == gameNumber && game[1] < minuteMin) {
                        minuteMin = game[1];
                        playerMinuteMin = playerGames.getKey();
                    }
                }
            }
            if (playerMinuteMin.equals(playerName)) {
                countPlayerOpenScore++;
            }
        }
        return countPlayerOpenScore;
    }
}