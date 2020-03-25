package Logic.FootprintSpaceTime;

public interface Point {
    public int  getX();
    public int  getY();
    public void setX(int  x);
    public void setY(int  y);

    public boolean isLeftRelative(Point startLine, Point endLine);
}
