package Logic.FootprintSpaceTime;

import Logic.Position;

public interface Footprint {
    public int getIdObject();
    public int getIdTrack();
    public Position getPosition();
    public double getTimeStanding();
    public PolygonExtended getOccupiedLocation();
    public String toString();

}
