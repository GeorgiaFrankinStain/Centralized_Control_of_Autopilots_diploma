package com.alamutra.CCoAWeb.Core.SettingRenderingTasks;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PolygonCCoA;

import java.util.Iterator;

public interface PoolDataFootprintForRendering {
    public void fillYourself(PolygonCCoA areaRendering, double gameTime);
    public DataFootprintForRendering getDataFootprint(int IdObject);


    Iterator<DataFootprintForRendering> iterator();
}
