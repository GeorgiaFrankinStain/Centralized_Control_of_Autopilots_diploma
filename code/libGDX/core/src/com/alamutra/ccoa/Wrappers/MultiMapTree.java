package com.alamutra.ccoa.Wrappers;

import java.util.*;

public class MultiMapTree<K, V> implements MultiMap<K, V> {
    private Map<K, List<V>> map = new TreeMap<K, List<V>>();


    @Override
    public void put(K key, V value) {
        if (this.map.get(key) == null) {
            this.map.put(key, new ArrayList<V>());
        }

        this.map.get(key).add(value);
    }


    @Override
    public Iterator<PairCCoA<K, V>> iteratorEntryPair() {
        return new EntryPairMapClass();
    }

    @Override
    public int size() { //FIXME add tests
        int size = 0;
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            List<V> test = entry.getValue();
            size += test.size();
        }

        return size;
    }


    private class EntryPairMapClass implements Iterator<PairCCoA<K, V>> { //FIXME ADD_TEST
        private int positionReturnedList = 0;
        private Iterator iteratorMap;
        List<V> currentList = null;
        K currentKey = null;

        public EntryPairMapClass() {
            this.iteratorMap = MultiMapTree.this.map.entrySet().iterator();


        }

        @Override
        public boolean hasNext() {
            return (this.hasReturnedNowItemInCurrentArrayList() || this.iteratorMap.hasNext());
        }

        @Override
        public PairCCoA<K, V> next() {
            boolean successfullyFindNonEmptyList = updateThisCurrentVarible();

            if (!successfullyFindNonEmptyList) {
                return null;
            }


            K resKeyMap = this.currentKey;
            V resValue = this.currentList.get(positionReturnedList);

            positionReturnedList++;


            return new PairCCoAClass<K, V>(resKeyMap, resValue);
        }

        private boolean updateThisCurrentVarible() {
            boolean needAndNoAbilityToSwitchToTheNextMap = this.currentList == null && !this.iteratorMap.hasNext();
            if (needAndNoAbilityToSwitchToTheNextMap) {
                return false;
            }

            boolean needAndAbilityToSwitchToTheNextMap = this.currentList == null && this.iteratorMap.hasNext();
            if (needAndAbilityToSwitchToTheNextMap) {
                Map.Entry me = (Map.Entry) this.iteratorMap.next();
                this.currentList = (List<V>) me.getValue();
                this.currentKey = (K) me.getKey();
                this.positionReturnedList = 0;
            }


            boolean successfullyFindNonEmptyList = this.goToTheFirstNonEmptyList();
            return successfullyFindNonEmptyList;
        }

        private boolean goToTheFirstNonEmptyList() {
            while (!this.hasReturnedNowItemInCurrentArrayList()) {
                if (this.iteratorMap.hasNext()) {
                    Map.Entry me = (Map.Entry) this.iteratorMap.next();
                    this.currentList = (List<V>) me.getValue();
                    this.currentKey = (K) me.getKey();
                    this.positionReturnedList = 0;
                } else {
                    return false;
                }
            }

            return true;
        }

        private boolean hasReturnedNowItemInCurrentArrayList() {
            if (currentList == null) {
                return false;
            }
            return this.positionReturnedList < currentList.size();
        }
    }


}
