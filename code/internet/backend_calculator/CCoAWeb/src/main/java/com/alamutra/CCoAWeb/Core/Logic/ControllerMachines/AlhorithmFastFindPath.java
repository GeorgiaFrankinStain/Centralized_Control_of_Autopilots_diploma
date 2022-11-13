package com.alamutra.CCoAWeb.Core.Logic.ControllerMachines;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.Logic.MovingBody.ParametersMovingUnique;
import com.alamutra.CCoAWeb.Core.Logic.MovingBody.PathCCoA;

public interface AlhorithmFastFindPath {
    public PathCCoA getPath(PointCCoA start, PointCCoA destination, ParametersMovingUnique parametersMovingUnique,
                            double timeAdding);
}
