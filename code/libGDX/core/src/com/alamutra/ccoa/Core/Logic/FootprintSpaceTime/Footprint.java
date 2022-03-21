package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMovingUnique;
import com.alamutra.ccoa.Core.Logic.Position;

public interface Footprint {
    public int getIdMovingObject();

    public Position getPosition();

    public PointCCoA getCoordinat();

    public double getTimeToNextFootprint();

    public Footprint getApproximation(double timeFirst, Footprint second , double timeSecond, double timeApproximation);

    public Footprint getApproximationWithNextFootprint(double timeApproximation);

    public ParametersMovingUnique getMovingObject();

    public void setTimeToNextFootprint(double newTimeStanding);

    public PolygonCCoA getOccupiedLocation();

    public String toString();

    public boolean equals(Object obj);
}
