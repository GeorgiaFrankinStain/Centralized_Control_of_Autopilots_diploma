package com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoA;

import java.util.List;

public interface NetworkNodes {
    public List<Node> getNeighboringNodes(PointCCoA coordinates, double timeTravelFromStart);

    public List<Node> getNeighboringNodes(Node currentNode);

    public Node createNode(PointCCoA coordinates, double timeTravelFromStart);

    public void createManualNodeAnyTime(PointCCoA coordinates);
}
