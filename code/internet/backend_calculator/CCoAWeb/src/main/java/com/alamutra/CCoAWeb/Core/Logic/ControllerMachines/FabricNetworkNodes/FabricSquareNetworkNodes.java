package com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.FabricNetworkNodes;

import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.NetworkNodes;
import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.SquareNetworkNodes;
import com.alamutra.CCoAWeb.Core.Logic.MovingBody.ParametersMovingUnique;

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
