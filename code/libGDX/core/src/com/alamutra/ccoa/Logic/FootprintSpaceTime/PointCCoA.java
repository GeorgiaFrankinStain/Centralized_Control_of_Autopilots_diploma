package com.alamutra.ccoa.Logic.FootprintSpaceTime;

public interface PointCCoA {
    public double getX();

    public double getY();

    public void setX(double x);

    public void setY(double y);

    public PointCCoA getMultipliedVector(double multiplier);

    public PointCCoA clone();

    public boolean equals(Object obj);

    public String toString();

    public boolean isLeftRelative(PointCCoA startLine, PointCCoA endLine);


    public double getAngleRotareRelative(PointCCoA origin);

    public PointCCoA getRotateRelative(PointCCoA origin, double angle);

    public PointCCoA getApproximationWith(
            double timeFirst,
            PointCCoA secondPointCCoA,
            double timeSecond,
            double timeProximity
    );

    public int getQuarter(PointCCoA origin);

    public double getLengthVector();

    public double getDistanceToPoint(PointCCoA pointCCoA);

    public double getDistanceToPointProjectionX(PointCCoA pointCCoA);

    public double getDistanceToPointProjectionY(PointCCoA pointCCoA);

    public PointCCoA getVector(PointCCoA pointCCoA);

    public PointCCoA getInversion();

    public PointCCoA getDeposeOn(PointCCoA vector);

    public void deposeOn(PointCCoA vector);
}
