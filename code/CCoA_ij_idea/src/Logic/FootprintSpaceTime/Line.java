package Logic.FootprintSpaceTime;

public interface Line {
    public Point getStart();
    public Point getEnd();
    public boolean intersectionLine(Line secondLine);
    public double length();
}
