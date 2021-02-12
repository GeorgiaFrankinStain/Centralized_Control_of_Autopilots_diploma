package Logic.FootprintSpaceTime;

public interface LineCut {
    public Point getStart();
    public Point getEnd();
    public boolean intersectionLine(LineCut secondLineCut);
    public boolean intersection(Point testedPoint);
    public double length();
}
