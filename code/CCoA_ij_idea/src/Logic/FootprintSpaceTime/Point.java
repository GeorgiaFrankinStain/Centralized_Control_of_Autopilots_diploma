package Logic.FootprintSpaceTime;

public interface Point {
    public int  getX();
    public int  getY();
    public void setX(int  x);
    public void setY(int  y);
    public Point clone();

    public boolean isLeftRelative(Point startLine, Point endLine);
    public double getAngleRotareRelative(Point origin);
    public Point getRotareRelative(Point origin, double angle);
    public int getQuarter(Point origin);
}
