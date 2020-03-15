package GUI.Rendering;

import Logic.FootprintSpaceTime.PhisicalBody;
import Logic.FootprintSpaceTime.Polygon;

import java.util.List;

public interface HistChangesFromWhen {
    public List<PhisicalBody> getPhysicalBodysFromWhen(Polygon areaFind, long when);
    public List<PhisicalBody> getPhysicalBodysFromWhen(Polygon areaFind, long when, int level);
}
