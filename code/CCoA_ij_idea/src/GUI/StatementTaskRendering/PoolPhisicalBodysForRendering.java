package GUI.StatementTaskRendering;

import Logic.FootprintSpaceTime.PhisicalBody;
import Logic.FootprintSpaceTime.PolygonExtended;

import java.util.Iterator;

public interface PoolPhisicalBodysForRendering {
    public void fillYourself(PolygonExtended areaRendering, int gameTime);
    public PhisicalBody getPhisicalBody(int ID);


    Iterator<PhisicalBody> iterator();
}