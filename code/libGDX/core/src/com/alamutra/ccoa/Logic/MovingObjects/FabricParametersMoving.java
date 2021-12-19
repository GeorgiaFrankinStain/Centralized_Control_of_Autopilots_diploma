package com.alamutra.ccoa.Logic.MovingObjects;

import com.alamutra.ccoa.GUI.StatementTaskRendering.TypeMachinesBody;

public interface FabricParametersMoving {
    public ParametersMoving getMoving(TypeMachinesBody typeMachinesBody);
    public BuilderParametersMoving getBuilderMoving(TypeMachinesBody typeMachinesBody);
}

