package main.my.homework1;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Path2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(getPath(line));
        scanner.close();
    }

    private static String getPath(String line) {
        String[] directs = line.split("/");
        List<String> newDirects = new ArrayList<>();
        int i, j = 0;
        for (i = 0; i < directs.length; i++) {
            if (directs[i].equals("..") ) {
                newDirects.add(j--, "");
            } else if (!directs[i].equals(".")) {
                newDirects.add(j++, directs[i]);
            }
            if (j < 0) {
                return "/";
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int k = 0; k < j; k++) {
            stringBuilder.append("/").append(newDirects.get(k));
        }
        line = stringBuilder.toString().trim();
        while (line.contains("//")) {
            line = line.replaceAll("//", "/");
        }
          /*
        if (line.lastIndexOf("/") == line.length() - 1 && line.length() > 1) {
            line = line.substring(0, line.length() - 1);
        }
           */

        if (line.length() == 0) {
            line = "/";
        }
        return line;
    }
}
