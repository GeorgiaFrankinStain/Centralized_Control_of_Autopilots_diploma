package com.alamutra.ccoa.Core.Logic.ControllerMachines;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMovingUnique;
import com.alamutra.ccoa.Core.Logic.MovingBody.PathCCoA;

public interface AlhorithmFastFindPath {
    public PathCCoA getPath(PointCCoA start, PointCCoA destination, ParametersMovingUnique parametersMovingUnique,
                            double timeAdding);
}
