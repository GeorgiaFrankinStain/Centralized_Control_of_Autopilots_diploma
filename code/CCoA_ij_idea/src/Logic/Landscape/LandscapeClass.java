package Logic.Landscape;

import GUI.StatementTaskRendering.HistChangesFromWhen;
import GUI.StatementTaskRendering.PhisicalBodysFromWhen;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.FootprintSpaceTime.PhisicalBody;
import Logic.MovingObjects.Path;
import Logic.TypesInLevel;

import java.util.List;

public class LandscapeClass implements Landscape, PhisicalBodysFromWhen, HistChangesFromWhen {
    @Override
    public List<PhisicalBody> getRenderingPolygonsFromWhen(PolygonExtended areaFind, int  when) {
        return null;
    }

    @Override
    public List<PhisicalBody> getRenderingPolygonsFromWhen(PolygonExtended areaFind, int  when, TypesInLevel type) {
        return null;
    }

    @Override
    public void fillArea() {

    }

    @Override
    public int  speedInfluenceEnvironmentOnProperties(int  speed) {
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
    public PolygonExtended getAreaChangesAfterBefore(int  afterTime, int  berforeTime) {
        return null;
    }

    @Override
    public int  getTimeLastUpdate() {
        return 0;
    }
}
