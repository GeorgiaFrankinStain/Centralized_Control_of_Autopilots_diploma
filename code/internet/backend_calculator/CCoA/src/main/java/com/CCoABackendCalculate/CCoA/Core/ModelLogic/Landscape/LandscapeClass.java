package com.CCoABackendCalculate.CCoA.Core.ModelLogic.Landscape;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.PathCCoA;

import java.util.ArrayList;
import java.util.List;

public class LandscapeClass implements Landscape {
    private List<ZonaLandscape> allZonaLandscape = new ArrayList<ZonaLandscape>();


    @Override
    public void fillArea(ZonaLandscape zonaLandscape) {
        allZonaLandscape.add(zonaLandscape);
    }

    @Override
    public int speedInfluenceEnvironmentOnProperties(int speed) {
        return 0;
    }

    @Override
    public List<PointCCoA> getPointsDirectAccess(PolygonCCoA area) {
        return null;
    }

    @Override
    public PathCCoA getDijkstraPath(PointCCoA from, PointCCoA to) {
        return null;
    }

}
