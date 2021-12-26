package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Logic.GlobalVariable;

public class PointClass implements Point {
    private double x;
    private double y;

    public PointClass(double x, double y) {
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
    public Point getMultipliedVector(double multiplier) {
        Point result = new PointClass(this.getX() * multiplier, this.getY() * multiplier);
        return result;
    }

    @Override
    public Point clone() {
        return new PointClass(this.getX(), this.getY());
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



        Point other = (Point) obj;
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
    public boolean isLeftRelative(Point startLine, Point endLine){
        return ((endLine.getX() - startLine.getX())*(this.getY() - startLine.getY()) -
                (endLine.getY() - startLine.getY())*(this.getX() - startLine.getX())) > 0;
    }


    @Override
    public double getAngleRotareRelative(Point origin) {
        Point vector = new PointClass(
                this.getX() - origin.getX(),
                this.getY() - origin.getY()
        );
        return Math.atan2(vector.getY(), vector.getX());
    }

    @Override
    public Point getRotateRelative(Point origin, double angle) {
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

        return new PointClass(rotatedX, rotatedY);
    }

    @Override
    public Point getApproximationWith(double timeFirst, Point secondPoint, double timeSecond, double timeProximity) {
        if (timeFirst > timeSecond) {
            return secondPoint.getApproximationWith(timeSecond, this, timeFirst, timeProximity);
        }

        double timeInterval = timeSecond - timeFirst;
        double percentProximityToFirst = (timeProximity - timeFirst) / timeInterval;

        Point vector = secondPoint.getVector(this);
        Point margin = vector.getMultipliedVector(percentProximityToFirst);

        Point result = this.clone();
        result.deposeOn(margin);
        return result;
    }

    @Override
    public int getQuarter(Point origin) {
        Point radiusVertor = new PointClass(
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
    public double getDistanceToPoint(Point point) {
        Point vector = this.getVector(point);
        return vector.getLengthVector();
    }

    @Override
    public double getDistanceToPointProjectionX(Point point) {
        return Math.abs(this.getX() - point.getX());
    }

    @Override
    public double getDistanceToPointProjectionY(Point point) {
        return Math.abs(this.getY() - point.getY());
    }

    @Override
    public Point getVector(Point point) {
        return new PointClass(this.getX() - point.getX(), this.getY() - point.getY());
    }

    @Override
    public Point getInversion() {
        return new PointClass(-this.getX(), -this.getY());
    }

    @Override
    public Point getDeposeOn(Point vector) {
        return new PointClass(this.getX() + vector.getX(), this.getY() + vector.getY());
    }

    @Override
    public void deposeOn(Point vector) {
        this.setX(this.getX() + vector.getX());
        this.setY(this.getY() + vector.getY());
    }


}

