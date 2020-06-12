package Logic.MovingObjects;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObstacleExeption;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.LevelLayer;

public interface MovingObject {
    public void mark(
            FootprintsSpaceTime footprintsSpaceTime,
            Path path,
            double timeAdding,
            LevelLayer levelLayer
    ) throws СrashIntoAnImpassableObstacleExeption;

    public PolygonExtended getPolygonExtended();

    public double getSpeed();
    public void setSpeed(double speed);

    public double timeTravel(double distance);

    public String getType();

    public int getID();

    public int getLevel();

    public double getLength();

    public Point getPointWhereCoordinatesAreApplied();

    public double getRadius();

    public String toString();
}
