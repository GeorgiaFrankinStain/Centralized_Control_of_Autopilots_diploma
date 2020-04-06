package Logic.MovingObjects;

import Logic.FootprintSpaceTime.FootprintSpaceTime;
import Logic.FootprintSpaceTime.PolygonExtended;

public interface MovingObject {
    public void mark(FootprintSpaceTime footprintSpaceTime, Path path);

    public PolygonExtended getPolygonExtended();

    public String getType();

    public int getID();

    public int getLevel();
}
