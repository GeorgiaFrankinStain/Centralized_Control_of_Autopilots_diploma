package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.MovingObjects.Path;

public interface CreatorMarksOfPath {
    public void addFootprint(
            Path path,
            double startTime
    ) throws СrashIntoAnImpassableObjectExeption;
}
