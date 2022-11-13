package com.alamutra.CCoAWeb.Core.Logic.ControllerMachines;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.Logic.MovingBody.ParametersMovingUnique;

public interface ControllerMachines {
    public void bringCarToEndOfRoad(
            PointCCoA from,
            PointCCoA to,
            ParametersMovingUnique parametersMovingUnique,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectException;
}
