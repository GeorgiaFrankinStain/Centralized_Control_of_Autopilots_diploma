package Logic.MovingObjects;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.IndexLayer;

public interface ParametersMoving {
    public void mark(
            FootprintsSpaceTime footprintsSpaceTime,
            Path path,
            double timeAdding,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectExeption;

    public PolygonExtended getShape();

    public double getSpeed();

    public double timeTravel(double distance);

    public String getType();

    public int getID();

    public int getLevel();

    public double getLength();

    public Point getPointWhereCoordinatesAreApplied();

    public double getRadius();

    public Point getVectorFromTopLeftToAppliedCoordinates();

    public String toString();
}
