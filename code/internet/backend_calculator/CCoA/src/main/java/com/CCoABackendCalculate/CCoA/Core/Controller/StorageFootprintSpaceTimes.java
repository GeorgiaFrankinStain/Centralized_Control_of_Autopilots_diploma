package com.CCoABackendCalculate.CCoA.Core.Controller;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;

public interface StorageFootprintSpaceTimes {
    public FootprintsSpaceTime get(String id);

    public void remove(String id);

    public String addFootprintSpaceTime(FootprintsSpaceTime addingFootprintSpaceTime);
}
