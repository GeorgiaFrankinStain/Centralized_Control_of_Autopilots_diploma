package Logic.FootprintSpaceTime;

import GUI.Rendering.TypePhisicalBody;

public interface RenderingPolygon {
    PolygonExtended getArea();
    TypePhisicalBody getTypePhisicalBody();
    int getID();
    int getLevel();
}
