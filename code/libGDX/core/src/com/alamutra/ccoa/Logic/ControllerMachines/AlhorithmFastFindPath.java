package com.alamutra.ccoa.Logic.ControllerMachines;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;
import com.alamutra.ccoa.Logic.MovingObjects.PathCCoA;

public interface AlhorithmFastFindPath {
    public PathCCoA getPath(PointCCoA start, PointCCoA destination, double radiusMovingObject, ParametersMoving parametersMoving,
                            double timeAdding);
}
