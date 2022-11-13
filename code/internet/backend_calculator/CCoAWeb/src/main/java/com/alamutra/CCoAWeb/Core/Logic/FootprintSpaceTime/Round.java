package com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime;

public interface Round {
    public PointCCoA getCenter();

    public double getRadius();

    public boolean isIncludes(PointCCoA pointCCoA);

    public Round getApproximation(double timeFirst, Round secondRound, double timeSecond, double timeProximity);
}
