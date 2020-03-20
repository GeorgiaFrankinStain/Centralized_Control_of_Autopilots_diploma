package GUI.Rendering;

import Logic.FootprintSpaceTime.RenderingPolygon;
import Logic.FootprintSpaceTime.Polygon;
import Logic.TypesInLevel;

import java.util.List;

public interface RendeingPolygonsFromWhen {
    public List<RenderingPolygon> getRenderingPolygonsFromWhen(Polygon areaFind, long when);
    public List<RenderingPolygon> getRenderingPolygonsFromWhen(Polygon areaFind, long when, TypesInLevel type);
}
