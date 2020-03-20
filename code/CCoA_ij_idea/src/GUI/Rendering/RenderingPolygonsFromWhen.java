package GUI.Rendering;

import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.FootprintSpaceTime.RenderingPolygon;
import Logic.TypesInLevel;

import java.util.List;

public interface RenderingPolygonsFromWhen {
    public List<RenderingPolygon> getRenderingPolygonsFromWhen(PolygonExtended areaFind, long when);
    public List<RenderingPolygon> getRenderingPolygonsFromWhen(PolygonExtended areaFind, long when, TypesInLevel type);
}
