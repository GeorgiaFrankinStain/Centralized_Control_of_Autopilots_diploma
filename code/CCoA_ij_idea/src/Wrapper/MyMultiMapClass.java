package Wrapper;

import java.util.*;

public class MyMultiMapClass<K, V> implements MyMultiMap<K, V>, Iterable<V> {
    private Map<K, List<V>> map = new TreeMap<K, List<V>>();


    @Override
    public void put(K key, V value) {
        if (this.map.get(key) == null) {
            this.map.put(key, new ArrayList<V>());
        }

        this.map.get(key).add(value);
    }

    @Override
    public Iterator<V> iterator() {
        return new IteratorMapClass();
    }

    @Override
    public Iterator<EntryPair<K, V>> iteratorEntryPair() {
        return new EntryPairMapClass();
    }


    private class EntryPairMapClass implements Iterator<EntryPair<K, V>> { //FIXME ADD_TEST
        private int positionReturnedList = 0;
        private Iterator iteratorMap;
        List<V> currentList = null;
        K currentKey = null;

        public EntryPairMapClass() {
//            Set entrySet = MyMultiMapClass.this.map.entrySet();
            this.iteratorMap = MyMultiMapClass.this.map.entrySet().iterator();

/*            Map.Entry me = (Map.Entry) this.iteratorMap.next();
            this.currentList = (List<V>) me.getValue();
            this.currentKey = (K) me.getKey();*/


        }

        @Override
        public boolean hasNext() {
//            boolean hasNextLastItemInArrayList = this.positionReturnedList < currentList.size();
            return (this.hasReturnedNowItemInCurrentArrayList() || this.iteratorMap.hasNext());
        }

        @Override
        public EntryPair<K, V> next() {
            boolean successfullyFindNonEmptyList = updateThisCurrentVarible();

            if (!successfullyFindNonEmptyList) {
                return null;
            }


            K resKeyMap = this.currentKey;
            V resValue = this.currentList.get(positionReturnedList);

            positionReturnedList++;


            return new EntryPairClass<K, V>(resKeyMap, resValue);
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


    private class IteratorMapClass implements Iterator<V> { //FIXME ADD_TEST
        private int positionInLastList = 0;
        private Iterator iteratorMap;
        List<V> currentList;


        public IteratorMapClass() {
            Set entrySet = MyMultiMapClass.this.map.entrySet();
            this.iteratorMap = entrySet.iterator();
        }

        @Override
        public boolean hasNext() {
            boolean hasNextLastItemInArrayList = this.positionInLastList < (currentList.size() - 1);
            return (hasNextLastItemInArrayList || this.iteratorMap.hasNext());
        }

        @Override
        public V next() {

            boolean hasNextLastItemInArrayList = this.positionInLastList < (currentList.size() - 1);
            if (!hasNextLastItemInArrayList) {
                Map.Entry me = (Map.Entry) this.iteratorMap.next();
                this.currentList = (List<V>) me.getValue();

                this.positionInLastList = 0;
            }


            V resFootprint = this.currentList.get(positionInLastList);
            positionInLastList++;


            return resFootprint;
        }
    }
}
