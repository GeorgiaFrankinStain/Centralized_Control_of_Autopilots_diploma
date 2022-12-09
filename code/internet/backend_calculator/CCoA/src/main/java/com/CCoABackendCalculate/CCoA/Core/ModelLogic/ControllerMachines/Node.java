package com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoA;

import java.util.List;

public interface Node {

    public PointCCoA getCoordinate();

    public double getEstimateDistanceToDestinationHeuristicFunction(PointCCoA to);

    public Double getActualTimeTravelFromStart();

    public List<Node> getNeighboringNodes();
}
