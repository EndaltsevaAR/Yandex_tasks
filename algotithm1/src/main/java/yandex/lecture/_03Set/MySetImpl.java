package yandex.lecture._03Set;

import java.util.ArrayList;
import java.util.List;

public class MySetImpl {
    int size = 10;
    List<List<Integer>> mySet = new ArrayList<>();

    public void add(int x) {
        mySet.get(x % size).add(x);
    }

    public boolean find(int x) {
        for (Integer integer : mySet.get(x % size)) {
            if (integer == x) {
                return true;
            }
        }
        return false;
    }

    public void delete(int x) {
        List<Integer> xList = mySet.get(x % size);
        boolean isFind = false;
        for (int i = 0; i < xList.size(); i++) {
            if (xList.get(i) == x) {
                xList.set(i, xList.get(xList.size() - 1));
                isFind = true;
            }
        }
        if (isFind) {
            xList.remove(xList.size() - 1);
        }
    }
}
