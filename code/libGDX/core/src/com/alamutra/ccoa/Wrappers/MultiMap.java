package com.alamutra.ccoa.Wrappers;

import java.util.Iterator;

public interface MultiMap<K, V> {
    public void put(K key, V value);
    public Iterator<PairCCoA<K, V>> iteratorEntryPair();
    public int size();
}
