package com.alamutra.ccoa.Core.SettingRenderingTasks;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;

import java.util.Iterator;

public interface PoolDataFootprintForRendering {
    public void fillYourself(PolygonCCoA areaRendering, double gameTime);
    public DataFootprintForRendering getDataFootprint(int IdObject);


    Iterator<DataFootprintForRendering> iterator();
}
