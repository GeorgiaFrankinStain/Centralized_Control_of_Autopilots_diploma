package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Point;
import Logic.MovingObjects.MovingObject;

import java.util.List;

public interface Node {
    public List<Node> getNeighboringNodes(double radiusMovingObject, MovingObject movingObject);

    public double getCoveredDistanceFrom(Point from);

    public double getEstimatedDistanceToDestination(Point to);

    public Point getCoordinate();

    public void setTimeTravelFromStart(Double timeTravelFromStart);

    public Double getTimeTravelFromStart();
}
