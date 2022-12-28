package com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody;

import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;

public interface FabricParametersMovingUnique {
    public ParametersMovingUnique getMoving(TypeMachinesBody typeMachinesBody, int idParametersMovingUnique);
    public BuilderParametersMovingUnique getNewBuilderMoving(TypeMachinesBody typeMachinesBody);
}
