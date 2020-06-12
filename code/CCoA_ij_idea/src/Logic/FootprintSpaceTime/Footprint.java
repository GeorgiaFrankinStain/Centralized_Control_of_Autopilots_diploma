package Logic.FootprintSpaceTime;

import Logic.MovingObjects.MovingObject;
import Logic.Position;

public interface Footprint {
    public int getIdObject();
    public int getIdTrack();
    public Position getPosition();
    public Point getCoordinat();
    public double getTimeStanding();
    public MovingObject getMovingObject();
    public void setTimeStanding(double newTimeStanding);
    public PolygonExtended getOccupiedLocation();
//    public double getTravelTimeFromLastFootprint();

    public String toString();
    public boolean equals(Object obj);
}
