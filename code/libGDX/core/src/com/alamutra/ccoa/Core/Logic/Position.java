package com.alamutra.ccoa.Core.Logic;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;

public interface Position {
    public PointCCoA getCoordinates();

    public double getRotation();

    public Position getApproximation(
            double timeFirst,
            Position secondPosition,
            double timeSecond,
            double timeProximity
    );

    public double getRotationDegree();

    public String toString();

    public boolean equals(Object obj);
}
