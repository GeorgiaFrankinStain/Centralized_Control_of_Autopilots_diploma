package com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime;

/**
 * it differs from PathCCoA in that it is the movement
 * of a car in space-time with timings, and
 * PathCCoA is just a recommendation where to drive in space
 */
public interface Route {
    public void addLastFootprint(double time, Footprint footprint);

    public NextFootprintAndTimes getNextFootprint(Footprint currentFootprint);
}
