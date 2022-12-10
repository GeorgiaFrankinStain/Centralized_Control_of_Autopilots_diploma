package com.CCoABackendCalculate.CCoA.Core.ModelLogic.AreasBenchmarkPaths;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;

public class StraightLineEstimatedClass implements AreasBenchmarkPaths {

    @Override
    public double getEstimatedDistanceFromTo(PointCCoA from, PointCCoA to, double radiusMovingObject) {
        //FIXME consider size move object (radius)
        return to.getDistanceToPoint(from); //FIXME add tests
    }
}
