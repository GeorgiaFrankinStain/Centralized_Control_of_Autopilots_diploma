package com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.Hexagon;

import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.Node;
import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoA;

import java.util.List;

public interface StorageManualNodes {
    public List<Node> neighboringMultipleNodes(
            List<Node> neighborsCentresNodes,
            PointCCoA coordinatePreviousNode,
            double timeTravelFromStartPreviousNode
    );

    public void addNodeAnyTime(PointCCoA coordinate);
}
