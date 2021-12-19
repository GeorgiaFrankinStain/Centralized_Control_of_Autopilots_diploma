package com.alamutra.ccoa.Logic.ControllerMachines;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.Point;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;

public interface ControllerMachines {
    public void bringCarToEndOfRoad(
            Point from,
            Point to,
            ParametersMoving parametersMoving,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption;
}
