package Logic.PathsMachines;

import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PointClass;
import Logic.GlobalVariable;
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
    public Position getApproximation(
            double timeFirst,
            Position secondPosition,
            double timeSecond,
            double timeApproximation
    ) { //FIXME add tests
        Point approximationPoint = this.point.getApproximationWith(
                timeFirst,
                secondPosition.getCoordinats(),
                timeSecond,
                timeApproximation
        );
        double mediumAngle = mediumAngle(secondPosition.getRotation(), this.getRotation());
        assert(-Math.PI <= mediumAngle && mediumAngle <= Math.PI);
        Position result = new PositionClass(approximationPoint, mediumAngle);
        return result;
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
                && GlobalVariable.equalsNumber(this.getRotation(), other.getRotation());
    }

    @Override
    public String toString() {
        return "rotation: " + rotation + " " + point.toString();
    }

    private double mediumAngle(double a, double b) {
        double halfRound = Math.PI;
        double fullRound = halfRound * 2;

        a = a % fullRound;
        b = b % fullRound;

        double sum = a + b;
        if (fullRound < sum && sum < fullRound * 1.5) {
            sum = sum % halfRound;
        }
        return sum / 2;

        /*
        double halfRound = Math.PI;
        double fullRound = halfRound * 2;

        a = a % fullRound;
        b = b % fullRound;

        double sum = a + b;
        if (fullRound < sum && sum < fullRound * 1.5) {
            sum = sum % halfRound;
        }
        return sum / 2;*/
    }

}
