package Logic;

import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PolygonExtended;

public interface Position {
    public Point getCoordinats();
    public double getRotation();
    public String toString();
    public boolean equals(Object obj);
}
