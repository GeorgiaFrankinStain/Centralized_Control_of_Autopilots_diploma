package com.alamutra.ccoa.Logic.AreasBenchmarkPaths;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Point;

public class StraightLineEstimatedClass implements AreasBenchmarkPaths {

    @Override
    public double getEstimatedDistanceFromTo(Point from, Point to, double radiusMovingObject) {
        //FIXME consider size move object (radius)
        return to.getDistanceToPoint(from);
    }
}
