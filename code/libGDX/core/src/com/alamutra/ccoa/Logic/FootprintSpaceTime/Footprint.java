package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;
import com.alamutra.ccoa.Logic.Position;

public interface Footprint {
    public int getIdMovingObject();

    public Position getPosition();

    public Point getCoordinat();

    public double getTimeToNextFootprint();

    public Footprint getApproximation(double timeFirst, Footprint second , double timeSecond, double timeApproximation);

    public ParametersMoving getMovingObject();

    public void setTimeToNextFootprint(double newTimeStanding);

    public PolygonExtended getOccupiedLocation();
//    public double getTravelTimeFromLastFootprint();

    public String toString();

    public boolean equals(Object obj);
}
