package Wrapper;

import Logic.FootprintSpaceTime.Footprint;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class MyMultiMapTest {

    @Test
    public void iterator() {
    }

    @Test
    public void iteratorEntryPair() {

        MyMultiMap<Integer, Integer> storage =
                new MyMultiMapClass<Integer, Integer>();

        storage.put(0, 10);
        storage.put(0, 11);

        int i = 0;
        Iterator<EntryPair<Integer, Integer>> iteratorEntryPair = storage.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            EntryPair<Integer, Integer> entry = iteratorEntryPair.next();

            {
                int actual = entry.getKey();
                int expected = 0;
                assertEquals(expected, actual);
            }

            if (i == 0) {
                int actual = entry.getValue();
                int expected = 10;
                assertEquals(expected, actual);
            } else if (i == 1) {
                int actual = entry.getValue();
                int expected = 11;
                assertEquals(expected, actual);
            }
            i++;
        }
    }
}