package com.alamutra.ccoa.Core.Logic.ControllerMachines.FabricNetworkNodes;

import com.alamutra.ccoa.Core.Logic.ControllerMachines.Hexagon.HexagonTile;
import com.alamutra.ccoa.Core.Logic.ControllerMachines.NetworkNodes;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMovingUnique;

public class FabricHexagonNodes implements FabricNetworkNodes {

    private double allowedErrorCoDirectivityRadian;
    private ParametersMovingUnique parametersMovingUnique;

    public FabricHexagonNodes(double allowedErrorCoDirectivityRadian, ParametersMovingUnique parametersMovingUnique) {
        this.allowedErrorCoDirectivityRadian = allowedErrorCoDirectivityRadian;
        this.parametersMovingUnique = parametersMovingUnique;
    }

    @Override
    public NetworkNodes getNewNetworkNodes() {
        return new HexagonTile(allowedErrorCoDirectivityRadian, parametersMovingUnique);
    }
}
