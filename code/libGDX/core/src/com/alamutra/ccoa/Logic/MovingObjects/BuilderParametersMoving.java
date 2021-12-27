package com.alamutra.ccoa.Logic.MovingObjects;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.StatementTaskRendering.TypeMachinesBody;

public interface BuilderParametersMoving {
    public void setSpeed(double speed);

    public void setShape(PolygonCCoA shape);

    public void setTypeMachinesBody(TypeMachinesBody typeMachinesBody);

    public ParametersMoving getParametersMoving();
}
