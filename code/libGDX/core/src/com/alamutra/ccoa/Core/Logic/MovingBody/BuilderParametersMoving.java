package com.alamutra.ccoa.Core.Logic.MovingBody;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeMachinesBody;

public interface BuilderParametersMoving {
    public void setSpeed(double speed);

    public void setShape(PolygonCCoA shape);

    public void setTypeMachinesBody(TypeMachinesBody typeMachinesBody);

    public ParametersMoving getParametersMoving();
}
