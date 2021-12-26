package com.alamutra.ccoa.Wrappers;

public class EntryPairClass<K, V> implements EntryPair<K, V> {
    private K key;
    private V value;

    public EntryPairClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }
}
