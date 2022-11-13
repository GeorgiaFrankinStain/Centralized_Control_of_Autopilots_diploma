package com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.CCoAWeb.Core.ViewSettingRenderingTasks.TypeMachinesBody;

public interface BuilderParametersMovingUnique {
    public void setSpeed(double speed);

    public void setShape(PolygonCCoA shape);

    public void setTypeMachinesBody(TypeMachinesBody typeMachinesBody);

    public ParametersMovingUnique getParametersMoving();
}
