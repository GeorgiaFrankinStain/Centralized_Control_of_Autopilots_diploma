package com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.ParametersMovingUnique;

public interface ControllerMachines {
    public void bringCarToEndOfRoad(
            PointCCoA from,
            PointCCoA to,
            ParametersMovingUnique parametersMovingUnique,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectException;
}
