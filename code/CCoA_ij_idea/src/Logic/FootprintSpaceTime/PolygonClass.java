package Logic.FootprintSpaceTime;

import java.util.ArrayList;
import java.util.List;

public class PolygonClass implements Polygon {
    public List<Point> points = new ArrayList<Point>();;

    //FIXME rule of create polygon (lines don't intersect) (запретить узкие полигоны)


    public PolygonClass() {
    }

    public PolygonClass(List<Point> points) {
        this.points = points;
    }

    @Override
    public int countPoints() {
        return points.size();
    }

    @Override
    public Point getPoint(int index) {
        return this.points.get(index);
    }

    @Override
    public void setPoint(Point newPoint) {
        this.points.add(newPoint);
    }

    @Override
    public void setPoint(int index, Point newPoint) {
        this.points.add(index, newPoint);
    }

    @Override
    public void setInsertBeforetPoint(int index, Point newPoint) {
        //FIXME
    }


    @Override
    public boolean contains(Point desirededPoint) {
        return this.points.contains(desirededPoint);
    }

    public boolean enteringPoint(Point position) {

        //FIXME использовать алгоримт зональной декйкстры с алгоритмом создания точек прямого доступа около каждого вектора

        boolean isPointInPolygon = false;

        if (this.contains(position)) {
            return true;
        }

        int i = 0;
        int j = this.countPoints() - 1;
        for (; i < this.countPoints();) {
            Point pi = this.getPoint(i);
            Point pj = this.getPoint(j);
            if (
                    (
                            (
                                    (pi.getX() <= position.getY())
                                            && (position.getY() < pj.getY())
                            ) || (
                                    (pj.getY() <= position.getY())
                                            && (position.getY() < pi.getY())
                            )
                    ) && (pj.getY() - pi.getY() != 0 && position.getX() > (pj.getX() - pi.getX()) * (position.getY() - pi.getY()) / (pj.getY() - pi.getY()) + pi.getX())
            ) {
                isPointInPolygon = !isPointInPolygon;
            }

            j = i;
            i++;
        }

        return isPointInPolygon;
    }
}
