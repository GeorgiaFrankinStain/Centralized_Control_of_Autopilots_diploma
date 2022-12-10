package com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.Hexagon;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.Node;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;

import java.util.List;

public interface StorageManualNodes {
    public List<Node> neighboringMultipleNodes(
            List<Node> neighborsCentresNodes,
            PointCCoA coordinatePreviousNode,
            double timeTravelFromStartPreviousNode
    );

    public void addNodeAnyTime(PointCCoA coordinate);
}
