package com.alamutra.ccoa.Core.Logic.Landscape;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.Logic.MovingBody.PathCCoA;

import java.util.List;

public interface Landscape {
    public void fillArea(ZonaLandscape zonaLandscape);
    public int  speedInfluenceEnvironmentOnProperties(int  speed);
    public List<PointCCoA> getPointsDirectAccess(PolygonCCoA area);
    public PathCCoA getDijkstraPath(PointCCoA from, PointCCoA to); //TODO: толстая машина не может проезать через узкое ущелье
}
