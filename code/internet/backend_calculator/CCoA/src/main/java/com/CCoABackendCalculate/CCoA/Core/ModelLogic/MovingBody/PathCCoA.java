package com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoA;

public interface PathCCoA {
    public int getSize();

    public PointCCoA getPoint(int index);

    public PointCCoA getPointLast();

    public void addPoint(PointCCoA pointCCoA);

    public void addPoint(int index, PointCCoA pointCCoA);

    public void deposeOn(PointCCoA vector);

}
