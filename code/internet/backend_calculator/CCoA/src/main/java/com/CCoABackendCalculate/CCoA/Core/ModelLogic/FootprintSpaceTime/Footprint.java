package com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.Position;

public interface Footprint {
    public int getIdMovingObject();

    public Position getPosition();

    public PointCCoA getCoordinat();

    public double getTimeToNextFootprint();

    public Footprint getNextFootprint();

    public Footprint getApproximation(double timeFirst, Footprint second , double timeSecond, double timeApproximation);

    public Footprint getApproximationWithNextFootprint(double timeApproximation);

    public ParametersMovingUnique getMovingObject();

    public void setTimeToNextFootprint(double newTimeStanding);

    public PolygonCCoA getOccupiedLocation();

    public boolean isStanding();

    public String toString();

    public boolean equals(Object obj);

    public boolean equalsWithoutUniqueId(Object obj);
}
