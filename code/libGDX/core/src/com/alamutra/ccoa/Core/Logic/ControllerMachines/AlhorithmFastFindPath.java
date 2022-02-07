package com.alamutra.ccoa.Core.Logic.ControllerMachines;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.MovingObjects.ParametersMoving;
import com.alamutra.ccoa.Core.Logic.MovingObjects.PathCCoA;

public interface AlhorithmFastFindPath {
    public PathCCoA getPath(PointCCoA start, PointCCoA destination, double radiusMovingObject, ParametersMoving parametersMoving,
                            double timeAdding);
}
