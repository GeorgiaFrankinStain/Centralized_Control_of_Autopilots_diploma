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
    public Point getCoordinats() {
        return this.point;
    }

    @Override
    public double getRotation() {
        return this.rotation;
    }

    @Override
    public double getRotationDegree() {
        return this.getRotation() * 180 / Math.PI;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;



        Position other = (Position) obj;
        return (this.getCoordinats().equals(other.getCoordinats()))
                       && (this.getRotation() - other.getRotation() < 0.01); //FIXME MAGIC_NUMBER
    }

    @Override
    public String toString() {
        return "rotation: " + rotation + " " + point.toString();
    }
}
