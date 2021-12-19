package com.alamutra.ccoa.Logic.Landscape;

import com.alamutra.ccoa.GUI.StatementTaskRendering.HistChangesFromWhen;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.Point;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonExtended;
import com.alamutra.ccoa.Logic.MovingObjects.Path;

import java.util.ArrayList;
import java.util.List;

public class LandscapeClass implements Landscape, HistChangesFromWhen {
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
    public List<Point> getPointsDirectAccess(PolygonExtended area) {
        return null;
    }

    @Override
    public Path getDijkstraPath(Point from, Point to) {
        return null;
    }

    @Override
    public PolygonExtended getAreaChangesAfterBefore(int afterTime, int berforeTime) {
        return null;
    }

    @Override
    public int getTimeLastUpdate() {
        return 0;
    }
}
