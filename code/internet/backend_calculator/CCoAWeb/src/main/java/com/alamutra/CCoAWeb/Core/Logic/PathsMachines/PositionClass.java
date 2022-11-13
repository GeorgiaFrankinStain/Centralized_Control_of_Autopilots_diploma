package com.alamutra.CCoAWeb.Core.Logic.PathsMachines;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.Logic.GlobalVariable;
import com.alamutra.CCoAWeb.Core.Logic.Position;

public class PositionClass implements Position {
    private PointCCoA pointCCoA;
    private double rotation;

    public PositionClass(PointCCoA pointCCoA, double rotation) {
        this.pointCCoA = pointCCoA;
        this.rotation = rotation;
        toTestInputDataIsValid(pointCCoA, rotation);
    }

    @Override
    public PointCCoA getCoordinates() {
        return this.pointCCoA;
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
    ) {
        boolean isReverseOrder = timeFirst > timeSecond;
        if (isReverseOrder) {
            return secondPosition.getApproximation(timeSecond, this, timeFirst, timeApproximation);
        } else {
            return getApproximationAscendingOrderOfTime(timeFirst, secondPosition, timeSecond, timeApproximation);
        }
    }

    @Override
    public double getRotationDegree() {
        return this.getRotation() * 180 / Math.PI;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }


        Position other = (Position) obj;
        PointCCoA thisCoordinate = this.getCoordinates();
        PointCCoA otherCoordinate = other.getCoordinates();
        boolean coordinateEquals = thisCoordinate.equals(otherCoordinate);
        boolean rotationEquals = GlobalVariable.equalsNumber(this.getRotation(), other.getRotation());
        return coordinateEquals && rotationEquals;
    }

    @Override
    public String toString() {
        return "(" + pointCCoA.toString() + " rotation: " + rotation + ")";
    }

    @Override
    public int hashCode() {
        int twoPow32 = 2147483647;
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.getRotation() % twoPow32);
        result = prime * result + (int) (this.getCoordinates().hashCode());
        result = prime * result + prime;
        return result;
    }

    private Position getApproximationAscendingOrderOfTime(
            double timeFirst,
            Position secondPosition,
            double timeSecond,
            double timeApproximation
    ) {
        PointCCoA approximationPointCCoA = this.pointCCoA.getApproximationWith(
                timeFirst,
                secondPosition.getCoordinates(),
                timeSecond,
                timeApproximation
        );
        double approximationAngle =
                approximationAngle(secondPosition.getRotation(), timeSecond, this.getRotation(), timeFirst, timeApproximation);
        verificationOutputData(approximationAngle);

        Position result = new PositionClass(approximationPointCCoA, approximationAngle);
        return result;
    }


    private double approximationAngle(
            double angle1,
            double time1,
            double angle2,
            double time2,
            double timeApproximation
    ) {
        assert (isTimeApproximationIncludeInTimeInterval(time1, time2, timeApproximation));
        if (angle1 <= angle2) {
            return approximationAngleAscendingOrderOfAngle(angle1, time1, angle2, time2, timeApproximation);
        } else {
            return approximationAngleAscendingOrderOfAngle(angle2, time2, angle1, time1, timeApproximation);
        }
    }

    private boolean isTimeApproximationIncludeInTimeInterval(
            double time1,
            double time2,
            double timeApproximation
    ) {
        if (time1 > time2) {
            double temp = time1;
            time1 = time2;
            time2 = temp;
        }

        return time1 <= timeApproximation && timeApproximation <= time2;
    }


    private double approximationAngleAscendingOrderOfAngle(
            double angle1,
            double time1,
            double angle2,
            double time2,
            double timeApproximation
    ) {
        assert(angle1 <= angle2);

        double coefficientApproximation = percentageProximityFromAngle1ToFoundAngle(time1, time2, timeApproximation);

        double mediumAngle = mediumAngle(angle1, angle2);
        double angularDistanceToMediumAngle = mediumAngle - angle1;
        double rotationVectorThatConvertsFirstAngleToSecond = angularDistanceToMediumAngle * 2;

        double angularVectorThatConvertsFirstAngleToFoundApproximationAngle =
                rotationVectorThatConvertsFirstAngleToSecond * coefficientApproximation;
        double approximationAngle = angle1 + angularVectorThatConvertsFirstAngleToFoundApproximationAngle;

        return approximationAngle;
    }

    private double percentageProximityFromAngle1ToFoundAngle(double time1, double time2, double timeProximity) {
        if (time1 <= time2) {
            return percentageProximity(time1, time2, timeProximity);
        } else {
            return 1 - percentageProximity(time2, time1, timeProximity);
        }
    }

    private double percentageProximity(
            double time1,
            double time2,
            double timeProximity
    ) {
        double timeInterval = time2 - time1;
        boolean isIntervalIs0 = GlobalVariable.equalsNumber(0, timeInterval);
        if (isIntervalIs0) {
            double inMiddle = 0.5;
            return inMiddle;
        }
        double percentProximity = (timeProximity - time1) / timeInterval;
        return percentProximity;
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
    }

    private void verificationOutputData(double rotation) {
        assert (isRotationIsValid(rotation));
    }

    private boolean isRotationIsValid(double rotation) {
        return -Math.PI <= rotation && rotation <= Math.PI;
    }

    private void toTestInputDataIsValid(PointCCoA pointCCoA, double rotation) {
        if (!isRotationIsValid(rotation)) {
            throw new IllegalArgumentException("rotation is no valid");
        }
        assert (pointCCoA != null);
    }
}
