package com.alamutra.CCoAWeb.Core.Logic.MovingBody;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoA;

public interface PathCCoA {
    public int getSize();

    public PointCCoA getPoint(int index);

    public PointCCoA getPointLast();

    public void addPoint(PointCCoA pointCCoA);

    public void addPoint(int index, PointCCoA pointCCoA);

    public void deposeOn(PointCCoA vector);

}
