package com.alamutra.CCoAWeb.Core.ViewSettingRenderingTasks;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;

import java.util.Iterator;

public interface PoolDataFootprintForRendering {
    public void fillYourself(PolygonCCoA areaRendering, double gameTime);
    public DataFootprintForRendering getDataFootprint(int IdObject);


    Iterator<DataFootprintForRendering> iterator();
}
