package com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.FabricNetworkNodes;

import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.NetworkNodes;
import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.SquareNetworkNodes;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.ParametersMovingUnique;

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
