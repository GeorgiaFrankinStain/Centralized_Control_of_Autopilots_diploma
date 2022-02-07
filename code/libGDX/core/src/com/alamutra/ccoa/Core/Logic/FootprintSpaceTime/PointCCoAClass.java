package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Core.Logic.GlobalVariable;

public class PointCCoAClass implements PointCCoA {
    private double x;
    private double y;

    public PointCCoAClass(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double  x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public PointCCoA getMultipliedVector(double multiplier) {
        PointCCoA result = new PointCCoAClass(this.getX() * multiplier, this.getY() * multiplier);
        return result;
    }

    @Override
    public PointCCoA clone() {
        return new PointCCoAClass(this.getX(), this.getY());
    }



    @Override
    public int hashCode() {
        //FIXME hash code from double is good?
        int  twoPow32 = 2147483647;
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.getY() % twoPow32);
        result = prime * result + (int) (this.getY() / twoPow32);
        result = prime * result + (int) (this.getX() % twoPow32);
        result = prime * result + (int) (this.getX() / twoPow32);
        result = prime * result + prime;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;



        PointCCoA other = (PointCCoA) obj;
        boolean xEquals = GlobalVariable.equalsNumber(this.getX(), other.getX());
        boolean yEquals = GlobalVariable.equalsNumber(this.getY(), other.getY());
        return xEquals && yEquals;
    }
    @Override
    public String toString() {
        return "(x: " + this.getX() + "   y: " + this.getY() + ")";
    }



    /**
     * (https://en.wikipedia.org/wiki/Cross_product)
     * (https://stackoverflow.com/questions/1560492/how-to-tell-whether-a-point-is-to-the-right-or-left-side-of-a-line)
     * Use the sign of the determinant of vectors (AB,AM), where M(X,Y) is the query point:
     * position = sign((Bx - Ax) * (Y - Ay) - (By - Ay) * (X - Ax))
     */
    @Override
    public boolean isLeftRelative(PointCCoA startLine, PointCCoA endLine){
        return ((endLine.getX() - startLine.getX())*(this.getY() - startLine.getY()) -
                (endLine.getY() - startLine.getY())*(this.getX() - startLine.getX())) > 0;
    }


    @Override
    public double getAngleRotareRelative(PointCCoA origin) {
        PointCCoA vector = new PointCCoAClass(
                this.getX() - origin.getX(),
                this.getY() - origin.getY()
        );
        return Math.atan2(vector.getY(), vector.getX());
    }

    @Override
    public PointCCoA getRotateRelative(PointCCoA origin, double angle) {
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        double xDistanceOnProjection = this.getX() - origin.getX();
        double yDistanceOnProjection = this.getY() - origin.getY();
        double rotatedX =
                cosAngle * xDistanceOnProjection
                        - sinAngle * yDistanceOnProjection + origin.getX();
        double rotatedY =
                sinAngle * xDistanceOnProjection
                        + cosAngle * yDistanceOnProjection + origin.getY();

        return new PointCCoAClass(rotatedX, rotatedY);
    }

    @Override
    public PointCCoA getApproximationWith(double timeFirst, PointCCoA secondPointCCoA, double timeSecond, double timeProximity) {
        if (timeFirst > timeSecond) {
            return secondPointCCoA.getApproximationWith(timeSecond, this, timeFirst, timeProximity);
        }

        double timeInterval = timeSecond - timeFirst;
        double percentProximityToFirst = (timeProximity - timeFirst) / timeInterval;

        PointCCoA vector = secondPointCCoA.getVector(this);
        PointCCoA margin = vector.getMultipliedVector(percentProximityToFirst);

        PointCCoA result = this.clone();
        result.deposeOn(margin);
        return result;
    }

    @Override
    public int getQuarter(PointCCoA origin) {
        PointCCoA radiusVertor = new PointCCoAClass(
                this.getX() - origin.getX(),
                this.getY() - origin.getY()
        );

        boolean isRight = radiusVertor.getX() >= 0; //FIXME situation point on line
        boolean isLeft = !isRight;
        boolean isTop = radiusVertor.getY() >= 0;
        boolean isBottom = !isTop;

        if (isRight && isTop) {
            return 0;
        } else if (isLeft && isTop) {
            return 1;
        } else if (isLeft && isBottom) {
            return 2;
        } else if (isRight && isBottom) {
            return 3;
        }

        assert (false);
        return -1;
    }

    @Override
    public double getLengthVector() {
        return Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY());
    }

    @Override
    public double getDistanceToPoint(PointCCoA pointCCoA) {
        PointCCoA vector = this.getVector(pointCCoA);
        return vector.getLengthVector();
    }

    @Override
    public double getDistanceToPointProjectionX(PointCCoA pointCCoA) {
        return Math.abs(this.getX() - pointCCoA.getX());
    }

    @Override
    public double getDistanceToPointProjectionY(PointCCoA pointCCoA) {
        return Math.abs(this.getY() - pointCCoA.getY());
    }

    @Override
    public PointCCoA getVector(PointCCoA pointCCoA) {
        return new PointCCoAClass(this.getX() - pointCCoA.getX(), this.getY() - pointCCoA.getY());
    }

    @Override
    public PointCCoA getInversion() {
        return new PointCCoAClass(-this.getX(), -this.getY());
    }

    @Override
    public PointCCoA getDeposeOn(PointCCoA vector) {
        return new PointCCoAClass(this.getX() + vector.getX(), this.getY() + vector.getY());
    }

    @Override
    public void deposeOn(PointCCoA vector) {
        this.setX(this.getX() + vector.getX());
        this.setY(this.getY() + vector.getY());
    }


}

