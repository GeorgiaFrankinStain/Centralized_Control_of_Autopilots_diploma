package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import java.util.List;

public interface PolygonCCoA {
    public int getCountPoints();

    public PointCCoA getPoint(int index);

    public void addPoint(PointCCoA newPointCCoA);

    public void addPoint(PointCCoA[] pointCCoAS);

    public void addPoint(List<PointCCoA> pointCCoAS);

    public void addAllPoint(List<PointCCoA> newPointCCoAS);

    public void insertPoint(int index, PointCCoA newPointCCoA);

    public void setPoint(int index, PointCCoA newPointCCoA);

    public void rotateRelative(PointCCoA origin, double angle);

    public void deposeOn(PointCCoA vector);

    public boolean contains(PointCCoA desirededPointCCoA);

    public boolean enteringPoint(PointCCoA position);

    public boolean intersectionPolygon(PolygonCCoA secondPolygon);

    public boolean intersectionLine(PointCCoA startLine, PointCCoA endLine);

    public boolean intersectionLine(LineCut lineCut);

    public boolean isLiesInsideThe(Round round);

    public PointCCoA getCenterAverage();

    public Double[] getFormatDoubleArray();

    public boolean equals(Object obj);

    public PolygonCCoA clone();
}
