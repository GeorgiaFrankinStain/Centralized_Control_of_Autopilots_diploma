package Logic.FootprintSpaceTime;

import Logic.GlobalVariable;

public class RoundClass implements Round {
    private Point center;
    private double radius;

    public RoundClass(Point center, double radius) {
        assert (center != null);
        this.center = center;
        assert (radius >= 0);
        this.radius = radius;
    }

    @Override
    public Point getCenter() {
        return this.center;
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    @Override
    public boolean isIncludes(Point point) {
        double distanceToCenter = point.getDistanceToPoint(this.getCenter());
        boolean isPointInsideRound = distanceToCenter <= this.getRadius();
        return isPointInsideRound;
    }

    @Override
    public Round getApproximation(double timeFirst, Round secondRound, double timeSecond, double timeProximity) {

        Point newCenter = center.getApproximationWith(
                timeFirst,
                secondRound.getCenter(),
                timeSecond,
                timeProximity
        );
        double newRadius = (radius + secondRound.getRadius()) / 2;

        return new RoundClass(newCenter, newRadius);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        Round other = (Round) obj;

        boolean isCenterShared = this.getCenter().equals(other.getCenter());
        boolean isRadiusEquals = GlobalVariable.equalsNumber(this.getRadius(), other.getRadius());

        return isCenterShared && isRadiusEquals;
    }

    @Override
    public int hashCode() {
        int twoPow32 = 2147483647;
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.getRadius() % twoPow32);
        int centerHash = this.getCenter().hashCode();
        result = prime * result + centerHash;
        result = prime * result + prime;
        return result;
    }


    @Override
    public String toString() {
        return "(center: " + this.center + " radius: " + this.radius + ")";
    }
}