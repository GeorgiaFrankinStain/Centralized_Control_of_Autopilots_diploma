package Logic.FootprintSpaceTime;

public interface Point {
    public double  getX();
    public double  getY();
    public void setX(double  x);
    public void setY(double  y);
    public Point clone();

    public boolean equals(Object obj);
    public String toString();

    public boolean isLeftRelative(Point startLine, Point endLine);
    public double getAngleRotareRelative(Point origin);
    public Point getRotareRelative(Point origin, double angle);
    public int getQuarter(Point origin);
    public double getLengthVector();
    public double distanceToPoint(Point point);
}
