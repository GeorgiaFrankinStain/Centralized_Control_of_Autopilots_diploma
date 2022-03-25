package com.alamutra.ccoa.Core.Logic.ControllerMachines;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;

import java.util.List;

public interface Node {
    public PointCCoA getCoordinate();

    public double getEstimateDistanceToDestinationHeuristicFunction(PointCCoA to);

    public Double getActualTimeTravelFromStart();

    public List<Node> getNeighboringNodes();
}
