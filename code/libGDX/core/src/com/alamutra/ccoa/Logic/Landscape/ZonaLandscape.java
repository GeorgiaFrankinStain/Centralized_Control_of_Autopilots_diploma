package com.alamutra.ccoa.Logic.Landscape;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonExtended;
import com.alamutra.ccoa.Logic.TypesInLevel;

public interface ZonaLandscape {
    public boolean getAccessPlace(PolygonExtended place, double time, TypesInLevel type);
}
