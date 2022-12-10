package com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.FabricNetworkNodes;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.Hexagon.HexagonTile;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.NetworkNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;

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
