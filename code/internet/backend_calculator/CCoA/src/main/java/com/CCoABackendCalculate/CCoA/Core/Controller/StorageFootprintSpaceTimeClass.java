package com.CCoABackendCalculate.CCoA.Core.Controller;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;
import com.CCoABackendCalculate.CCoA.Core.Wrappers.RandomWrapper;
import com.CCoABackendCalculate.CCoA.Core.Wrappers.RandomWrapperClass;

import java.util.HashMap;
import java.util.Map;

public class StorageFootprintSpaceTimeClass implements StorageFootprintSpaceTimes {
    private Map<String, FootprintsSpaceTime> storage = new HashMap<>();

    @Override
    public FootprintsSpaceTime get(String id) {
        return storage.get(id);
    }

    @Override
    public void remove(String id) {
        storage.remove(id);
    }

    @Override
    public String addFootprintSpaceTime(FootprintsSpaceTime addedFootprintSpaceTime) {
        RandomWrapper random = new RandomWrapperClass(743174);
        String randomString = random.nextString(20);
        storage.put(randomString, addedFootprintSpaceTime);
        return randomString;
    }
}
