package com.alamutra.ccoa.Logic.ControllerMachines;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Point;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;

import java.util.List;

public interface Node {
    public List<Node> getNeighboringNodes(double radiusMovingObject, ParametersMoving parametersMoving);

    public double getCoveredDistanceFrom(Point from);

    public double getEstimatedDistanceToDestination(Point to);

    public Point getCoordinate();

    public void setTimeTravelFromStart(Double timeTravelFromStart);

    public Double getTimeTravelFromStart();
}
