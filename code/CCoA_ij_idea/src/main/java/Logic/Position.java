package Logic;

import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PolygonExtended;

public interface Position {
    public Point getCoordinates();

    public double getRotation();

    public Position getApproximation(
            double timeFirst,
            Position secondPosition,
            double timeSecond,
            double timeProximity
    );

    public double getRotationDegree();

    public String toString();

    public boolean equals(Object obj);
}
