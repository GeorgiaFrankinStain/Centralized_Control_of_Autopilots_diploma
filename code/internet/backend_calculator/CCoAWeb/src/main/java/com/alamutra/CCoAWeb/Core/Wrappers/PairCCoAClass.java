package com.alamutra.CCoAWeb.Core.Wrappers;

public class PairCCoAClass<K, V> implements PairCCoA<K, V> {
    private K key;
    private V value;

    public PairCCoAClass(K key, V value) {
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
