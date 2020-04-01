package GUI.StatementTaskRendering;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprint;
import Logic.FootprintSpaceTime.PolygonExtended;

import java.util.Iterator;

public interface PoolPhisicalBodysForRendering {
    public void fillYourself(PolygonExtended areaRendering, int gameTime);
    public DataFootprintForRendering getDataFootprint(int IdObject);


    Iterator<DataFootprintForRendering> iterator();
}