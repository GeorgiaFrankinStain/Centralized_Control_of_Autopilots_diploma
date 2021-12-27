package com.alamutra.ccoa.StatementTaskRendering;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonCCoA;

import java.util.Iterator;

public interface PoolDataFootprintForRendering {
    public void fillYourself(PolygonCCoA areaRendering, int gameTime);
    public DataFootprintForRendering getDataFootprint(int IdObject);


    Iterator<DataFootprintForRendering> iterator();
}
