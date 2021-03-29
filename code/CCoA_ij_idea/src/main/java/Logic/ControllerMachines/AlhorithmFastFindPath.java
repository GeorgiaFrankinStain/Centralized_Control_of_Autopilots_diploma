package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Point;
import Logic.MovingObjects.ParametersMoving;
import Logic.MovingObjects.Path;

public interface AlhorithmFastFindPath {
    public Path getPath(Point start, Point destination, double radiusMovingObject, ParametersMoving parametersMoving,
                        double timeAdding);
}
