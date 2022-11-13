package com.alamutra.CCoAWeb.Core.Logic.MovingBody;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.CCoAWeb.Core.SettingRenderingTasks.TypeMachinesBody;

public interface BuilderParametersMovingUnique {
    public void setSpeed(double speed);

    public void setShape(PolygonCCoA shape);

    public void setTypeMachinesBody(TypeMachinesBody typeMachinesBody);

    public ParametersMovingUnique getParametersMoving();
}
