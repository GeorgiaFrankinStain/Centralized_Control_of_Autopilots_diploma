package com.alamutra.ccoa.Logic.MovingObjects;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonExtended;
import com.alamutra.ccoa.StatementTaskRendering.TypeMachinesBody;

public interface BuilderParametersMoving {
    public void setSpeed(double speed);

    public void setShape(PolygonExtended shape);

    public void setTypeMachinesBody(TypeMachinesBody typeMachinesBody);

    public ParametersMoving getParametersMoving();
}
