package com.alamutra.ccoa.Core.Logic.MovingObjects;

import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeMachinesBody;

public interface FabricParametersMoving {
    public ParametersMoving getMoving(TypeMachinesBody typeMachinesBody);
    public BuilderParametersMoving getBuilderMoving(TypeMachinesBody typeMachinesBody);
}
