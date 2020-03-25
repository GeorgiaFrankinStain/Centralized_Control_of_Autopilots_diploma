package Logic.FootprintSpaceTime;

public interface PolygonExtended {
    public int countPoints();

    public Point getPoint(int index);

    public void setPoint(Point newPoint);

    public void setPoint(int index, Point newPoint);

    public void setInsertBeforetPoint(int index, Point newPoint);

    public boolean contains(Point desirededPoint);

    public boolean enteringPoint(Point position);

    public boolean intersectionPolygon(PolygonExtended secondPolygon);

    public boolean intersecionLine(Point startLine, Point endLine);

    public boolean intersectionLines(
            Point aStartLine,
            Point aEndLine,
            Point bStartLine,
            Point bEndLine
    );
}
