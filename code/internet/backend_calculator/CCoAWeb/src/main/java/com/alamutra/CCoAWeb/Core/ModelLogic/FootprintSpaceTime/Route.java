package com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime;

public interface Route {
    public void addLastFootprint(double time, Footprint footprint);

    public NextFootprintAndTimes getNextFootprint(Footprint currentFootprint);
}
