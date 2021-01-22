package Logic.FootprintSpaceTime;

import java.util.List;

public interface PolygonExtended {
    public int getCountPoints();

    public Point getPoint(int index);

    public void addPoint(Point newPoint);

    public void addPoint(Point[] points);

    public void addPoint(List<Point> points);

    public void addAllPoint(List<Point> newPoints);

    public void insertPoint(int index, Point newPoint);

    public void setPoint(int index, Point newPoint);

    public void insertBeforetPoint(int index, Point newPoint);

    public void rotateRelative(Point origin, double angle);

    public void deposeOn(Point vector);

    public boolean contains(Point desirededPoint);

    public boolean enteringPoint(Point position);

    public boolean intersectionPolygon(PolygonExtended secondPolygon);

    public boolean intersectionLine(Point startLine, Point endLine);

    public boolean intersectionLine(Line line);

    public boolean isLiesInsideThe(Round round);

    public Point getCenterAverage();

    public Double[] getFormatDoubleArray();

    public boolean equals(Object obj);

    public PolygonExtended clone();
}
