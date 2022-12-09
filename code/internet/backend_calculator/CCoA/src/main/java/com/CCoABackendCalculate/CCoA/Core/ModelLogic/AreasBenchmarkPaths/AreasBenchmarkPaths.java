package com.alamutra.CCoAWeb.Core.ModelLogic.AreasBenchmarkPaths;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoA;

public interface AreasBenchmarkPaths {
    public double getEstimatedDistanceFromTo(PointCCoA from, PointCCoA to, double radiusMovingObject);
}
