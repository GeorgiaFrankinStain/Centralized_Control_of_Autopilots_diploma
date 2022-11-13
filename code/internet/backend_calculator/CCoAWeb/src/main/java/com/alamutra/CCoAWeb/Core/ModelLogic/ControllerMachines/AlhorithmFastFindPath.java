package com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.ParametersMovingUnique;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.PathCCoA;

public interface AlhorithmFastFindPath {
    public PathCCoA getPath(PointCCoA start, PointCCoA destination, ParametersMovingUnique parametersMovingUnique,
                            double timeAdding);
}
