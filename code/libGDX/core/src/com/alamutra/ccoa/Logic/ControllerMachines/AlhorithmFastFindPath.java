package com.alamutra.ccoa.Logic.ControllerMachines;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Point;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;
import com.alamutra.ccoa.Logic.MovingObjects.Path;

public interface AlhorithmFastFindPath {
    public Path getPath(Point start, Point destination, double radiusMovingObject, ParametersMoving parametersMoving,
                        double timeAdding);
}
