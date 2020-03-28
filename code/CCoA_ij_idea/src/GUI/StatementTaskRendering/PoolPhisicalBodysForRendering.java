package GUI.StatementTaskRendering;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingBody;
import Logic.FootprintSpaceTime.PolygonExtended;

import java.util.Iterator;

public interface PoolPhisicalBodysForRendering {
    public void fillYourself(PolygonExtended areaRendering, int gameTime);
    public RenderingBody getPhisicalBody(int ID);


    Iterator<RenderingBody> iterator();
}