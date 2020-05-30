package Wrapper;

import Logic.FootprintSpaceTime.Footprint;

import java.util.Iterator;

public interface MyMultiMap<K, V> { //FIXME TEST_ADD
    public void put(K key, V value);
    public Iterator<V> iterator();
    public Iterator<EntryPair<K, V>> iteratorEntryPair();
}
