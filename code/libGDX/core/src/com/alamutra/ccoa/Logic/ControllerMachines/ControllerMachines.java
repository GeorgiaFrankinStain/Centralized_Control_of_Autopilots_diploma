package com.alamutra.ccoa.Logic.ControllerMachines;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;

public interface ControllerMachines {
    public void bringCarToEndOfRoad(
            PointCCoA from,
            PointCCoA to,
            ParametersMoving parametersMoving,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption;
}
