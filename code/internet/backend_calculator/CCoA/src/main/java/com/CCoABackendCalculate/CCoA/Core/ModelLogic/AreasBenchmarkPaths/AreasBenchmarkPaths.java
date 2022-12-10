package com.CCoABackendCalculate.CCoA.Core.ModelLogic.AreasBenchmarkPaths;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;

public interface AreasBenchmarkPaths {
    public double getEstimatedDistanceFromTo(PointCCoA from, PointCCoA to, double radiusMovingObject);
}
