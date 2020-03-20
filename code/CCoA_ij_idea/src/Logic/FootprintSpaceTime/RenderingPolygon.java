package Logic.FootprintSpaceTime;

import GUI.Rendering.TypePhisicalBody;

public interface RenderingPolygon {
    Polygon getArea();
    TypePhisicalBody getTypePhisicalBody();
    int getID();
    int getLevel();
}
