package Logic.Landscape;

import GUI.Rendering.HistChangesFromWhen;
import GUI.Rendering.RendeingPolygonsFromWhen;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.Polygon;
import Logic.FootprintSpaceTime.RenderingPolygon;
import Logic.MovingObjects.Path;
import Logic.TypesInLevel;

import java.util.List;

public class LandscapeClass implements Landscape, RendeingPolygonsFromWhen, HistChangesFromWhen {
    @Override
    public List<RenderingPolygon> getRenderingPolygonsFromWhen(Polygon areaFind, long when) {
        return null;
    }

    @Override
    public List<RenderingPolygon> getRenderingPolygonsFromWhen(Polygon areaFind, long when, TypesInLevel type) {
        return null;
    }

    @Override
    public void fillArea() {

    }

    @Override
    public long speedInfluenceEnvironmentOnProperties(long speed) {
        return 0;
    }

    @Override
    public List<Point> getPointsDirectAccess(Polygon area) {
        return null;
    }

    @Override
    public Path getDijkstraPath(Point from, Point to) {
        return null;
    }

    @Override
    public Polygon getAreaChangesAfterBefore(long afterTime, long berforeTime) {
        return null;
    }

    @Override
    public long getTimeLastUpdate() {
        return 0;
    }
}
