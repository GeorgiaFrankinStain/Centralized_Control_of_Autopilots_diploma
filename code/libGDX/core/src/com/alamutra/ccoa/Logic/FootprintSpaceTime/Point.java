package com.alamutra.ccoa.Logic.FootprintSpaceTime;

public interface Point {
    public double getX();

    public double getY();

    public void setX(double x);

    public void setY(double y);

    public Point getMultipliedVector(double multiplier);

    public Point clone();

    public boolean equals(Object obj);

    public String toString();

    public boolean isLeftRelative(Point startLine, Point endLine);


    public double getAngleRotareRelative(Point origin);

    public Point getRotateRelative(Point origin, double angle);

    public Point getApproximationWith(
            double timeFirst,
            Point secondPoint,
            double timeSecond,
            double timeProximity
    );

    public int getQuarter(Point origin);

    public double getLengthVector();

    public double getDistanceToPoint(Point point);

    public double getDistanceToPointProjectionX(Point point);

    public double getDistanceToPointProjectionY(Point point);

    public Point getVector(Point point);

    public Point getInversion();

    public Point getDeposeOn(Point vector);

    public void deposeOn(Point vector);

}
