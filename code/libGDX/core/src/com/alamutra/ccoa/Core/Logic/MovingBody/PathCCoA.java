package com.alamutra.ccoa.Core.Logic.MovingObjects;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;

public interface PathCCoA {
    public int getSize();

    public PointCCoA getPoint(int index);

    public void addPoint(PointCCoA pointCCoA);

    public void addPoint(int index, PointCCoA pointCCoA);

    public void deposeOn(PointCCoA vector);
}
