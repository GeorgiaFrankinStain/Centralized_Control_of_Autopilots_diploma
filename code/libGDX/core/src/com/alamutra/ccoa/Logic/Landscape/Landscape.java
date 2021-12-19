package com.alamutra.ccoa.Logic.Landscape;


import com.alamutra.ccoa.Logic.FootprintSpaceTime.Point;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonExtended;
import com.alamutra.ccoa.Logic.MovingObjects.Path;

import java.util.List;

public interface Landscape {
    public void fillArea(ZonaLandscape zonaLandscape);
    public int  speedInfluenceEnvironmentOnProperties(int  speed);
    public List<Point> getPointsDirectAccess(PolygonExtended area);
    public Path getDijkstraPath(Point from, Point to); //TODO: толстая машина не может проезать через узкое ущелье
}
