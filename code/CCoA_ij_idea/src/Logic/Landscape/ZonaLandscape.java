package Logic.Landscape;

import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.TypesInLevel;

public interface ZonaLandscape {
    public boolean getAccessPlace(PolygonExtended place, double time, TypesInLevel type);
}
