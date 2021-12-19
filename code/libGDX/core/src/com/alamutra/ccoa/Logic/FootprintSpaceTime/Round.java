package com.alamutra.ccoa.Logic.FootprintSpaceTime;

public interface Round {
    public Point getCenter();

    public double getRadius();

    public boolean isIncludes(Point point);

    public Round getApproximation(double timeFirst, Round secondRound, double timeSecond, double timeProximity);

}
