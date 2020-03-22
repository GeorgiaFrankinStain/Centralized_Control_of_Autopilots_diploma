package GUI.Rendering;

import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.FootprintSpaceTime.PhisicalBody;
import Logic.TypesInLevel;

import java.util.List;

public interface PhisicalBodysFromWhen {
    public List<PhisicalBody> getPhisicalBodysFromWhen(PolygonExtended areaFind, int when);
    public List<PhisicalBody> getPhisicalBodysFromWhen(PolygonExtended areaFind, int when, TypesInLevel type);
}
