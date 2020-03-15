package Logic.FootprintSpaceTime;

import GUI.Rendering.TypePhisicalBody;

public interface PhisicalBody {
    Polygon getArea();
    TypePhisicalBody getTypePhisicalBody();
    int getID();
    int getLevel();
}
