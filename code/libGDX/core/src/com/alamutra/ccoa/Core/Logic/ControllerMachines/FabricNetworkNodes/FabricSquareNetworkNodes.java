package com.alamutra.ccoa.Core.Logic.ControllerMachines.FabricNetworkNodes;

import com.alamutra.ccoa.Core.Logic.ControllerMachines.NetworkNodes;
import com.alamutra.ccoa.Core.Logic.ControllerMachines.SquareNetworkNodes;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMovingUnique;

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
