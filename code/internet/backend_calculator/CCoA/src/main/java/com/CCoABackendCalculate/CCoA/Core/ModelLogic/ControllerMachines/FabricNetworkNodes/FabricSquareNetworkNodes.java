package com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.FabricNetworkNodes;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.NetworkNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.SquareNetworkNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;

public class FabricSquareNetworkNodes implements FabricNetworkNodes {

    private ParametersMovingUnique parametersMovingUnique;

    public FabricSquareNetworkNodes(ParametersMovingUnique parametersMovingUnique) {
        this.parametersMovingUnique = parametersMovingUnique;
    }

    @Override
    public NetworkNodes getNewNetworkNodes() {
        return new SquareNetworkNodes(parametersMovingUnique);
    }
}
