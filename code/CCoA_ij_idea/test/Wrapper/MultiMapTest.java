package Wrapper;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class MultiMapTest {



    @Test
    public void iteratorEntryPair() {

        MultiMap<Integer, Integer> storage =
                new MultiMapTree<Integer, Integer>();

        storage.put(0, 10);
        storage.put(0, 11);
        storage.put(1, 12);
        storage.put(-1, 9);

        int i = 0;
        Iterator<EntryPair<Integer, Integer>> iteratorEntryPair = storage.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            EntryPair<Integer, Integer> entry = iteratorEntryPair.next();

            if (i == 0) {
                {
                    int actual = entry.getValue();
                    int expected = 9;
                    assertEquals(expected, actual);
                }
                {
                    int actual = entry.getKey();
                    int expected = -1;
                    assertEquals(expected, actual);
                }
            } else if (i == 1) {
                {
                    int actual = entry.getValue();
                    int expected = 10;
                    assertEquals(expected, actual);
                }
                {
                    int actual = entry.getKey();
                    int expected = 0;
                    assertEquals(expected, actual);
                }

            } else if (i == 2) {
                {
                    int actual = entry.getValue();
                    int expected = 11;
                    assertEquals(expected, actual);
                }
                {
                    int actual = entry.getKey();
                    int expected = 0;
                    assertEquals(expected, actual);
                }
            } else if (i == 3) {
                {
                    int actual = entry.getValue();
                    int expected = 12;
                    assertEquals(expected, actual);
                }
                {
                    int actual = entry.getKey();
                    int expected = 1;
                    assertEquals(expected, actual);
                }
            }
            i++;
        }
    }
}