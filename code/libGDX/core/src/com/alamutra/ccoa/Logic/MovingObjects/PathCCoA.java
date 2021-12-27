package com.alamutra.ccoa.Logic.MovingObjects;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.PointCCoA;

public interface PathCCoA {
    public int getSize();

    public PointCCoA getPoint(int index);

    public void addPoint(PointCCoA pointCCoA);

    public void addPoint(int index, PointCCoA pointCCoA);

    public void deposeOn(PointCCoA vector);
}
