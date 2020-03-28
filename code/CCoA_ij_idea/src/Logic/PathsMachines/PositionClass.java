package Logic.PathsMachines;

import Logic.FootprintSpaceTime.Point;
import Logic.Position;

public class PositionClass implements Position {
    private Point point;
    private double rotation;

    public PositionClass(Point point, double rotation) {
        this.point = point;
        this.rotation = rotation;
    }

    @Override
    public Point getPosition() {
        return this.point;
    }

    @Override
    public double getRotation() {
        return this.rotation;
    }
}
