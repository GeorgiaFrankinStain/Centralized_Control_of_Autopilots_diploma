package com.alamutra.ccoa.Core.Logic.ControllerMachines.Hexagon;

import com.alamutra.ccoa.Core.Logic.ControllerMachines.Node;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;

import java.util.List;

public interface StorageManualNodes {
    public List<Node> neighboringMultipleNodes(
            List<Node> neighborsCentresNodes,
            PointCCoA coordinatePreviousNode,
            double timeTravelFromStartPreviousNode
    );

    public void addNodeAnyTime(PointCCoA coordinate);
}
