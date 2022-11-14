package com.alamutra.CCoAWeb.Core.Controller;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;

public interface StorageFootprintSpaceTimes {
    public FootprintsSpaceTime get(String id);

    public void delete(String id);

    public String addFootprintSpaceTime(FootprintsSpaceTime addingFootprintSpaceTime);
}
