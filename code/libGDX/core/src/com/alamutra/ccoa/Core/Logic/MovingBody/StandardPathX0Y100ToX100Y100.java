package com.alamutra.ccoa.Core.Logic.MovingBody;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoAClass;

public class StandardPathX0Y100ToX100Y100 implements PathCCoA {

    private PathCCoA path = new PathCCoAClass();

    public StandardPathX0Y100ToX100Y100() {
        path.addPoint(new PointCCoAClass(0, 100));
        path.addPoint(new PointCCoAClass(100, 100));
    }

    @Override
    public int getSize() {
        return path.getSize();
    }

    @Override
    public PointCCoA getPoint(int index) {
        return path.getPoint(index);
    }

    @Override
    public PointCCoA getPointLast() {
        return path.getPointLast();
    }

    @Override
    public void addPoint(PointCCoA pointCCoA) {
        path.addPoint(pointCCoA);
    }

    @Override
    public void addPoint(int index, PointCCoA pointCCoA) {
        path.addPoint(index, pointCCoA);
    }

    @Override
    public void deposeOn(PointCCoA vector) {
        path.deposeOn(vector);
    }

    @Override
    public String toString() {
        return path.toString();
    }
}
