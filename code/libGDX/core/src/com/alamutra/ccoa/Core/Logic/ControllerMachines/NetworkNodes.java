package com.alamutra.ccoa.Core.Logic.ControllerMachines;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;

import java.util.List;

public interface NetworkNodes {
    public List<Node> getNeighboringNodes(PointCCoA coordinates, double timeTravelFromStart);

    public List<Node> getNeighboringNodes(Node currentNode);
}
