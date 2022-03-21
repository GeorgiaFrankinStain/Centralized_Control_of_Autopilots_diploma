package com.alamutra.ccoa.Core.Logic.ControllerMachines;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMovingUnique;

public interface ControllerMachines {
    public void bringCarToEndOfRoad(
            PointCCoA from,
            PointCCoA to,
            ParametersMovingUnique parametersMovingUnique,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectException;
}
