package Logic.MovingObjects;

import Logic.FootprintSpaceTime.Point;
import Wrapper.RandomWrapperClass;

import java.util.ArrayList;
import java.util.List;

public class PathClass implements Path {
    private List<Point> points = new ArrayList<Point>();
    private int idTrack = new RandomWrapperClass().nextInt();


    @Override
    public int getSize() {
        return this.points.size();
    }

    @Override
    public Point getPoint(int index) {
        return this.points.get(index);
    }

    @Override
    public void addPoint(Point point) {
        this.points.add(point);
    }

    @Override
    public int getIdTrack() {
        return this.idTrack;
    }

}
