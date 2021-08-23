package Logic.MovingObjects;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.IndexLayer;
import Logic.TypesInLevel;

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

    public String getTypeTitle();

    public TypesInLevel getTypeInLevel();

    public double getLengthStep();

    public Point getPointWhereCoordinatesAreApplied();

    public double getRadius();

    public Point getVectorFromTopLeftToAppliedCoordinates();

    public String toString();

    public int getID();
}
