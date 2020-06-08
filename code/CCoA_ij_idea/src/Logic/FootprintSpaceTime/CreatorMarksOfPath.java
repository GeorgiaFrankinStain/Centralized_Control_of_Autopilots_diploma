package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObstacleExeption;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;

public interface CreatorMarksOfPath {
    public void addFootprint(
            Path path,
            double startTime
    ) throws СrashIntoAnImpassableObstacleExeption;
}
