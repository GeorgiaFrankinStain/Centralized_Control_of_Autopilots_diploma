package Logic.FootprintSpaceTime;

import java.util.ArrayList;
import java.util.List;

public class PolygonClass implements Polygon {
    public List<Point> points = new ArrayList<Point>();;


    @Override
    public int getSize() {
        return points.size();
    }

    @Override
    public Point getPoint(int index) {
        return points.get(index);
    }
}
