package com.CCoABackendCalculate.CCoA.Core.ModelLogic.Landscape;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.PathCCoA;

import java.util.List;

public interface Landscape {
    public void fillArea(ZonaLandscape zonaLandscape);
    public int  speedInfluenceEnvironmentOnProperties(int  speed);
    public List<PointCCoA> getPointsDirectAccess(PolygonCCoA area);
    public PathCCoA getDijkstraPath(PointCCoA from, PointCCoA to); //TODO: толстая машина не может проезать через узкое ущелье
}
