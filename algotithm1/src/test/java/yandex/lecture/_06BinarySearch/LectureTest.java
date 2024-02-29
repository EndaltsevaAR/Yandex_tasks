package yandex.lecture._06BinarySearch;

import org.junit.Test;

import yandex.lecture._06BinarySearch.Lecture;

import static org.junit.Assert.*;

public class LectureTest {
    @Test
    public void test() {
        int w = 25;
        int h = 25;
        int n = 25;
        int result = Lecture.rightSearch(w, h, n);
        int answer = 5;
        assertEquals(result, answer);
    }
}
