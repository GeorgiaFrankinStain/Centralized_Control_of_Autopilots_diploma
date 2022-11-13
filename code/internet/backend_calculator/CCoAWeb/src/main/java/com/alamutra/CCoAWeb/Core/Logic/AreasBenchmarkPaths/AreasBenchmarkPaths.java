package com.alamutra.CCoAWeb.Core.Logic.AreasBenchmarkPaths;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoA;

public interface AreasBenchmarkPaths {
    public double getEstimatedDistanceFromTo(PointCCoA from, PointCCoA to, double radiusMovingObject);
}
