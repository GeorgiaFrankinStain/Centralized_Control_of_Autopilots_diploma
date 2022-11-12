package com.alamutra.ccoa.Core.Wrappers;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Footprint;
import com.alamutra.ccoa.Core.Logic.GlobalVariable;
import com.alamutra.ccoa.Core.Logic.IndexLayer;

import java.util.*;

public class MultiMapTree implements MultiMap {
    private Map<Double, List<Footprint>> map = new TreeMap<Double, List<Footprint>>();


    @Override
    public void put(Double key, Footprint value) {
        if (this.map.get(key) == null) {
            this.map.put(key, new ArrayList<Footprint>());
        }

        this.map.get(key).add(value);
    }


    @Override
    public Iterator<PairCCoA<Double, Footprint>> iteratorEntryPair() {
        return new EntryPairMapClass();
    }

    @Override
    public int size() { //FIXME add tests
        int size = 0;
        for (Map.Entry<Double, List<Footprint>> entry : map.entrySet()) {
            List<Footprint> test = entry.getValue();
            size += test.size();
        }

        return size;
    }

    @Override
    public boolean equalsWithoutUniqueId(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        MultiMap other = (MultiMap) obj;

        if (other.size() != this.size()) {
            return false;
        }

        Iterator<PairCCoA<Double, Footprint>> thisIterator = this.iteratorEntryPair();

        boolean isOnlyOneEnded = false;
        boolean isSizeNotEquals;
        Iterator<PairCCoA<Double, Footprint>> otherIterator = other.iteratorEntryPair();
        while (true) {

            boolean isAreNotEnoughElementsToCompare = !otherIterator.hasNext() || !thisIterator.hasNext();
            isSizeNotEquals = isAreNotEnoughElementsToCompare;
            if (isSizeNotEquals) {
                isOnlyOneEnded = otherIterator.hasNext() ^ thisIterator.hasNext();
                break;
            }
            PairCCoA<Double, Footprint> entryOther = otherIterator.next();
            PairCCoA<Double, Footprint> entryThis = thisIterator.next();


            if (!GlobalVariable.equalsNumber(entryThis.getKey(), entryThis.getKey())) {
                return false;
            }

            Footprint thisFootprint = entryOther.getValue();
            Footprint otherFootprint = entryThis.getValue();

            if (!thisFootprint.equalsWithoutUniqueId(otherFootprint)) {
                return false;
            }

        }

        if (isOnlyOneEnded) {
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object o) { //FIXME add tests
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiMapTree that = (MultiMapTree) o;
        return Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }

    private class EntryPairMapClass implements Iterator<PairCCoA<Double, Footprint>> { //FIXME ADD_TEST
        private int positionReturnedList = 0;
        private Iterator iteratorMap;
        List<Footprint> currentList = null;
        Double currentKey = null;

        public EntryPairMapClass() {
            this.iteratorMap = MultiMapTree.this.map.entrySet().iterator();


        }

        @Override
        public boolean hasNext() {
            return (this.hasReturnedNowItemInCurrentArrayList() || this.iteratorMap.hasNext());
        }

        @Override
        public PairCCoA<Double, Footprint> next() {
            boolean successfullyFindNonEmptyList = updateThisCurrentVarible();

            if (!successfullyFindNonEmptyList) {
                return null;
            }


            Double resKeyMap = this.currentKey;
            Footprint resValue = this.currentList.get(positionReturnedList);

            positionReturnedList++;


            return new PairCCoAClass<Double, Footprint>(resKeyMap, resValue);
        }

        private boolean updateThisCurrentVarible() {
            boolean needAndNoAbilityToSwitchToTheNextMap = this.currentList == null && !this.iteratorMap.hasNext();
            if (needAndNoAbilityToSwitchToTheNextMap) {
                return false;
            }

            boolean needAndAbilityToSwitchToTheNextMap = this.currentList == null && this.iteratorMap.hasNext();
            if (needAndAbilityToSwitchToTheNextMap) {
                Map.Entry me = (Map.Entry) this.iteratorMap.next();
                this.currentList = (List<Footprint>) me.getValue();
                this.currentKey = (Double) me.getKey();
                this.positionReturnedList = 0;
            }


            boolean successfullyFindNonEmptyList = this.goToTheFirstNonEmptyList();
            return successfullyFindNonEmptyList;
        }

        private boolean goToTheFirstNonEmptyList() {
            while (!this.hasReturnedNowItemInCurrentArrayList()) {
                if (this.iteratorMap.hasNext()) {
                    Map.Entry me = (Map.Entry) this.iteratorMap.next();
                    this.currentList = (List<Footprint>) me.getValue();
                    this.currentKey = (Double) me.getKey();
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
