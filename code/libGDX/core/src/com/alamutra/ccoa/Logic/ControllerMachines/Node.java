package com.alamutra.ccoa.Logic.ControllerMachines;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;

import java.util.List;

public interface Node {
    public List<Node> getNeighboringNodes(double radiusMovingObject, ParametersMoving parametersMoving);

    public double getCoveredDistanceFrom(PointCCoA from);

    public double getEstimatedDistanceToDestination(PointCCoA to);

    public PointCCoA getCoordinate();

    public void setTimeTravelFromStart(Double timeTravelFromStart);

    public Double getTimeTravelFromStart();
}
