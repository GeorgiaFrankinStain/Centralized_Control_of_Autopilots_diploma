package com.alamutra.ccoa.GUI.StatementTaskRendering;

import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;
import com.alamutra.ccoa.Logic.Position;

public interface DataFootprintForRendering {
    public int getIdMovingObject();
    public Position getLocalOriginForPointMovingObject();
    public String getType();
    public ParametersMoving getMovingObject();
}
