package com.alamutra.CCoAWeb.Core.Logic.AreasBenchmarkPaths;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoA;

public class StraightLineEstimatedClass implements AreasBenchmarkPaths {

    @Override
    public double getEstimatedDistanceFromTo(PointCCoA from, PointCCoA to, double radiusMovingObject) {
        //FIXME consider size move object (radius)
        return to.getDistanceToPoint(from); //FIXME add tests
    }
}
