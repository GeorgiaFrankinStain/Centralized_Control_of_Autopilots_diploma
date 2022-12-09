package com.alamutra.CCoAWeb.Core.Wrappers;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.Footprint;

import java.util.Iterator;

public interface MultiMap {
    public void put(Double key, Footprint value);
    public Iterator<PairCCoA<Double, Footprint>> iteratorEntryPair();
    public int size();

    public boolean equalsWithoutUniqueId(Object obj);
}
