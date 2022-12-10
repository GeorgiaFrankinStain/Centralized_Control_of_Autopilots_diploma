package com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.AStar;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.AlhorithmFastFindPath;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.FabricNetworkNodes.FabricNetworkNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.NetworkNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.*;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.PathCCoA;

public class AStarSpaceTimePlanarGraphClass implements AlhorithmFastFindPath {
    private FabricNetworkNodes fabricNetworkNodes;
    private FootprintsSpaceTime footprintsSpaceTime;

    public AStarSpaceTimePlanarGraphClass(
            FabricNetworkNodes fabricNetworkNodes,
            FootprintsSpaceTime footprintsSpaceTime
    ) {
        this.fabricNetworkNodes = fabricNetworkNodes;
        this.footprintsSpaceTime = footprintsSpaceTime;
    }

    /**
     * created on pceudocode (https://gist.github.com/damienstanton/7de65065bf584a43f96a)
     *
     * @param start
     * @param destination
     * @return
     */
    @Override
    public PathCCoA getPath(PointCCoA start,
                            PointCCoA destination,
                            ParametersMovingUnique parametersMovingUnique,
                            double timeAdding) {
        NetworkNodes newNetworkNodes = this.fabricNetworkNodes.getNewNetworkNodes();

        AStartPathSearchEngine aStartPathSearchEngine = new AStartPathSearchEngineClass(
                start,
                destination,
                parametersMovingUnique,
                timeAdding,
                newNetworkNodes,
                footprintsSpaceTime
        );
        return aStartPathSearchEngine.getPath();
    }

}

