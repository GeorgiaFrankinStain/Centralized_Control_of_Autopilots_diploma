package Logic.FootprintSpaceTime;

import GUI.Rendering.TypePhisicalBody;

public interface PhisicalBody {
    PolygonExtended getPolygonExtended();
    TypePhisicalBody getTypePhisicalBody();
    int getID();
    int getLevel();

}
