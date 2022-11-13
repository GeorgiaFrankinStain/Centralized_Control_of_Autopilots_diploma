package com.alamutra.CCoAWeb.Core.ModelLogic.Landscape;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.PathCCoA;

import java.util.List;

public interface Landscape {
    public void fillArea(ZonaLandscape zonaLandscape);
    public int  speedInfluenceEnvironmentOnProperties(int  speed);
    public List<PointCCoA> getPointsDirectAccess(PolygonCCoA area);
    public PathCCoA getDijkstraPath(PointCCoA from, PointCCoA to); //TODO: толстая машина не может проезать через узкое ущелье
}
