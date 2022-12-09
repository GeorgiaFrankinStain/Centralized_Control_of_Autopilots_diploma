package com.alamutra.CCoAWeb.Core.ModelLogic;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoA;

public interface Position { //FIXME add get Layer
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
