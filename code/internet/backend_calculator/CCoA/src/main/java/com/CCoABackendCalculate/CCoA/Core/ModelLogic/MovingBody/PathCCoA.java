package com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;

public interface PathCCoA {
    public int getSize();

    public PointCCoA getPoint(int index);

    public PointCCoA getPointLast();

    public void addPoint(PointCCoA pointCCoA);

    public void addPoint(int index, PointCCoA pointCCoA);

    public void deposeOn(PointCCoA vector);

}
