package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Point;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;

public interface AlhorithmFastFindPath {
    public Path getPath(Point start, Point destination, double radiusMovingObject, MovingObject movingObject,
                        double timeAdding);
}
