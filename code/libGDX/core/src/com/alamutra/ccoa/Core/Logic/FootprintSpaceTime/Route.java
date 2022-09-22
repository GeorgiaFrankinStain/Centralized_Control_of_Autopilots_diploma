package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

public interface Route {
    public void addLastFootprint(double time, Footprint footprint);

    public NextFootprintAndTimes getNextFootprint(Footprint currentFootprint);
}
