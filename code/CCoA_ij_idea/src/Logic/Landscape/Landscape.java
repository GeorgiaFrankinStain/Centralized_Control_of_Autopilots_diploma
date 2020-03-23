package Logic.Landscape;


import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.MovingObjects.Path;

import java.util.List;

public interface Landscape {
    public void fillArea();
    public int  speedInfluenceEnvironmentOnProperties(int  speed);
    public List<Point> getPointsDirectAccess(PolygonExtended area);
    public Path getDijkstraPath(Point from, Point to); //TODO: толстая машина не может проезать через узкое ущелье
}
