package com.alamutra.ccoa.Core.Logic.AreasBenchmarkPaths;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;

public interface AreasBenchmarkPaths {
    public double getEstimatedDistanceFromTo(PointCCoA from, PointCCoA to, double radiusMovingObject);
}
