package com.alamutra.ccoa.Core.Logic.ControllerMachines;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.MovingObjects.ParametersMoving;

public interface ControllerMachines {
    public void bringCarToEndOfRoad(
            PointCCoA from,
            PointCCoA to,
            ParametersMoving parametersMoving,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption;
}
