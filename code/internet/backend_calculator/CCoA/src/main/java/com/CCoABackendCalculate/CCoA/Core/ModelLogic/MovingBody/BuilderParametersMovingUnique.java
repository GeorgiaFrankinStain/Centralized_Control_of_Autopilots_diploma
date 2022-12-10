package com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;

public interface BuilderParametersMovingUnique {
    public void setSpeed(double speed);

    public void setShape(PolygonCCoA shape);

    public void setTypeMachinesBody(TypeMachinesBody typeMachinesBody);

    public ParametersMovingUnique getParametersMoving();
}
