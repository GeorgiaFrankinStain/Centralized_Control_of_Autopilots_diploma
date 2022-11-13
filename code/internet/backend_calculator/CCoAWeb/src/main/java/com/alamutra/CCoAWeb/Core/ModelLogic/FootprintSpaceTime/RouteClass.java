package com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime;

import java.util.HashMap;
import java.util.Map;

public class RouteClass implements Route { //FIXME add tests
    private Map<Footprint, NextFootprintAndTimes> nextFootprints = new HashMap<Footprint, NextFootprintAndTimes>();
    private Footprint lastFootprint = null;
    private double timeLastFootprint = 0.0;

    @Override
    public void addLastFootprint(double timeNextFootprint, Footprint nextFootprint) {
        NextFootprintAndTimes nextFootprintAndTimes = null;
        if (this.lastFootprint != null) {
            nextFootprintAndTimes =
                    new NextFootprintAndTimesClass(timeNextFootprint, nextFootprint, this.timeLastFootprint);
        }

        this.nextFootprints.put(this.lastFootprint, nextFootprintAndTimes);
        this.timeLastFootprint = timeNextFootprint;
        this.lastFootprint = nextFootprint;
    }

    @Override
    public NextFootprintAndTimes getNextFootprint(Footprint currentFootprint) {
        NextFootprintAndTimes nextFootprintAndTimes = nextFootprints.get(currentFootprint);
        return nextFootprintAndTimes;
    }

}
