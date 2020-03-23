package GUI.ExecutionTaskRendering;

import Logic.FootprintSpaceTime.PhisicalBody;
import Logic.FootprintSpaceTime.PolygonExtended;

import java.util.Iterator;

public interface PoolPhisicalBodys {
    public void fillYourself(PolygonExtended areaRendering, int time);
    public PhisicalBody getPhisicalBody(int ID);


    Iterator<PhisicalBody> iterator();
}