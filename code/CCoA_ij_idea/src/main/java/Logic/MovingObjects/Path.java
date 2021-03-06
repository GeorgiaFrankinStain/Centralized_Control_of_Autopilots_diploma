package Logic.MovingObjects;

import Logic.FootprintSpaceTime.Point;

public interface Path {
    public int getSize();
    public Point getPoint(int index);
    public void addPoint(Point point);
    public void addPoint(int index, Point point);
    public void deposeOn(Point vector);
    public int getIdTrack();
}
