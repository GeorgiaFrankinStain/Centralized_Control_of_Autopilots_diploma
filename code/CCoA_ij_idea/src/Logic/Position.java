package Logic;

import Logic.FootprintSpaceTime.Point;

public interface Position {
    public Point getCoordinats();
    public double getRotation();
    public boolean equals(Object obj);
}
