package com.alamutra.ccoa.Logic;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Point;

public interface Position {
    public Point getCoordinates();

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
