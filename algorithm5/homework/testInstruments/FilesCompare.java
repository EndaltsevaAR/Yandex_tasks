// "cwd": "${workspaceFolder}/src/main/java/algorithm5/homework4",

package algorithm5.homework4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FilesCompare {
    public static void main(String[] args) throws IOException {
        System.out.println("Start!");
        List<String> mys = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("my.txt"))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            mys.add(st.nextToken());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("answer.txt"))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            answers.add(st.nextToken());
        }

        for (int i = 0; i < mys.size(); i++) {
            if (!mys.get(i).equals(answers.get(i))) {
                System.out.println("My answer is " + mys.get(i) + ", answer is " + answers.get(i) + ", index is " + i);
            }
        }
    }
}
