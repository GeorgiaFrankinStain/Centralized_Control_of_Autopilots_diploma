package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Point;

import java.util.List;

public interface Node {
    public List<Node> getNeighboringNodes(double radiusMovingObject);

    public double getCoveredDistanceFrom(Point from);

    public double getEstimatedToDestination(Point to);

    public Point getCoordinate();

    public void setTimeTravelFromStart(Double timeTravelFromStart);

    public Double getTimeTravelFromStart();
}
