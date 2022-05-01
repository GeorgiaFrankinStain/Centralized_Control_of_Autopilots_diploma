package com.alamutra.ccoa.Core.Logic.ControllerMachines.AStar;

import com.alamutra.ccoa.Core.Logic.ControllerMachines.AlhorithmFastFindPath;
import com.alamutra.ccoa.Core.Logic.ControllerMachines.FabricNetworkNodes.FabricNetworkNodes;
import com.alamutra.ccoa.Core.Logic.ControllerMachines.NetworkNodes;
import com.alamutra.ccoa.Core.Logic.ControllerMachines.Node;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.*;
import com.alamutra.ccoa.Core.Logic.IndexLayerClass;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMovingUnique;
import com.alamutra.ccoa.Core.Logic.MovingBody.PathCCoA;
import com.alamutra.ccoa.Core.Logic.MovingBody.PathCCoAClass;

import java.util.*;

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

