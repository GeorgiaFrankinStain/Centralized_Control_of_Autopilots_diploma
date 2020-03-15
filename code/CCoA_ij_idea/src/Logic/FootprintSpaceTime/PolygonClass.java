package Logic.FootprintSpaceTime;

import java.util.ArrayList;
import java.util.List;

public class PolygonClass implements Polygon {
    public List<Point> points = new ArrayList<Point>();;


    @Override
    public int getCountPoints() {
        return points.size();
    }

    @Override
    public Point getPoint(int index) {
        return this.points.get(index);
    }
}
