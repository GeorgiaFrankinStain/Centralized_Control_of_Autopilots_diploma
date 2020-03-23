package GUI.StatementTaskRendering;

import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.FootprintSpaceTime.PhisicalBody;
import Logic.TypesInLevel;

import java.util.List;

public interface PhisicalBodysFromWhen {
    public List<PhisicalBody> getRenderingPolygonsFromWhen(PolygonExtended areaFind, int  when);
    public List<PhisicalBody> getRenderingPolygonsFromWhen(PolygonExtended areaFind, int  when, TypesInLevel type);
}
