package Wrapper;

import Logic.FootprintSpaceTime.Footprint;

import java.util.Iterator;

public interface MultiMap<K, V> {
    public void put(K key, V value);
    public Iterator<EntryPair<K, V>> iteratorEntryPair();
}
