package com.alamutra.ccoa.Logic.Landscape;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Logic.MovingObjects.PathCCoA;

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
