package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

public interface LineCut {
    public PointCCoA getStart();
    public PointCCoA getEnd();
    public boolean intersectionLineCut(LineCut secondLineCut);
    public boolean intersection(PointCCoA testedPointCCoA);
    public double length();
}
