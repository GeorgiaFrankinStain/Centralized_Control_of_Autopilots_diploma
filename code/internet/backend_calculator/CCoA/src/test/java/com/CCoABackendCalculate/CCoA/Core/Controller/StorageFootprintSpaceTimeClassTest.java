package com.CCoABackendCalculate.CCoA.Core.Controller;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageFootprintSpaceTimeClassTest {

    @Test
    void get() {
        StorageFootprintSpaceTimes storage = new StorageFootprintSpaceTimeClass();
        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        String id = storage.addFootprintSpaceTime(footprintsSpaceTime);
        FootprintsSpaceTime result = storage.get(id);
        assertEquals(footprintsSpaceTime, result);
    }

    @Test
    void remove() {
        StorageFootprintSpaceTimes storage = new StorageFootprintSpaceTimeClass();
        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        String id = storage.addFootprintSpaceTime(footprintsSpaceTime);
        storage.remove(id);
        FootprintsSpaceTime result = storage.get(id);
        assertEquals(null, result);
    }
}