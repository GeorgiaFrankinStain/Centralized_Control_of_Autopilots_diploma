package com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.FabricNetworkNodes;

import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.Hexagon.HexagonTile;
import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.NetworkNodes;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.ParametersMovingUnique;

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
